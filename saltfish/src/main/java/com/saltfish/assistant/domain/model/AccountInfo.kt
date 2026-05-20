package com.saltfish.assistant.domain.model

data class AccountInfo(
    val id: Long = 0,
    val platform: String = "",
    val accountName: String = "",
    val nickName: String = "",
    val status: String = "",
    val deviceId: Long = 0
)
