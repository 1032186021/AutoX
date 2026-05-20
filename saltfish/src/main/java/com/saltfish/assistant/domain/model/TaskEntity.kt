package com.saltfish.assistant.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey val id: Long = 0,
    val platform: String,
    val taskType: String,
    val params: String,
    val priority: Int = 0,
    val status: TaskStatus = TaskStatus.PENDING,
    val createdAt: Long = System.currentTimeMillis(),
    val startedAt: Long? = null,
    val completedAt: Long? = null,
    val errorMessage: String? = null,
    val retryCount: Int = 0,
    val maxRetries: Int = 3
)

enum class TaskStatus { PENDING, RUNNING, COMPLETED, FAILED, CANCELLED }
