package com.saltfish.assistant.domain.entity

data class SaltfishGoodsEntity(
    val id: String? = null,
    val categoryId: Long? = null,
    val status: Int = 0,
    val categoryName: String? = null,
    val purchasePrice: Double? = null,
    val sellingPrice: Double = 0.0,
    val name: String? = null,
    val description: String? = null,
    val brandName: String? = null,
    val images: Any? = null,
    val deliveryMethod: Any? = null,
    val freight: Double = 0.0,
    val coinDeduction: Int = 0,
    val userId: Long? = null,
    val accountId: Long? = null,
    val productId: Long? = null,
    val platform: String? = null,
    val type: Int = 0,
    val displayAttributes: Any? = null,
    val showCount: Int = 0,
    val viewCount: Int = 0,
    val wantCount: Int = 0,
    val remark: String? = null,
    val pushTime: String? = null,
    val createTime: String? = null,
    val updateTime: String? = null,
    val updatedColumn: Any? = null,
    val modelName: String? = null,
    val tags: Any? = null,
    val skus: Any? = null,
    val attributes: Any? = null,
    val md5Name: String? = null
)

data class SaltfishGoodsAnalysisEntity(
    val id: Long = 0,
    val createTime: String? = null,
    val updateTime: String? = null,
    val goodsId: String? = null,
    val platform: String? = null,
    val showCount: Int = 0,
    val viewCount: Int = 0,
    val wantCount: Int = 0,
    val userId: Long? = null,
    val accountId: Long? = null
)
