package com.saltfish.assistant.data.repository

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.saltfish.assistant.data.local.DeviceInfoCollector
import com.saltfish.assistant.data.local.PreferencesManager
import com.saltfish.assistant.data.remote.ApiClient
import com.saltfish.assistant.data.remote.ApiResult
import com.saltfish.assistant.data.remote.isSuccess
import com.saltfish.assistant.domain.entity.SaltfishDeviceEntity
import com.saltfish.assistant.domain.model.DeviceInfo

class DeviceRepository(
    private val apiClient: ApiClient,
    private val prefs: PreferencesManager,
    private val deviceInfoCollector: DeviceInfoCollector
) {
    private val gson = Gson()

    fun collectDeviceInfo(): DeviceInfo = deviceInfoCollector.collect()

    suspend fun registerDevice(): Boolean {
        val deviceInfo = deviceInfoCollector.collect()
        val json = gson.toJsonTree(deviceInfo).asJsonObject
        return when (val result = apiClient.safeApiCall { apiClient.device.register(json) }) {
            is ApiResult.Success -> {
                prefs.deviceId = result.data.id.toString()
                true
            }
            is ApiResult.Error -> false
        }
    }

    suspend fun registerWithSecret(secret: String): SaltfishDeviceEntity? {
        val deviceInfo = deviceInfoCollector.collect()
        val json = gson.toJsonTree(deviceInfo).asJsonObject.apply {
            addProperty("secret", secret)
        }
        return when (val result = apiClient.safeApiCall { apiClient.device.register(json) }) {
            is ApiResult.Success -> {
                prefs.deviceId = result.data.id.toString()
                result.data
            }
            is ApiResult.Error -> null
        }
    }

    suspend fun getDeviceInfo(): SaltfishDeviceEntity? {
        val uuid = prefs.uuid ?: return null
        return when (val result = apiClient.safeApiCall { apiClient.device.getInfo(uuid) }) {
            is ApiResult.Success -> result.data
            is ApiResult.Error -> null
        }
    }

    suspend fun renewDevice(secret: String): Boolean {
        val body = JsonObject().apply {
            addProperty("deviceId", prefs.deviceId)
            addProperty("secret", secret)
        }
        return apiClient.safeApiCall { apiClient.device.renew(body) }.isSuccess()
    }

    suspend fun sendHeartbeat(): Boolean {
        val deviceId = prefs.deviceId ?: return false
        val body = JsonObject().apply {
            addProperty("deviceId", deviceId)
            addProperty("timestamp", System.currentTimeMillis())
        }
        return apiClient.safeApiCall { apiClient.device.heartbeat(body) }.isSuccess()
    }
}
