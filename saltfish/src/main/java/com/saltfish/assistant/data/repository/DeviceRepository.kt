package com.saltfish.assistant.data.repository

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.saltfish.assistant.BuildConfig
import com.saltfish.assistant.data.local.DeviceInfoCollector
import com.saltfish.assistant.data.local.PreferencesManager
import com.saltfish.assistant.data.remote.ApiClient
import com.saltfish.assistant.data.remote.ApiResult
import com.saltfish.assistant.data.remote.SignHelper
import com.saltfish.assistant.data.remote.isSuccess
import com.saltfish.assistant.domain.entity.SaltfishAccountEntity
import com.saltfish.assistant.domain.entity.SaltfishDeviceEntity
import com.saltfish.assistant.domain.model.DeviceInfo

class DeviceRepository(
    private val apiClient: ApiClient,
    private val prefs: PreferencesManager,
    private val deviceInfoCollector: DeviceInfoCollector
) {
    private val gson = Gson()

    fun collectDeviceInfo(): DeviceInfo = deviceInfoCollector.collect()

    // ==================== register ====================
    // 对应 TS: export function register(secret: string): SaltfishDeviceEntity | null

    suspend fun register(secret: String): SaltfishDeviceEntity? {
        if (secret.isEmpty()) return null
        val info = deviceInfoCollector.collect()
        val extra = JsonObject().apply {
            addProperty("name", info.model)
            addProperty("ip", info.ipAddress)
            addProperty("appVersion", info.appVersion)
            addProperty("scriptVersion", BuildConfig.SCRIPT_VERSION)
            add("info", gson.toJsonTree(info))
            addProperty("secret", secret)
        }
        val body = SignHelper.buildSignedBody(prefs, secret, extra)
        return when (val result = apiClient.safeApiCall { apiClient.device.register(body) }) {
            is ApiResult.Success -> {
                // 服务端 /device/add 只返回 id，需要重新获取完整设备信息
                getInfo(result.data.id.toString())
            }
            is ApiResult.Error -> null
        }
    }

    // ==================== renew ====================
    // 对应 TS: export function renew(secret: string): SaltfishDevi
    suspend fun renew(secret: String): SaltfishDeviceEntity? {
        if (secret.isEmpty()) return null
        val extra = JsonObject().apply {
            addProperty("deviceId", prefs.deviceId)
            addProperty("secret", secret)
        }
        val body = SignHelper.buildSignedBody(prefs, secret, extra)
        return when (val result = apiClient.safeApiCall { apiClient.device.renew(body) }) {
            is ApiResult.Success -> {
                val device = result.data
                prefs.deviceJson = gson.toJson(device)
                device
            }
            is ApiResult.Error -> null
        }
    }

    // ==================== getInfo ====================
    // 对应 TS: export function getInfo(id?: string | number): SaltfishDeviceEntity | null

    suspend fun getInfo(id: String? = null): SaltfishDeviceEntity? {
        val queryId = id ?: prefs.uuid
        return when (val result = apiClient.safeApiCall { apiClient.device.getInfo(queryId) }) {
            is ApiResult.Success -> {
                val device = result.data
                val accountsList = extractAccountsList(device.accounts)
                val groupedAccounts = groupAccountsByPlatform(accountsList)
                prefs.deviceId = device.id.toString()
                prefs.deviceJson = gson.toJson(device)
                prefs.accountJson = if (groupedAccounts != null) gson.toJson(groupedAccounts) else null
                device.copy(accounts = groupedAccounts)
            }
            is ApiResult.Error -> {
                prefs.deviceId = null
                prefs.deviceJson = null
                prefs.accountJson = null
                null
            }
        }
    }

    // ==================== updateInfo ====================
    // 对应 TS: export function updateInfo(data?: Partial<SaltfishDeviceEntity>): void

    suspend fun updateInfo(data: JsonObject? = null) {
        val info = deviceInfoCollector.collect()
        val body = JsonObject().apply {
            addProperty("id", prefs.deviceId ?: prefs.uuid)
            addProperty("uuid", prefs.uuid)
            addProperty("ip", info.ipAddress)
            addProperty("appVersion", info.appVersion)
            addProperty("scriptVersion", BuildConfig.SCRIPT_VERSION)
            add("info", gson.toJsonTree(info))
        }
        data?.entrySet()?.forEach { (key, value) ->
            body.add(key, value)
        }
        apiClient.safeApiCall { apiClient.device.updateInfo(body) }
    }

    // ==================== heartbeat (extra, not in TS) ====================

    suspend fun sendHeartbeat(): Boolean {
        val deviceId = prefs.deviceId ?: return false
        val body = JsonObject().apply {
            addProperty("deviceId", deviceId)
            addProperty("timestamp", System.currentTimeMillis())
        }
        return apiClient.safeApiCall { apiClient.device.heartbeat(body) }.isSuccess()
    }

    // ==================== helpers ====================

    /** 将 accounts (Any?) 转为 List<SaltfishAccountEntity> */
    private fun extractAccountsList(accounts: Any?): List<SaltfishAccountEntity> {
        return when (accounts) {
            is List<*> -> accounts.mapNotNull { item ->
                try { gson.fromJson(gson.toJson(item), SaltfishAccountEntity::class.java) } catch (_: Exception) { null }
            }
            else -> emptyList()
        }
    }

    /** 按 platform 分组，并按 updateTime 升序排列（晚更新的排后面） */
    private fun groupAccountsByPlatform(accounts: List<SaltfishAccountEntity>): Map<String, SaltfishAccountEntity>? {
        if (accounts.isEmpty()) return null
        val sorted = accounts.sortedBy { it.updateTime ?: "" }
        val grouped = linkedMapOf<String, SaltfishAccountEntity>()
        for (account in sorted) {
            account.platform?.let { grouped[it] = account }
        }
        return if (grouped.isEmpty()) null else grouped
    }
}
