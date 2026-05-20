package com.saltfish.assistant.data.repository

import com.google.gson.Gson
import com.saltfish.assistant.data.local.PreferencesManager
import com.saltfish.assistant.data.remote.ApiClient
import com.saltfish.assistant.domain.model.AccountInfo

class AccountRepository(
    private val apiClient: ApiClient,
    private val prefs: PreferencesManager
) {
    private val gson = Gson()

    suspend fun addAccount(account: AccountInfo): Boolean {
        return try {
            val json = gson.toJsonTree(account).asJsonObject
            apiClient.api.addAccount(json)
            true
        } catch (e: Exception) { false }
    }

    suspend fun getAccounts(): List<AccountInfo> {
        return try {
            val response = apiClient.api.getAccounts(prefs.deviceId ?: "")
            val arr = response.getAsJsonArray("data")
            arr.map { gson.fromJson(it, AccountInfo::class.java) }
        } catch (e: Exception) { emptyList() }
    }
}
