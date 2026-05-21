package com.saltfish.assistant.data.remote.api

import com.google.gson.JsonObject
import com.saltfish.assistant.data.remote.ApiResponse
import com.saltfish.assistant.domain.entity.PaginationResponse
import retrofit2.http.*

interface ReceiptApi {
    @POST("/admin/aios-saltfish/receipt/page")
    suspend fun getPage(@Body body: JsonObject): ApiResponse<PaginationResponse<JsonObject>>
}
