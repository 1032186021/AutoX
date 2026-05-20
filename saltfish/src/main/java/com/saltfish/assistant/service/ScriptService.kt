package com.saltfish.assistant.service

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.saltfish.assistant.MainActivity
import com.saltfish.assistant.SaltfishApp
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
    private var isRunning = false

    lateinit var scheduler: TaskScheduler
        private set

    val schedulerState: StateFlow<SchedulerState>
        get() = scheduler.state

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        startForeground(NOTIFICATION_ID, createNotification("咸鱼助手运行中"))
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (!isRunning) {
            isRunning = true
            serviceScope.launch {
                app.scriptManager.initialize()
            }
            scheduler = TaskScheduler(app.scriptBridge) { task, result ->
                serviceScope.launch {
                    handleTaskCompleted(task, result)
                }
            }
            // 恢复数据库中待执行的任务
            serviceScope.launch {
                app.taskRepository.observeTasks().collect { tasks ->
                    val pending = tasks.filter {
                        it.status == TaskStatus.PENDING || it.status == TaskStatus.RUNNING
                    }
                    pending.forEach { scheduler.enqueue(it) }
                }
            }
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
        // 更新通知
        updateNotification("${task.taskType} ${if (status == TaskStatus.COMPLETED) "完成" else "失败"}")
    }

    private fun createNotification(text: String) =
        NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("咸鱼助手")
            .setContentText(text)
            .setSmallIcon(android.R.drawable.ic_menu_manage)
            .setContentIntent(
                PendingIntent.getActivity(
                    this, 0,
                    Intent(this, MainActivity::class.java),
                    PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
                )
            )
            .build()

    private fun updateNotification(text: String) {
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("咸鱼助手")
            .setContentText(text)
            .setSmallIcon(android.R.drawable.ic_menu_manage)
            .build()
        val manager = getSystemService(NOTIFICATION_SERVICE) as android.app.NotificationManager
        manager.notify(NOTIFICATION_ID, notification)
    }

    override fun onDestroy() {
        serviceScope.cancel()
        isRunning = false
        super.onDestroy()
    }

    companion object {
        const val CHANNEL_ID = "saltfish_script"
        const val NOTIFICATION_ID = 1001
    }
}
