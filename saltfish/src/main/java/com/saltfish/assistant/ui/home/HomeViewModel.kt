package com.saltfish.assistant.ui.home

import android.app.Application
import android.content.Context
import android.provider.Settings
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.saltfish.assistant.SaltfishApp
import com.saltfish.assistant.data.remote.SocketIOManager.ConnectionState
import com.saltfish.assistant.domain.model.DeviceInfo
import com.saltfish.assistant.engine.TaskExecutionState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

data class HomeUiState(
    val wsState: ConnectionState = ConnectionState.DISCONNECTED,
    val taskState: TaskExecutionState = TaskExecutionState.Idle,
    val deviceInfo: DeviceInfo = DeviceInfo(),
    val isAccessibilityEnabled: Boolean = false,
    val isFloatyPermissionGranted: Boolean = false,
    val isIgnoringBattery: Boolean = false,
    val username: String = "",
    val pendingTaskCount: Int = 0,
    val adapterVersions: Map<String, String> = emptyMap()
)

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val app = application as SaltfishApp

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                app.socketIOManager.connectionState,
                app.scriptBridge.taskState,
                app.scriptManager.adapterVersions
            ) { ws, task, adapters ->
                HomeUiState(
                    wsState = ws,
                    taskState = task,
                    deviceInfo = app.deviceRepository.collectDeviceInfo(),
                    isAccessibilityEnabled = isAccessibilityServiceEnabled(),
                    isFloatyPermissionGranted = Settings.canDrawOverlays(app),
                    isIgnoringBattery = isIgnoringBatteryOptimizations(),
                    username = app.preferencesManager.token?.let { "已登录" } ?: "未登录",
                    pendingTaskCount = 0,
                    adapterVersions = adapters
                )
            }.collect { _uiState.value = it }
        }
    }

    fun connectWebSocket() = app.socketIOManager.connect()
    fun disconnectWebSocket() = app.socketIOManager.disconnect()

    private fun isAccessibilityServiceEnabled(): Boolean {
        val service = "${app.packageName}/com.stardust.autojs.core.accessibility.AccessibilityService"
        val enabledServices = Settings.Secure.getString(
            app.contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
        ) ?: ""
        return enabledServices.contains(service)
    }

    private fun isIgnoringBatteryOptimizations(): Boolean {
        val powerManager = app.getSystemService(Context.POWER_SERVICE) as android.os.PowerManager
        return powerManager.isIgnoringBatteryOptimizations(app.packageName)
    }
}
