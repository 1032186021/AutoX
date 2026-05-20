package com.saltfish.assistant.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context

object NotificationHelper {
    fun createChannels(context: Context) {
        val channel = NotificationChannel(
            ScriptService.CHANNEL_ID,
            "脚本服务",
            NotificationManager.IMPORTANCE_LOW
        ).apply {
            description = "咸鱼助手脚本执行通知"
        }
        val manager = context.getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)
    }
}
