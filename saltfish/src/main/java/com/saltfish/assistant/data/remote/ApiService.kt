package com.saltfish.assistant.data.remote

import com.google.gson.JsonObject
import retrofit2.http.*

interface ApiService {

    // Auth
    @POST("/admin/aios-saltfish/open/login")
    suspend fun login(@Body body: JsonObject): JsonObject

    @POST("/admin/base/open/login")
    suspend fun loginWithCaptcha(@Body body: JsonObject): JsonObject

    @GET("/admin/base/open/refreshToken")
    suspend fun refreshToken(@Query("refreshToken") token: String): JsonObject

    @GET("/admin/base/open/captcha")
    suspend fun getCaptcha(
        @Query("type") type: String = "png",
        @Query("height") height: Int = 40,
        @Query("width") width: Int = 150
    ): JsonObject

    // User
    @GET("/admin/base/comm/person")
    suspend fun getUserInfo(): JsonObject

    @GET("/admin/base/comm/permmenu")
    suspend fun getPermissions(): JsonObject

    // Device
    @POST("/admin/aios-saltfish/device/register")
    suspend fun registerDevice(@Body body: JsonObject): JsonObject

    @POST("/admin/aios-saltfish/device/heartbeat")
    suspend fun deviceHeartbeat(@Body body: JsonObject): JsonObject

    // Account
    @POST("/admin/aios-saltfish/account/add")
    suspend fun addAccount(@Body body: JsonObject): JsonObject

    @POST("/admin/aios-saltfish/account/update")
    suspend fun updateAccount(@Body body: JsonObject): JsonObject

    @GET("/admin/aios-saltfish/account/list")
    suspend fun getAccounts(@Query("deviceId") deviceId: String): JsonObject

    // Task
    @GET("/admin/aios-saltfish/task/list")
    suspend fun getTasks(@Query("deviceId") deviceId: String): JsonObject

    @POST("/admin/aios-saltfish/task/report")
    suspend fun reportTaskResult(@Body body: JsonObject): JsonObject

    // Upgrade
    @GET("/admin/aios-saltfish/upgrade/check")
    suspend fun checkUpgrade(
        @Query("scriptVersion") scriptVersion: String,
        @Query("packageName") packageName: String
    ): JsonObject

    @GET("/admin/aios-saltfish/cloud-config")
    suspend fun getCloudConfig(@Query("deviceId") deviceId: String): JsonObject
}
