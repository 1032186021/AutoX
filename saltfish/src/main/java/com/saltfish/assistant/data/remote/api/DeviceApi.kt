package com.saltfish.assistant.data.remote.api

import com.google.gson.JsonObject
import com.saltfish.assistant.data.remote.ApiResponse
import com.saltfish.assistant.domain.entity.SaltfishDeviceEntity
import retrofit2.http.*

interface DeviceApi {
    @POST("/admin/aios-saltfish/device/add")
    suspend fun register(@Body body: JsonObject): ApiResponse<SaltfishDeviceEntity>

    @POST("/admin/aios-saltfish/device/renew")
    suspend fun renew(@Body body: JsonObject): ApiResponse<SaltfishDeviceEntity>

    @GET("/admin/aios-saltfish/device/info")
    suspend fun getInfo(@Query("id") id: String): ApiResponse<SaltfishDeviceEntity>

    @POST("/admin/aios-saltfish/device/update")
    suspend fun updateInfo(@Body body: JsonObject): ApiResponse<JsonObject>

    @POST("/admin/aios-saltfish/device/heartbeat")
    suspend fun heartbeat(@Body body: JsonObject): ApiResponse<JsonObject>
}
