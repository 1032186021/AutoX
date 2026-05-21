package com.saltfish.assistant.data.remote.api

import com.google.gson.JsonObject
import com.saltfish.assistant.data.remote.ApiResponse
import com.saltfish.assistant.domain.entity.SaltfishAppEntity
import okhttp3.MultipartBody
import retrofit2.http.*

interface ConfigApi {
    @GET("/admin/aios-saltfish/config/info")
    suspend fun getConfig(@Query("id") id: String = "common"): ApiResponse<JsonObject>

    @GET("/admin/aios-saltfish/upgrade/check")
    suspend fun checkUpgrade(
        @Query("scriptVersion") scriptVersion: String,
        @Query("packageName") packageName: String
    ): ApiResponse<JsonObject>

    @GET("/app/saltfish/comm/applatest")
    suspend fun getAppLatest(): ApiResponse<SaltfishAppEntity>

    @POST("/admin/aios-saltfish/log/add")
    suspend fun sendLog(@Body body: JsonObject): ApiResponse<JsonObject>

    @POST("/admin/base/comm/upload")
    suspend fun uploadFile(@Body body: MultipartBody): ApiResponse<String>
}
