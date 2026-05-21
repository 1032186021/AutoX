package com.saltfish.assistant.domain.entity

data class SaltfishTaskEntity(
    val id: Long = 0,
    val createTime: String? = null,
    val updateTime: String? = null,
    val name: String? = null,
    val type: String? = null,
    val params: Any? = null,
    val status: Int = 0,
    val executeTime: String? = null,
    val userId: Long? = null
)
