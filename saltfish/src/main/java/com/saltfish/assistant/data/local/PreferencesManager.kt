package com.saltfish.assistant.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class PreferencesManager(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("saltfish_prefs", Context.MODE_PRIVATE)

    var baseUrl: String
        get() = prefs.getString("base_url", "https://api.saltfish.example.com") ?: ""
        set(value) = prefs.edit { putString("base_url", value) }

    var token: String?
        get() = prefs.getString("token", null)
        set(value) = prefs.edit { putString("token", value) }

    var deviceId: String?
        get() = prefs.getString("device_id", null)
        set(value) = prefs.edit { putString("device_id", value) }

    var wsUrl: String
        get() = prefs.getString("ws_url", "wss://ws.saltfish.example.com") ?: ""
        set(value) = prefs.edit { putString("ws_url", value) }

    var autoUpgrade: Boolean
        get() = prefs.getBoolean("auto_upgrade", true)
        set(value) = prefs.edit { putBoolean("auto_upgrade", value) }

    var isFirstLaunch: Boolean
        get() = prefs.getBoolean("is_first_launch", true)
        set(value) = prefs.edit { putBoolean("is_first_launch", value) }

    fun logCrash(ex: Throwable) {
        val crashes = prefs.getStringSet("crashes", emptySet())?.toMutableSet() ?: mutableSetOf()
        crashes.add("${System.currentTimeMillis()}: ${ex.message}")
        if (crashes.size > 50) crashes.clear()
        prefs.edit { putStringSet("crashes", crashes) }
    }
}
