package com.saltfish.assistant.data.remote.api

import com.google.gson.JsonObject
import com.saltfish.assistant.data.remote.ApiResponse
import com.saltfish.assistant.domain.entity.CaptchaInfo
import com.saltfish.assistant.domain.entity.UserAuthorization
import retrofit2.http.*

interface AuthApi {
    @POST("/admin/aios-saltfish/open/login")
    suspend fun login(@Body body: JsonObject): ApiResponse<UserAuthorization>

    @POST("/admin/base/open/login")
    suspend fun loginWithCaptcha(@Body body: JsonObject): ApiResponse<UserAuthorization>

    @GET("/admin/base/open/refreshToken")
    suspend fun refreshToken(@Query("refreshToken") token: String): ApiResponse<UserAuthorization>

    @GET("/admin/base/open/captcha")
    suspend fun getCaptcha(
        @Query("type") type: String = "png",
        @Query("height") height: Int = 40,
        @Query("width") width: Int = 150
    ): ApiResponse<CaptchaInfo>
}
