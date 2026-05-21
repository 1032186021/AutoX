package com.saltfish.assistant.data.remote.api

import com.google.gson.JsonObject
import com.saltfish.assistant.data.remote.ApiResponse
import com.saltfish.assistant.domain.entity.SaltfishGoodsAnalysisEntity
import com.saltfish.assistant.domain.entity.SaltfishGoodsEntity
import retrofit2.http.*

interface GoodsApi {
    @POST("/admin/aios-saltfish/goods/add")
    suspend fun add(@Body body: JsonObject): ApiResponse<SaltfishGoodsEntity>

    @POST("/admin/aios-saltfish/goods/update")
    suspend fun update(@Body body: JsonObject): ApiResponse<SaltfishGoodsEntity>

    @GET("/admin/aios-saltfish/goods/info")
    suspend fun getInfo(@Query("id") id: String): ApiResponse<SaltfishGoodsEntity>

    @POST("/admin/aios-saltfish/goods/list")
    suspend fun getList(@Body body: JsonObject): ApiResponse<List<SaltfishGoodsEntity>>

    @POST("/admin/aios-saltfish/goods/associationAdd")
    suspend fun addAssociation(@Body body: JsonObject): ApiResponse<JsonObject>

    @POST("/admin/aios-saltfish/goods/addAnalysis")
    suspend fun addAnalysis(@Body body: JsonObject): ApiResponse<SaltfishGoodsAnalysisEntity>
}
