package com.saltfish.assistant.data.remote.api

import com.google.gson.JsonObject
import com.saltfish.assistant.data.remote.ApiResponse
import com.saltfish.assistant.domain.entity.PromoteCopywritingEntity
import retrofit2.http.*

interface CopywritingApi {
    @GET("/admin/aios-promote/copywriting/info")
    suspend fun getInfo(@Query("id") id: Int): ApiResponse<PromoteCopywritingEntity>

    @POST("/admin/aios-promote/copywriting/list")
    suspend fun getList(@Body body: JsonObject): ApiResponse<List<PromoteCopywritingEntity>>

    @GET("/app/aios-promote/comm/getCommonComment")
    suspend fun getRandomCommonComment(): ApiResponse<List<PromoteCopywritingEntity>>

    @GET("/app/aios-promote/comm/getRandomtTissueComment")
    suspend fun getRandomTissueComment(): ApiResponse<List<PromoteCopywritingEntity>>
}
