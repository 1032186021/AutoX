package com.saltfish.assistant.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.saltfish.assistant.SaltfishApp
import com.saltfish.assistant.service.ScriptService

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            val app = context.applicationContext as SaltfishApp
            if (app.preferencesManager.token != null) {
                val serviceIntent = Intent(context, ScriptService::class.java)
                context.startForegroundService(serviceIntent)
            }
        }
    }
}
