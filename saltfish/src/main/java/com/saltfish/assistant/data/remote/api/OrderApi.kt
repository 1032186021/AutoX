package com.saltfish.assistant.data.remote.api

import com.google.gson.JsonObject
import com.saltfish.assistant.data.remote.ApiResponse
import com.saltfish.assistant.domain.entity.SaltfishOrderEntity
import retrofit2.http.*

interface OrderApi {
    @POST("/admin/aios-saltfish/order/add")
    suspend fun add(@Body body: JsonObject): ApiResponse<SaltfishOrderEntity>

    @POST("/admin/aios-saltfish/order/update")
    suspend fun update(@Body body: JsonObject): ApiResponse<SaltfishOrderEntity>

    @GET("/admin/aios-saltfish/order/info")
    suspend fun getInfo(@Query("id") id: String): ApiResponse<SaltfishOrderEntity>

    @POST("/admin/aios-saltfish/order/delete")
    suspend fun delete(@Query("ids") ids: String): ApiResponse<JsonObject>
}
