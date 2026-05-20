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
import kotlinx.coroutines.*
import org.json.JSONObject

class ScriptService : Service() {

    private val serviceScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private val app get() = applicationContext as SaltfishApp
    private var isRunning = false

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
        }
        return START_STICKY
    }

    fun submitTask(task: TaskEntity, callback: (Boolean) -> Unit) {
        serviceScope.launch {
            try {
                val result = app.scriptBridge.executeTask(
                    task.platform,
                    task.taskType,
                    JSONObject(task.params)
                )
                callback(result is TaskResult.Success)
            } catch (e: Exception) {
                callback(false)
            }
        }
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
