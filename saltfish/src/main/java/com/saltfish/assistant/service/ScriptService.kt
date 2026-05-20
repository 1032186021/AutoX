package com.saltfish.assistant.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.saltfish.assistant.MainActivity
import com.saltfish.assistant.SaltfishApp
import com.saltfish.assistant.data.remote.SocketEvent
import com.saltfish.assistant.domain.model.TaskEntity
import com.saltfish.assistant.domain.model.TaskResult
import com.saltfish.assistant.domain.model.TaskStatus
import com.saltfish.assistant.engine.SchedulerState
import com.saltfish.assistant.engine.TaskScheduler
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.StateFlow

class ScriptService : Service() {

    private val serviceScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private val app get() = applicationContext as SaltfishApp
    private val notify get() = app.notificationHelper
    private var isRunning = false
    private lateinit var deviceMonitor: DeviceMonitor

    lateinit var scheduler: TaskScheduler
        private set

    val schedulerState: StateFlow<SchedulerState>
        get() = scheduler.state

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        startForeground(NOTIFICATION_SERVICE, notify.buildServiceNotification("咸鱼助手运行中"))
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (!isRunning) {
            isRunning = true
            deviceMonitor = DeviceMonitor(app)
            serviceScope.launch {
                app.scriptManager.initialize()
            }
            scheduler = TaskScheduler(app.scriptBridge) { task, result ->
                serviceScope.launch {
                    handleTaskCompleted(task, result)
                }
            }
            // Restore pending tasks from DB
            serviceScope.launch {
                app.taskRepository.observeTasks().collect { tasks ->
                    val pending = tasks.filter {
                        it.status == TaskStatus.PENDING || it.status == TaskStatus.RUNNING
                    }
                    pending.forEach { scheduler.enqueue(it) }
                }
            }
            // Observe scheduler state for busy/idle switching
            serviceScope.launch {
                scheduler.state.collect { state ->
                    deviceMonitor.setBusy(state.isRunning)
                }
            }
            // Observe SocketIO events for device commands
            serviceScope.launch {
                app.socketIOManager.events.collect { event ->
                    if (event is SocketEvent.DeviceCommand) {
                        val cmd = event.data.optString("cmd", "")
                        app.deviceManager.handleServerCommand(cmd)
                    }
                }
            }
            deviceMonitor.start()
            app.deviceManager.start()
        }
        return START_STICKY
    }

    fun submitTask(task: TaskEntity) {
        serviceScope.launch {
            val id = app.taskRepository.enqueue(task)
            scheduler.enqueue(task.copy(id = id))
        }
    }

    fun cancelTask(taskId: Long) {
        scheduler.cancel(taskId)
        notify.cancelTaskNotification(taskId)
        serviceScope.launch {
            app.taskRepository.updateStatus(taskId, TaskStatus.CANCELLED)
        }
    }

    private fun handleTaskCompleted(task: TaskEntity, result: TaskResult) {
        val status = when (result) {
            is TaskResult.Success -> TaskStatus.COMPLETED
            is TaskResult.Error -> TaskStatus.FAILED
        }
        serviceScope.launch {
            app.taskRepository.updateStatus(
                task.id, status,
                if (result is TaskResult.Error) result.message else null
            )
            app.taskRepository.reportResult(
                task.copy(status = status, errorMessage = (result as? TaskResult.Error)?.message)
            )
        }

        when (result) {
            is TaskResult.Success -> {
                notify.showTaskCompleted(task.id, task.taskType, task.platform)
                notify.updateServiceNotification("${task.taskType} 执行完成")
            }
            is TaskResult.Error -> {
                notify.showTaskFailed(task.id, task.taskType, task.platform, result.message)
                notify.updateServiceNotification("${task.taskType} 执行失败")
                // High-priority alert for repeated failures
                if (task.retryCount >= task.maxRetries) {
                    notify.showError(
                        "任务最终失败",
                        "[${task.platform}] ${task.taskType}\n${result.message}"
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        if (::deviceMonitor.isInitialized) {
            deviceMonitor.stop()
        }
        app.deviceManager.stop()
        serviceScope.cancel()
        isRunning = false
        super.onDestroy()
    }

    companion object {
        const val NOTIFICATION_SERVICE = 1001
    }
}
