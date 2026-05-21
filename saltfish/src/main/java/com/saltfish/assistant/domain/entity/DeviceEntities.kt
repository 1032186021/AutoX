package com.saltfish.assistant.domain.entity

data class SaltfishDeviceEntity(
    val id: Long = 0,
    val createTime: String? = null,
    val updateTime: String? = null,
    val uuid: String? = null,
    val name: String? = null,
    val ip: String? = null,
    val appVersion: String? = null,
    val scriptVersion: String? = null,
    val status: Int = 0,
    val info: Any? = null,
    val connectTime: String? = null,
    val expiresTime: String? = null,
    val groupId: Long? = null,
    val remark: String? = null,
    val userId: Long? = null,
    val socketId: String? = null,
    val appId: Long? = null,
    val isMasterSlave: Boolean = false,
    val accounts: Any? = null
)
