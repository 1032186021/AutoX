package com.saltfish.assistant.data.remote.api

import com.google.gson.JsonObject
import com.saltfish.assistant.data.remote.ApiResponse
import com.saltfish.assistant.domain.entity.SaltfishProductEntity
import retrofit2.http.*

interface ProductApi {
    @POST("/admin/aios-saltfish/product/add")
    suspend fun add(@Body body: JsonObject): ApiResponse<SaltfishProductEntity>

    @GET("/admin/aios-saltfish/product/info")
    suspend fun getInfo(@Query("id") id: Int): ApiResponse<SaltfishProductEntity>

    @POST("/admin/aios-saltfish/product/list")
    suspend fun getList(@Body body: JsonObject): ApiResponse<List<SaltfishProductEntity>>
}
