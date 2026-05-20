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

    var refreshToken: String?
        get() = prefs.getString("refresh_token", null)
        set(value) = prefs.edit { putString("refresh_token", value) }

    var username: String
        get() = prefs.getString("username", "") ?: ""
        set(value) = prefs.edit { putString("username", value) }

    var password: String
        get() = prefs.getString("password", "") ?: ""
        set(value) = prefs.edit { putString("password", value) }

    var rememberAccount: Boolean
        get() = prefs.getBoolean("remember_account", false)
        set(value) = prefs.edit { putBoolean("remember_account", value) }

    var nickName: String?
        get() = prefs.getString("nick_name", null)
        set(value) = prefs.edit { putString("nick_name", value) }

    var userInfoJson: String?
        get() = prefs.getString("user_info_json", null)
        set(value) = prefs.edit { putString("user_info_json", value) }

    fun isLoggedIn(): Boolean = token != null

    fun logout() {
        prefs.edit {
            remove("token")
            remove("refresh_token")
            remove("nick_name")
            remove("user_info_json")
        }
    }

    fun logCrash(ex: Throwable) {
        val crashes = prefs.getStringSet("crashes", emptySet())?.toMutableSet() ?: mutableSetOf()
        crashes.add("${System.currentTimeMillis()}: ${ex.message}")
        if (crashes.size > 50) crashes.clear()
        prefs.edit { putStringSet("crashes", crashes) }
    }
}
