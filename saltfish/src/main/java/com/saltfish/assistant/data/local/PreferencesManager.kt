package com.saltfish.assistant.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.saltfish.assistant.data.remote.ApiConfig

class PreferencesManager(context: Context) {
    private val appContext = context.applicationContext
    private val prefs: SharedPreferences =
        context.getSharedPreferences("saltfish_prefs", Context.MODE_PRIVATE)

    val baseUrl: String
        get() = ApiConfig.API_BASE_URL

    var token: String?
        get() = prefs.getString("token", null)
        set(value) = prefs.edit { putString("token", value) }

    /** Device hardware-bound UUID — always derived, never from editable prefs. */
    val uuid: String
        get() = DeviceIdentity.getUUID(appContext) ?: ""

    var deviceId: String?
        get() = prefs.getString("device_id", null)
        set(value) = prefs.edit { putString("device_id", value) }

    val wsUrl: String
        get() = ApiConfig.WS_URL

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

    var deviceKey: String?
        get() = prefs.getString("device_key", null)
        set(value) = prefs.edit { putString("device_key", value) }

    var guideShown: Boolean
        get() = prefs.getBoolean("guide_shown", false)
        set(value) = prefs.edit { putBoolean("guide_shown", value) }

    var rememberAccount: Boolean
        get() = prefs.getBoolean("remember_account", false)
        set(value) = prefs.edit { putBoolean("remember_account", value) }

    var nickName: String?
        get() = prefs.getString("nick_name", null)
        set(value) = prefs.edit { putString("nick_name", value) }

    var userInfoJson: String?
        get() = prefs.getString("user_info_json", null)
        set(value) = prefs.edit { putString("user_info_json", value) }

    var deviceJson: String?
        get() = prefs.getString("device_json", null)
        set(value) = prefs.edit { putString("device_json", value) }

    var accountJson: String?
        get() = prefs.getString("account_json", null)
        set(value) = prefs.edit { putString("account_json", value) }

    var cloudConfigJson: String?
        get() = prefs.getString("cloud_config_json", null)
        set(value) = prefs.edit { putString("cloud_config_json", value) }

    /** 获取 deviceId 为 Long 类型，用于实体赋值 */
    val deviceIdLong: Long?
        get() = prefs.getString("device_id", null)?.toLongOrNull()

    fun isLoggedIn(): Boolean = token != null

    fun logout() {
        prefs.edit {
            remove("token")
            remove("refresh_token")
            remove("nick_name")
            remove("user_info_json")
            remove("device_json")
            remove("account_json")
            remove("cloud_config_json")
        }
    }

    /** 清除所有本地配置，用于重置设备 */
    fun clearAll() {
        prefs.edit { clear() }
    }

    fun logCrash(ex: Throwable) {
        val crashes = prefs.getStringSet("crashes", emptySet())?.toMutableSet() ?: mutableSetOf()
        crashes.add("${System.currentTimeMillis()}: ${ex.message}")
        if (crashes.size > 50) crashes.clear()
        prefs.edit { putStringSet("crashes", crashes) }
    }
}
