package com.saltfish.assistant.ui.task

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.saltfish.assistant.SaltfishApp
import com.saltfish.assistant.data.remote.SocketEvent
import com.saltfish.assistant.domain.model.TaskEntity
import com.saltfish.assistant.domain.model.TaskStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject

data class TaskUiState(
    val tasks: List<TaskEntity> = emptyList(),
    val currentTask: TaskEntity? = null,
    val logs: List<String> = emptyList()
)

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val app = application as SaltfishApp

    private val _uiState = MutableStateFlow(TaskUiState())
    val uiState: StateFlow<TaskUiState> = _uiState.asStateFlow()

    init {
        // Observe local task queue
        viewModelScope.launch {
            app.taskRepository.observeTasks().collect { tasks ->
                _uiState.value = _uiState.value.copy(tasks = tasks)
            }
        }
        // Listen for Socket.IO dispatched tasks
        viewModelScope.launch {
            app.socketIOManager.events.collect { event ->
                when (event) {
                    is SocketEvent.TaskExecute -> handleRemoteTask(event.data)
                    else -> {}
                }
            }
        }
        // Sync cloud tasks on init
        viewModelScope.launch {
            val remoteTasks = app.taskRepository.fetchTasks()
            remoteTasks.forEach { app.taskRepository.enqueue(it) }
        }
    }

    private fun handleRemoteTask(data: JSONObject) {
        val task = TaskEntity(
            platform = data.optString("platform"),
            taskType = data.optString("taskType"),
            params = data.optJSONObject("params")?.toString() ?: "{}",
            priority = data.optInt("priority", 0)
        )
        viewModelScope.launch {
            app.taskRepository.enqueue(task)
        }
    }

    fun cancelTask(taskId: Long) {
        viewModelScope.launch {
            app.taskRepository.updateStatus(taskId, TaskStatus.CANCELLED)
        }
    }

    fun removeTask(taskId: Long) {
        viewModelScope.launch {
            app.taskRepository.updateStatus(taskId, TaskStatus.CANCELLED)
        }
    }
}
