package com.saltfish.assistant.data.remote.api

import com.google.gson.JsonObject
import com.saltfish.assistant.data.remote.ApiResponse
import com.saltfish.assistant.domain.entity.SaltfishScriptBugEntity
import retrofit2.http.Body
import retrofit2.http.POST

interface BugApi {
    @POST("/admin/aios-saltfish/script/bug/add")
    suspend fun addScriptBug(@Body body: JsonObject): ApiResponse<SaltfishScriptBugEntity>
}
