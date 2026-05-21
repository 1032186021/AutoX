package com.saltfish.assistant.data.repository

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.saltfish.assistant.data.local.AppDatabase
import com.saltfish.assistant.data.local.PreferencesManager
import com.saltfish.assistant.data.remote.ApiClient
import com.saltfish.assistant.data.remote.ApiResult
import com.saltfish.assistant.data.remote.isSuccess
import com.saltfish.assistant.domain.entity.SaltfishTaskEntity
import com.saltfish.assistant.domain.model.TaskEntity
import com.saltfish.assistant.domain.model.TaskStatus
import kotlinx.coroutines.flow.Flow

class TaskRepository(
    private val apiClient: ApiClient,
    private val prefs: PreferencesManager,
    private val db: AppDatabase
) {
    private val gson = Gson()

    fun observeTasks(): Flow<List<TaskEntity>> = db.taskDao().observeAll()

    suspend fun getNextPending(): TaskEntity? = db.taskDao().getNextPending()

    suspend fun enqueue(task: TaskEntity): Long {
        return db.taskDao().insert(task.copy(status = TaskStatus.PENDING))
    }

    suspend fun updateStatus(id: Long, status: TaskStatus, error: String? = null) {
        db.taskDao().updateStatus(id, status, error)
    }

    suspend fun reportResult(task: TaskEntity): Boolean {
        val body = JsonObject().apply {
            addProperty("taskId", task.id)
            addProperty("status", task.status.name)
            task.errorMessage?.let { addProperty("error", it) }
        }
        return apiClient.safeApiCall { apiClient.task.reportResult(body) }.isSuccess()
    }

    suspend fun fetchTasks(): List<TaskEntity> {
        val body = JsonObject().apply {
            addProperty("deviceId", prefs.deviceId ?: "")
        }
        return when (val result = apiClient.safeApiCall { apiClient.task.getList(body) }) {
            is ApiResult.Success -> result.data.map { it.toTaskEntity() }
            is ApiResult.Error -> emptyList()
        }
    }

    suspend fun updateRemoteTaskStatus(taskId: Long, status: Int): Boolean {
        val deviceId = prefs.deviceId ?: return false
        val body = JsonObject().apply {
            addProperty("taskId", taskId)
            addProperty("status", status)
            addProperty("deviceId", deviceId)
        }
        return apiClient.safeApiCall { apiClient.task.updateStatus(body) }.isSuccess()
    }

    private fun SaltfishTaskEntity.toTaskEntity() = TaskEntity(
        id = id,
        platform = params?.let {
            try { JsonParser.parseString(it.toString()).asJsonObject.get("platform")?.asString } catch (_: Exception) { null }
        } ?: "",
        taskType = type ?: "",
        params = params?.toString() ?: "{}",
        status = when (status) {
            1 -> TaskStatus.RUNNING
            2 -> TaskStatus.COMPLETED
            3 -> TaskStatus.FAILED
            4 -> TaskStatus.CANCELLED
            else -> TaskStatus.PENDING
        }
    )
}
