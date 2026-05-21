package com.saltfish.assistant.data.remote.api

import com.google.gson.JsonObject
import com.saltfish.assistant.data.remote.ApiResponse
import com.saltfish.assistant.domain.entity.SaltfishTaskEntity
import retrofit2.http.*

interface TaskApi {
    @GET("/admin/aios-saltfish/task/info")
    suspend fun getInfo(@Query("id") id: Int): ApiResponse<SaltfishTaskEntity>

    @GET("/admin/aios-saltfish/config/info")
    suspend fun getConfig(@Query("id") id: String = "common"): ApiResponse<JsonObject>

    @POST("/admin/aios-saltfish/task/list")
    suspend fun getList(@Body body: JsonObject): ApiResponse<List<SaltfishTaskEntity>>

    @POST("/admin/aios-saltfish/task/updateStatus")
    suspend fun updateStatus(@Body body: JsonObject): ApiResponse<JsonObject>

    @POST("/admin/aios-saltfish/task/report")
    suspend fun reportResult(@Body body: JsonObject): ApiResponse<JsonObject>
}
