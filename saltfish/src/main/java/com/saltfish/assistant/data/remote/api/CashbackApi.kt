package com.saltfish.assistant.data.remote.api

import com.google.gson.JsonObject
import com.saltfish.assistant.data.remote.ApiResponse
import com.saltfish.assistant.domain.entity.PaginationResponse
import com.saltfish.assistant.domain.entity.SaltfishOrderCashbackEntity
import retrofit2.http.*

interface CashbackApi {
    @GET("/admin/aios-saltfish/cashback/info")
    suspend fun getInfo(@Query("id") id: String): ApiResponse<SaltfishOrderCashbackEntity>

    @POST("/admin/aios-saltfish/cashback/add")
    suspend fun add(@Body body: JsonObject): ApiResponse<JsonObject>

    @POST("/admin/aios-saltfish/cashback/update")
    suspend fun update(@Body body: JsonObject): ApiResponse<JsonObject>

    @POST("/admin/aios-saltfish/cashback/page")
    suspend fun getPage(@Body body: JsonObject): ApiResponse<PaginationResponse<JsonObject>>
}
