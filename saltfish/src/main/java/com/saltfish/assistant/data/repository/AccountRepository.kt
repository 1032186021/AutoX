package com.saltfish.assistant.data.repository

import com.google.gson.Gson
import com.saltfish.assistant.data.local.PreferencesManager
import com.saltfish.assistant.data.remote.ApiClient
import com.saltfish.assistant.data.remote.ApiResult
import com.saltfish.assistant.data.remote.isSuccess
import com.saltfish.assistant.domain.entity.SaltfishAccountEntity

class AccountRepository(
    private val apiClient: ApiClient,
    private val prefs: PreferencesManager,
    private val deviceRepository: DeviceRepository
) {
    private val gson = Gson()

    // ==================== addAccountInfo ====================
    // 对应 TS: export const addAccountInfo = (platform: PlatformEnum, info: SaltfishAccountEntity)

    suspend fun addAccountInfo(platform: String, info: SaltfishAccountEntity): SaltfishAccountEntity? {
        val deviceId = prefs.deviceIdLong
        val entity = if (deviceId != null) info.copy(deviceId = deviceId) else info
        val body = gson.toJsonTree(entity.copy(platform = platform)).asJsonObject
        return when (val result = apiClient.safeApiCall { apiClient.account.add(body) }) {
            is ApiResult.Success -> {
                // 更新账号绑定信息 — 重新拉取设备信息
                if (deviceId != null) {
                    deviceRepository.getInfo(deviceId.toString())
                }
                result.data
            }
            is ApiResult.Error -> null
        }
    }

    // ==================== updateAccountInfo ====================
    // 对应 TS: export const updateAccountInfo = (id: number, info: SaltfishAccountEntity)

    suspend fun updateAccountInfo(id: Long, info: SaltfishAccountEntity): SaltfishAccountEntity? {
        val deviceId = prefs.deviceIdLong
        val entity = info.copy(
            id = id,
            deviceId = deviceId ?: info.deviceId
        )
        val body = gson.toJsonTree(entity).asJsonObject
        return when (val result = apiClient.safeApiCall { apiClient.account.update(body) }) {
            is ApiResult.Success -> result.data
            is ApiResult.Error -> null
        }
    }

    // ==================== refreshToken ====================
    // 对应 TS user.ts 中的 refreshToken

    suspend fun refreshToken(): Boolean {
        val token = prefs.refreshToken ?: return false
        return when (val result = apiClient.safeApiCall { apiClient.auth.refreshToken(token) }) {
            is ApiResult.Success -> {
                prefs.token = result.data.token
                result.data.refreshToken?.let { prefs.refreshToken = it }
                true
            }
            is ApiResult.Error -> false
        }
    }
}
