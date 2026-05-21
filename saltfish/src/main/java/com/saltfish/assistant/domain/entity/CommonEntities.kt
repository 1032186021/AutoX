package com.saltfish.assistant.domain.entity

data class PaginationResponse<T>(
    val list: List<T>? = null,
    val pagination: PaginationInfo? = null
)

data class PaginationInfo(
    val page: Int = 0,
    val size: Int = 0,
    val total: Int = 0,
    val hasNextPage: Boolean? = null
)

data class PageQuery(
    val page: Int = 1,
    val size: Int = 20,
    val sort: String? = null,
    val order: String? = null
)
