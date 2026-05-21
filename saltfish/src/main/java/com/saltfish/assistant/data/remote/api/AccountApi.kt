package com.saltfish.assistant.data.remote.api

import com.google.gson.JsonObject
import com.saltfish.assistant.data.remote.ApiResponse
import com.saltfish.assistant.domain.entity.SaltfishAccountEntity
import retrofit2.http.*

interface AccountApi {
    @POST("/admin/aios-saltfish/account/add")
    suspend fun add(@Body body: JsonObject): ApiResponse<SaltfishAccountEntity>

    @POST("/admin/aios-saltfish/account/update")
    suspend fun update(@Body body: JsonObject): ApiResponse<SaltfishAccountEntity>

    @GET("/admin/aios-saltfish/account/list")
    suspend fun list(@Query("deviceId") deviceId: String): ApiResponse<List<SaltfishAccountEntity>>
}
