package com.saltfish.assistant.domain.entity

data class SaltfishAccountEntity(
    val id: Long = 0,
    val createTime: String? = null,
    val updateTime: String? = null,
    val username: String? = null,
    val avatar: String? = null,
    val sex: String? = null,
    val profile: String? = null,
    val remark: String? = null,
    val nickname: String? = null,
    val extension: Any? = null,
    val platform: String? = null,
    val userId: Long? = null,
    val deviceId: Long? = null,
    val config: Any? = null,
    val msgCount: Int = 0
)
