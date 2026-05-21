package com.saltfish.assistant.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import androidx.core.app.NotificationCompat
import com.saltfish.assistant.MainActivity

class NotificationHelper(private val context: Context) {

    private val manager: NotificationManager
        get() = context.getSystemService(NotificationManager::class.java)

    // region Channel IDs

    companion object {
        const val CHANNEL_SERVICE = "saltfish_service"
        const val CHANNEL_TASK_RESULT = "saltfish_task_result"
        const val CHANNEL_ERROR = "saltfish_error"
        const val CHANNEL_UPGRADE = "saltfish_upgrade"

        const val NOTIFICATION_SERVICE = 1001
        const val NOTIFICATION_ERROR = 3001
        const val NOTIFICATION_UPGRADE = 4001

        private const val TASK_NOTIFICATION_OFFSET = 2000
        private const val MAX_TASK_NOTIFICATIONS = 100
    }

    // endregion

    // region Create Channels

    fun createChannels() {
        val serviceChannel = NotificationChannel(
            CHANNEL_SERVICE,
            "脚本服务",
            NotificationManager.IMPORTANCE_LOW
        ).apply {
            description = "显示脚本服务运行状态"
            setShowBadge(false)
        }

        val taskResultChannel = NotificationChannel(
            CHANNEL_TASK_RESULT,
            "任务结果",
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = "任务执行结果通知"
            enableVibration(true)
            setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION), null)
        }

        val errorChannel = NotificationChannel(
            CHANNEL_ERROR,
            "异常告警",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "任务异常、服务崩溃等重要告警"
            enableVibration(true)
            vibrationPattern = longArrayOf(0, 300, 200, 300)
            setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM), null)
            lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        }

        val upgradeChannel = NotificationChannel(
            CHANNEL_UPGRADE,
            "版本更新",
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = "应用和适配器版本更新通知"
            enableVibration(true)
            setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION), null)
        }

        manager.createNotificationChannels(
            listOf(serviceChannel, taskResultChannel, errorChannel, upgradeChannel)
        )
    }

    // endregion

    // region Service Notification

    fun buildServiceNotification(text: String): Notification {
        return NotificationCompat.Builder(context, CHANNEL_SERVICE)
            .setContentTitle("咸鱼助手")
            .setContentText(text)
            .setSmallIcon(android.R.drawable.ic_menu_manage)
            .setContentIntent(mainActivityIntent())
            .setOngoing(true)
            .setSilent(true)
            .build()
    }

    fun updateServiceNotification(text: String) {
        val notification = buildServiceNotification(text)
        manager.notify(NOTIFICATION_SERVICE, notification)
    }

    // endregion

    // region Task Notifications

    fun showTaskCompleted(taskId: Long, taskType: String, platform: String) {
        val notification = NotificationCompat.Builder(context, CHANNEL_TASK_RESULT)
            .setContentTitle("任务完成")
            .setContentText("[$platform] $taskType")
            .setSubText("执行成功")
            .setSmallIcon(android.R.drawable.ic_menu_manage)
            .setContentIntent(mainActivityIntent())
            .setAutoCancel(true)
            .build()

        manager.notify(taskNotificationId(taskId), notification)
    }

    fun showTaskFailed(taskId: Long, taskType: String, platform: String, error: String) {
        val notification = NotificationCompat.Builder(context, CHANNEL_TASK_RESULT)
            .setContentTitle("任务失败")
            .setContentText("[$platform] $taskType")
            .setSubText("执行失败")
            .setSmallIcon(android.R.drawable.ic_menu_manage)
            .setContentIntent(mainActivityIntent())
            .setAutoCancel(true)
            .setStyle(NotificationCompat.BigTextStyle().bigText(error))
            .build()

        manager.notify(taskNotificationId(taskId), notification)
    }

    fun cancelTaskNotification(taskId: Long) {
        manager.cancel(taskNotificationId(taskId))
    }

    // endregion

    // region Error Notifications

    fun showError(title: String, message: String) {
        val notification = NotificationCompat.Builder(context, CHANNEL_ERROR)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(android.R.drawable.ic_menu_manage)
            .setContentIntent(mainActivityIntent())
            .setAutoCancel(true)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message))
            .build()

        manager.notify(NOTIFICATION_ERROR, notification)
    }

    // endregion

    // region Upgrade Notifications

    fun showUpgradeAvailable(version: String, downloadIntent: PendingIntent?) {
        val builder = NotificationCompat.Builder(context, CHANNEL_UPGRADE)
            .setContentTitle("发现新版本")
            .setContentText("新版本 v$version 可供下载")
            .setSmallIcon(android.R.drawable.ic_menu_manage)
            .setContentIntent(mainActivityIntent())
            .setAutoCancel(true)

        if (downloadIntent != null) {
            builder.addAction(0, "立即更新", downloadIntent)
        }

        manager.notify(NOTIFICATION_UPGRADE, builder.build())
    }

    fun cancelUpgradeNotification() {
        manager.cancel(NOTIFICATION_UPGRADE)
    }

    // endregion

    // region Helpers

    private fun mainActivityIntent(): PendingIntent {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        return PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

    private fun taskNotificationId(taskId: Long): Int {
        return TASK_NOTIFICATION_OFFSET + (taskId % MAX_TASK_NOTIFICATIONS).toInt()
    }

    // endregion
}
