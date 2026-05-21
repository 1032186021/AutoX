package com.saltfish.assistant.data.remote.api

import com.google.gson.JsonObject
import com.saltfish.assistant.data.remote.ApiResponse
import com.saltfish.assistant.domain.entity.SaltfishPostEntity
import retrofit2.http.*

interface PostApi {
    @POST("/admin/aios-saltfish/post/add")
    suspend fun add(@Body body: JsonObject): ApiResponse<SaltfishPostEntity>

    @POST("/admin/aios-saltfish/post/update")
    suspend fun update(@Body body: JsonObject): ApiResponse<SaltfishPostEntity>

    @GET("/admin/aios-saltfish/post/info")
    suspend fun getInfo(@Query("id") id: String): ApiResponse<SaltfishPostEntity>

    @POST("/admin/aios-saltfish/post/list")
    suspend fun getList(@Body body: JsonObject): ApiResponse<List<SaltfishPostEntity>>
}
