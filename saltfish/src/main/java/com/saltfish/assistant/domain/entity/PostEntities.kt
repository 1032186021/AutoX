package com.saltfish.assistant.domain.entity

data class SaltfishPostEntity(
    val id: String? = null,
    val categoryId: Long? = null,
    val categoryName: String? = null,
    val tags: Any? = null,
    val md5Name: String? = null,
    val description: String? = null,
    val images: Any? = null,
    val userId: Long? = null,
    val accountId: Long? = null,
    val platform: String? = null,
    val showCount: Int = 0,
    val viewCount: Int = 0,
    val likeCount: Int = 0,
    val remark: String? = null,
    val pushTime: String? = null,
    val createTime: String? = null,
    val updateTime: String? = null,
    val collectCount: Int = 0,
    val name: String? = null,
    val video: String? = null,
    val updatedColumn: Any? = null
)
