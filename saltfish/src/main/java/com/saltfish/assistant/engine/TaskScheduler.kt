package com.saltfish.assistant.engine

import com.saltfish.assistant.domain.model.TaskEntity
import com.saltfish.assistant.domain.model.TaskResult
import com.saltfish.assistant.domain.model.TaskStatus
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.json.JSONObject
import java.util.concurrent.PriorityBlockingQueue

data class SchedulerState(
    val currentTask: TaskEntity? = null,
    val queueSize: Int = 0,
    val isRunning: Boolean = false
)

class TaskScheduler(
    private val scriptBridge: ScriptBridge,
    private val onTaskCompleted: (TaskEntity, TaskResult) -> Unit
) {
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val queue = PriorityBlockingQueue<TaskEntity>(11, compareBy { it.priority })
    private var currentJob: Job? = null
    private var currentTask: TaskEntity? = null

    private val _state = MutableStateFlow(SchedulerState())
    val state: StateFlow<SchedulerState> = _state.asStateFlow()

    private var isRunning = false

    fun enqueue(task: TaskEntity) {
        queue.add(task.copy(status = TaskStatus.PENDING))
        _state.value = _state.value.copy(queueSize = queue.size)
        scheduleNext()
    }

    fun cancel(taskId: Long) {
        if (currentTask?.id == taskId) {
            currentJob?.cancel()
            onTaskFailed(currentTask!!, "任务已取消")
        } else {
            queue.removeAll { it.id == taskId }
            _state.value = _state.value.copy(queueSize = queue.size)
        }
    }

    fun getQueue(): List<TaskEntity> = queue.toList().sortedBy { it.priority }

    private fun scheduleNext() {
        if (isRunning) return

        scope.launch {
            isRunning = true
            while (queue.isNotEmpty()) {
                val task = queue.poll() ?: break
                currentTask = task
                _state.value = SchedulerState(
                    currentTask = task,
                    queueSize = queue.size,
                    isRunning = true
                )
                executeTask(task)
                currentTask = null
            }
            isRunning = false
            _state.value = SchedulerState(
                currentTask = null,
                queueSize = queue.size,
                isRunning = false
            )
        }
    }

    private suspend fun executeTask(task: TaskEntity) {
        val timeoutMs = 60_000L // default 60s timeout

        val job = withTimeoutOrNull(timeoutMs) {
            withContext(Dispatchers.IO) {
                scriptBridge.executeTask(
                    task.platform,
                    task.taskType,
                    JSONObject(task.params)
                )
            }
        }

        when {
            job == null -> {
                // Timeout
                if (task.retryCount < task.maxRetries) {
                    queue.add(task.copy(
                        retryCount = task.retryCount + 1,
                        status = TaskStatus.PENDING
                    ))
                } else {
                    onTaskFailed(task, "任务超时")
                }
            }
            job is TaskResult.Success -> {
                onTaskCompleted(task.copy(status = TaskStatus.COMPLETED), job)
            }
            job is TaskResult.Error -> {
                if (task.retryCount < task.maxRetries) {
                    queue.add(task.copy(
                        retryCount = task.retryCount + 1,
                        status = TaskStatus.PENDING
                    ))
                } else {
                    onTaskFailed(task, job.message)
                }
            }
        }
    }

    private fun onTaskFailed(task: TaskEntity, error: String) {
        val failed = task.copy(
            status = TaskStatus.FAILED,
            errorMessage = error,
            completedAt = System.currentTimeMillis()
        )
        onTaskCompleted(failed, TaskResult.Error(error))
    }

    private fun saveTasksToDb() {
        // persistence can be added here for crash recovery
    }
}
