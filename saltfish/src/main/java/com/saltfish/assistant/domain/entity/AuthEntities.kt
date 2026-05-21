package com.saltfish.assistant.domain.entity

data class UserAuthorization(
    val token: String? = null,
    val expire: Long = 0,
    val refreshToken: String? = null,
    val refreshExpire: Long = 0
)

data class CaptchaInfo(
    val captchaId: String? = null,
    val data: String? = null
)
