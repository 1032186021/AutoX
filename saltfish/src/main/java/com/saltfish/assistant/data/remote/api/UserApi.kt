package com.saltfish.assistant.data.remote.api

import com.google.gson.JsonObject
import com.saltfish.assistant.data.remote.ApiResponse
import retrofit2.http.GET

interface UserApi {
    @GET("/admin/base/comm/person")
    suspend fun getUserInfo(): ApiResponse<JsonObject>

    @GET("/admin/base/comm/permmenu")
    suspend fun getPermissions(): ApiResponse<JsonObject>
}
