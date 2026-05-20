package com.saltfish.assistant.service

import android.accessibilityservice.AccessibilityServiceInfo
import android.app.ActivityManager
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.accessibility.AccessibilityManager
import com.saltfish.assistant.SaltfishApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.io.File

typealias DeviceEventCallback = (event: String) -> Unit

class DeviceMonitor(private val app: SaltfishApp) {

    companion object {
        private const val ACCESSIBILITY_CHECK_INTERVAL = 5000L
        private const val ACCESSIBILITY_LOST_THRESHOLD = 3
        private const val OVERHEAT_TEMP = 70f
        private const val OVERHEAT_RECOVERY_TEMP = 67f
        private const val LOW_MEM_THRESHOLD = 100L // MB
        private const val HEALTH_INTERVAL_BUSY = 10000L
        private const val HEALTH_INTERVAL_IDLE = 30000L

        // Tick task default intervals
        const val TICK_REFRESH_TOKEN = "refreshToken"
        const val TICK_CHECK_VPN = "checkVpn"
        const val REFRESH_TOKEN_INTERVAL = 5 * 60 * 1000L
        const val CHECK_VPN_INTERVAL = 10 * 1000L
    }

    // Region: Event System

    private val listeners = mutableMapOf<String, MutableList<DeviceEventCallback>>()

    fun on(event: String, cb: DeviceEventCallback) {
        listeners.getOrPut(event) { mutableListOf() }.add(cb)
    }

    private fun emit(event: String) {
        listeners[event]?.forEach { it(event) }
    }

    // Endregion

    // Region: Tick Tasks

    private data class TickTask(
        val fn: suspend () -> Unit,
        val interval: Long,
        var lastRun: Long = 0
    )

    private val tickTasks = mutableMapOf<String, TickTask>()

    fun registerTick(key: String, interval: Long, fn: suspend () -> Unit) {
        tickTasks[key] = TickTask(fn, interval)
    }

    fun unregisterTick(key: String) {
        tickTasks.remove(key)
    }

    private suspend fun runTickTasks() {
        val now = System.currentTimeMillis()
        for ((_, task) in tickTasks) {
            if (now - task.lastRun >= task.interval) {
                task.lastRun = now
                try {
                    task.fn()
                } catch (e: Exception) {
                    // tick task failure shouldn't crash the monitor
                }
            }
        }
    }

    // Endregion

    // Region: State

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private var healthJob: Job? = null
    private var accJob: Job? = null
    private var accLostCount = 0
    private var accAlerted = false
    private var isOverheat = false
    @Volatile private var running = false
    @Volatile private var isIdle = true
    private var healthInterval = HEALTH_INTERVAL_IDLE

    // Endregion

    // Region: Accessibility Guard

    private fun checkAccessibility() {
        try {
            val am = app.getSystemService(Context.ACCESSIBILITY_SERVICE) as? AccessibilityManager ?: return
            val serviceId = app.packageName + "/" + app.packageName + ".accessibility.MyAccessibilityService"

            val enabled = am.getEnabledAccessibilityServiceList(
                AccessibilityServiceInfo.FEEDBACK_ALL_MASK
            ).any { it.id == serviceId }

            if (!enabled) {
                accLostCount++
                if (accLostCount == 1) {
                    emit("accessibility_lost")
                }
                if (accLostCount >= ACCESSIBILITY_LOST_THRESHOLD && !accAlerted) {
                    accAlerted = true
                    scope.launch {
                        app.notificationHelper.showError(
                            "无障碍服务已断开",
                            "无障碍服务已断开，请重新开启"
                        )
                    }
                    // Vibrate — ignore if device doesn't support it
                    try {
                        val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                            val manager = app.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? android.os.VibratorManager
                            manager?.defaultVibrator
                        } else {
                            @Suppress("DEPRECATION")
                            app.getSystemService(Context.VIBRATOR_SERVICE) as? android.os.Vibrator
                        }
                        @Suppress("DEPRECATION")
                        vibrator?.vibrate(300)
                    } catch (_: Exception) {}
                }
            } else {
                if (accLostCount > 0) {
                    emit("accessibility_restored")
                }
                accLostCount = 0
                accAlerted = false
            }
        } catch (_: Exception) {}
    }

    // Endregion

    // Region: Device Health

    private fun checkHealth() {
        // Temperature check
        try {
            val temp = readCpuTemp()
            if (temp > OVERHEAT_TEMP && !isOverheat) {
                isOverheat = true
                emit("overheat")
            } else if (temp <= OVERHEAT_RECOVERY_TEMP && isOverheat) {
                isOverheat = false
                emit("overheat_recovered")
            }
        } catch (_: Exception) {
            // thermal nodes not available on some devices
        }

        // Memory check
        try {
            val am = app.getSystemService(Context.ACTIVITY_SERVICE) as? ActivityManager ?: return
            val memInfo = ActivityManager.MemoryInfo()
            am.getMemoryInfo(memInfo)
            val availMem = memInfo.availMem / (1024 * 1024) // bytes → MB
            if (availMem < LOW_MEM_THRESHOLD) {
                emit("low_memory")
            }
        } catch (_: Exception) {}
    }

    private fun readCpuTemp(): Float {
        val thermalPaths = listOf(
            "/sys/class/thermal/thermal_zone0/temp",
            "/sys/class/thermal/thermal_zone1/temp",
            "/sys/class/thermal/thermal_zone2/temp"
        )
        var maxTemp = 0f
        for (path in thermalPaths) {
            try {
                val raw = File(path).readText().trim()
                val temp = raw.toFloatOrNull()?.div(1000f) ?: continue
                if (temp > maxTemp) maxTemp = temp
            } catch (_: Exception) { continue }
        }
        return maxTemp
    }

    // Endregion

    // Region: VPN Check

    private suspend fun checkVpn() {
        try {
            val cm = app.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager ?: return
            val network = cm.activeNetwork ?: return
            val caps = cm.getNetworkCapabilities(network) ?: return
            if (caps.hasTransport(NetworkCapabilities.TRANSPORT_VPN)) {
                app.notificationHelper.showError(
                    "检测到VPN/代理",
                    "检测到VPN/代理，软件即将退出"
                )
                delay(1000)
                android.os.Process.killProcess(android.os.Process.myPid())
            }
        } catch (_: Exception) {}
    }

    // Endregion

    // Region: Lifecycle

    private fun startHealthLoop() {
        healthJob?.cancel()
        healthJob = scope.launch {
            while (isActive) {
                delay(healthInterval)
                if (!isActive) return@launch
                runTickTasks()
                checkHealth()
            }
        }
    }

    fun start() {
        if (running) return

        // Register built-in tick tasks
        registerTick(TICK_REFRESH_TOKEN, REFRESH_TOKEN_INTERVAL) {
            try { app.accountRepository.refreshToken() } catch (_: Exception) {}
        }

        registerTick(TICK_CHECK_VPN, CHECK_VPN_INTERVAL) {
            checkVpn()
        }

        // Start main health loop (ticks + health check)
        startHealthLoop()

        // Start accessibility check loop (higher frequency)
        accJob = scope.launch {
            while (isActive) {
                delay(ACCESSIBILITY_CHECK_INTERVAL)
                if (!isActive) return@launch
                checkAccessibility()
            }
        }

        running = true
    }

    fun stop() {
        healthJob?.cancel()
        healthJob = null
        accJob?.cancel()
        accJob = null
        tickTasks.clear()
        running = false
    }

    fun setBusy(busy: Boolean) {
        val newIdle = !busy
        if (newIdle == isIdle) return
        isIdle = newIdle
        healthInterval = if (isIdle) HEALTH_INTERVAL_IDLE else HEALTH_INTERVAL_BUSY
        if (running) {
            startHealthLoop()
        }
    }

    fun isRunning(): Boolean = running

    // Endregion
}
