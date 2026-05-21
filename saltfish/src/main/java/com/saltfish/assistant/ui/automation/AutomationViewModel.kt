package com.saltfish.assistant.ui.automation

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.saltfish.assistant.SaltfishApp
import com.saltfish.assistant.domain.model.TaskEntity
import com.saltfish.assistant.engine.SchedulerState
import com.saltfish.assistant.engine.TaskExecutionState
import com.saltfish.assistant.service.ScriptService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

data class AutomationUiState(
    val availablePlatforms: Map<String, String> = emptyMap(),
    val selectedPlatform: String = "",
    val selectedTaskType: String = "",
    val taskParams: String = "{}",
    val isServiceRunning: Boolean = false,
    val schedulerState: SchedulerState = SchedulerState(),
    val taskState: TaskExecutionState = TaskExecutionState.Idle,
    val submitEnabled: Boolean = false,
    val paramsError: String? = null
)

class AutomationViewModel(application: Application) : AndroidViewModel(application) {
    private val app = application as SaltfishApp

    private val _uiState = MutableStateFlow(AutomationUiState())
    val uiState: StateFlow<AutomationUiState> = _uiState.asStateFlow()

    private val _logLines = MutableStateFlow<List<String>>(emptyList())
    val logLines: StateFlow<List<String>> = _logLines.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                app.scriptManager.adapterVersions,
                app.scriptBridge.taskState
            ) { platforms, taskState ->
                val current = _uiState.value
                val selectedPlatform = if (current.selectedPlatform.isEmpty()) {
                    platforms.keys.firstOrNull() ?: ""
                } else {
                    current.selectedPlatform
                }
                val serviceRunning = isScriptServiceRunning()
                current.copy(
                    availablePlatforms = platforms,
                    selectedPlatform = selectedPlatform,
                    isServiceRunning = serviceRunning,
                    taskState = taskState,
                    submitEnabled = selectedPlatform.isNotEmpty() &&
                            current.selectedTaskType.isNotEmpty() &&
                            serviceRunning &&
                            current.paramsError == null
                )
            }.collect { _uiState.value = it }
        }
    }

    fun selectPlatform(platform: String) {
        _uiState.value = _uiState.value.copy(selectedPlatform = platform)
        refreshSubmitEnabled()
    }

    fun selectTaskType(taskType: String) {
        _uiState.value = _uiState.value.copy(selectedTaskType = taskType)
        refreshSubmitEnabled()
    }

    fun updateParams(params: String) {
        val error = try {
            org.json.JSONObject(params)
            null
        } catch (e: Exception) {
            "JSON 格式错误: ${e.message}"
        }
        _uiState.value = _uiState.value.copy(taskParams = params, paramsError = error)
        refreshSubmitEnabled()
    }

    fun startService() {
        val context = getApplication<SaltfishApp>()
        val intent = Intent(context, ScriptService::class.java)
        context.startForegroundService(intent)
        _uiState.value = _uiState.value.copy(isServiceRunning = true)
        refreshSubmitEnabled()
    }

    fun stopService() {
        val context = getApplication<SaltfishApp>()
        val intent = Intent(context, ScriptService::class.java)
        context.stopService(intent)
        _uiState.value = _uiState.value.copy(isServiceRunning = false)
        refreshSubmitEnabled()
    }

    fun submitTask() {
        val state = _uiState.value
        if (!state.submitEnabled) return

        val task = TaskEntity(
            platform = state.selectedPlatform,
            taskType = state.selectedTaskType,
            params = state.taskParams
        )

        viewModelScope.launch {
            app.taskRepository.enqueue(task)
            addLog("任务已提交: ${state.selectedPlatform}/${state.selectedTaskType}")
            // Reset selection for next task
            _uiState.value = _uiState.value.copy(
                selectedTaskType = "",
                taskParams = "{}"
            )
        }
    }

    fun refreshServiceState() {
        val running = isScriptServiceRunning()
        _uiState.value = _uiState.value.copy(isServiceRunning = running)
        refreshSubmitEnabled()
    }

    fun clearLogs() {
        _logLines.value = emptyList()
    }

    private fun addLog(message: String) {
        val timestamp = java.text.SimpleDateFormat("HH:mm:ss", java.util.Locale.getDefault())
            .format(java.util.Date())
        _logLines.value = (_logLines.value + "[$timestamp] $message").takeLast(100)
    }

    private fun refreshSubmitEnabled() {
        val state = _uiState.value
        _uiState.value = state.copy(
            submitEnabled = state.selectedPlatform.isNotEmpty() &&
                    state.selectedTaskType.isNotEmpty() &&
                    state.isServiceRunning &&
                    state.paramsError == null
        )
    }

    private fun isScriptServiceRunning(): Boolean {
        val manager = app.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        return manager.getRunningServices(Int.MAX_VALUE)
            .any { it.service.className == ScriptService::class.java.name }
    }

    companion object {
        val TASK_TYPES = listOf(
            "post_goods" to "发布商品",
            "refresh" to "刷新/擦亮",
            "collect_coin" to "领取金币",
            "browse" to "浏览任务",
            "sign_in" to "每日签到",
            "custom" to "自定义"
        )
    }
}
