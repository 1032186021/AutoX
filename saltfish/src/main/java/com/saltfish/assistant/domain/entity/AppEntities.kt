package com.saltfish.assistant.domain.entity

data class SaltfishAppEntity(
    val id: Long = 0,
    val createTime: String? = null,
    val updateTime: String? = null,
    val versionCode: Int = 0,
    val versionName: String? = null,
    val description: String? = null,
    val url: String? = null,
    val type: String? = null,
    val isForce: Int = 0
)

data class SaltfishScriptBugEntity(
    val id: Long = 0,
    val createTime: String? = null,
    val updateTime: String? = null,
    val description: String? = null,
    val log: String? = null,
    val layout: Any? = null,
    val screenshot: String? = null,
    val deviceId: Long = 0,
    val userId: Long? = null,
    val appVersion: String? = null,
    val scriptVersion: String? = null,
    val isFixed: Int = 0,
    val packageName: String? = null,
    val versionName: String? = null
)
