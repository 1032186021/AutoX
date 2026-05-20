package com.saltfish.assistant.engine

import com.saltfish.assistant.SaltfishApp
import com.saltfish.assistant.domain.entity.SaltfishDeviceEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.TimeUnit

enum class DeviceState { Idle, Registering, Online, Renewing, ExpiringSoon, Expired }

typealias DeviceStateCallback = (from: DeviceState, to: DeviceState) -> Unit

class DeviceManager(private val app: SaltfishApp) {

    companion object {
        private const val EXPIRY_CHECK_INTERVAL = 60_000L
        private const val DEVICE_UPDATE_INTERVAL = 60_000L
        private const val EXPIRY_WARN_DAYS = 3
        private val EXPIRY_FORMAT = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    }

    // Region: State

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private var expiryJob: Job? = null
    private var updateJob: Job? = null

    private var _state = DeviceState.Idle
    private var _device: SaltfishDeviceEntity? = null
    private val listeners = mutableListOf<DeviceStateCallback>()

    val state: DeviceState get() = _state

    val device: SaltfishDeviceEntity? get() = _device

    val remainingDays: Long
        get() {
            val dev = _device ?: return -1
            val expiresTime = dev.expiresTime ?: return -1
            val expiry = parseExpiryTime(expiresTime) ?: return -1
            val now = System.currentTimeMillis()
            return TimeUnit.MILLISECONDS.toDays(expiry - now).let {
                if (expiry - now > 0 && (expiry - now) % TimeUnit.DAYS.toMillis(1) > 0) it + 1 else it
            }
        }

    // Endregion

    // Region: State Transitions

    private fun setState(newState: DeviceState) {
        if (_state == newState) return
        val from = _state
        _state = newState
        listeners.forEach { it(from, newState) }
    }

    fun onStateChange(cb: DeviceStateCallback) {
        listeners.add(cb)
    }

    // Endregion

    // Region: Public Operations

    suspend fun register(secret: String): SaltfishDeviceEntity? {
        setState(DeviceState.Registering)
        return try {
            val result = app.deviceRepository.registerWithSecret(secret)
            if (result != null) {
                _device = result
                setState(DeviceState.Online)
                startTimers()
            } else {
                setState(DeviceState.Idle)
            }
            result
        } catch (e: Exception) {
            setState(DeviceState.Idle)
            null
        }
    }

    suspend fun renew(secret: String): Boolean {
        setState(DeviceState.Renewing)
        return try {
            val success = app.deviceRepository.renewDevice(secret)
            if (success) {
                sync()
            }
            success
        } catch (e: Exception) {
            checkExpiry()
            false
        }
    }

    suspend fun sync() {
        try {
            val info = app.deviceRepository.getDeviceInfo()
            if (info != null) {
                _device = info
                checkExpiry()
            }
        } catch (_: Exception) {}
    }

    // Endregion

    // Region: Gating Check

    fun checkDevice(): DeviceState {
        val deviceId = app.preferencesManager.deviceId
        if (deviceId == null || _device == null) {
            setState(DeviceState.Idle)
            return DeviceState.Idle
        }
        checkExpiry()
        return _state
    }

    // Endregion

    // Region: Expiry Check

    private fun checkExpiry() {
        val dev = _device
        if (dev == null || dev.expiresTime == null) {
            setState(DeviceState.Idle)
            return
        }
        val now = System.currentTimeMillis()
        val expiry = parseExpiryTime(dev.expiresTime)
            ?: run { setState(DeviceState.Idle); return }
        val days = remainingDays

        setState(
            when {
                now >= expiry -> DeviceState.Expired
                days <= EXPIRY_WARN_DAYS -> DeviceState.ExpiringSoon
                else -> DeviceState.Online
            }
        )
    }

    private fun parseExpiryTime(time: String): Long? {
        return try {
            val normalized = time.replace(" ", "T")
            EXPIRY_FORMAT.parse(normalized)?.time
        } catch (_: Exception) { null }
    }

    // Endregion

    // Region: Periodic Timers

    private fun startTimers() {
        stopTimers()

        expiryJob = scope.launch {
            while (isActive) {
                delay(EXPIRY_CHECK_INTERVAL)
                if (!isActive) return@launch
                checkExpiry()
            }
        }

        updateJob = scope.launch {
            while (isActive) {
                delay(DEVICE_UPDATE_INTERVAL)
                if (!isActive) return@launch
                try { app.deviceRepository.sendHeartbeat() } catch (_: Exception) {}
            }
        }
    }

    private fun stopTimers() {
        expiryJob?.cancel()
        expiryJob = null
        updateJob?.cancel()
        updateJob = null
    }

    fun start() {
        val deviceId = app.preferencesManager.deviceId
        if (deviceId != null) {
            checkExpiry()
            if (_state == DeviceState.Online || _state == DeviceState.ExpiringSoon) {
                startTimers()
            }
        } else {
            setState(DeviceState.Idle)
        }
    }

    fun stop() {
        stopTimers()
    }

    // Endregion

    // Region: WebSocket Command Handling

    fun handleServerCommand(cmd: String) {
        when (cmd) {
            "Client_Expire" -> {
                setState(DeviceState.Expired)
                stopTimers()
            }
            "Client_Sync" -> {
                scope.launch {
                    try { sync() } catch (_: Exception) {}
                }
            }
        }
    }

    // Endregion
}
