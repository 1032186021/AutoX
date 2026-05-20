package com.saltfish.assistant.data.remote

import com.google.gson.JsonObject
import retrofit2.http.*

interface ApiService {

    @POST("/admin/aios-saltfish/device/register")
    suspend fun registerDevice(@Body body: JsonObject): JsonObject

    @POST("/admin/aios-saltfish/device/heartbeat")
    suspend fun deviceHeartbeat(@Body body: JsonObject): JsonObject

    @POST("/admin/aios-saltfish/account/add")
    suspend fun addAccount(@Body body: JsonObject): JsonObject

    @POST("/admin/aios-saltfish/account/update")
    suspend fun updateAccount(@Body body: JsonObject): JsonObject

    @GET("/admin/aios-saltfish/account/list")
    suspend fun getAccounts(@Query("deviceId") deviceId: String): JsonObject

    @GET("/admin/aios-saltfish/task/list")
    suspend fun getTasks(@Query("deviceId") deviceId: String): JsonObject

    @POST("/admin/aios-saltfish/task/report")
    suspend fun reportTaskResult(@Body body: JsonObject): JsonObject

    @GET("/admin/aios-saltfish/upgrade/check")
    suspend fun checkUpgrade(
        @Query("scriptVersion") scriptVersion: String,
        @Query("packageName") packageName: String
    ): JsonObject

    @GET("/admin/aios-saltfish/cloud-config")
    suspend fun getCloudConfig(@Query("deviceId") deviceId: String): JsonObject
}
