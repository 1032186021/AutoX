package com.saltfish.assistant.data.repository

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.saltfish.assistant.data.local.PreferencesManager
import com.saltfish.assistant.data.remote.ApiClient
import com.saltfish.assistant.domain.model.DeviceInfo

class DeviceRepository(
    private val apiClient: ApiClient,
    private val prefs: PreferencesManager
) {
    private val gson = Gson()

    suspend fun registerDevice(deviceInfo: DeviceInfo): Boolean {
        return try {
            val json = gson.toJsonTree(deviceInfo).asJsonObject
            apiClient.api.registerDevice(json)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun sendHeartbeat(): Boolean {
        return try {
            val body = JsonObject().apply {
                addProperty("deviceId", prefs.deviceId)
                addProperty("timestamp", System.currentTimeMillis())
            }
            apiClient.api.deviceHeartbeat(body)
            true
        } catch (e: Exception) {
            false
        }
    }
}
