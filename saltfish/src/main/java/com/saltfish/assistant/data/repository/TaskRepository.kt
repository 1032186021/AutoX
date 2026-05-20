package com.saltfish.assistant.data.repository

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.saltfish.assistant.data.local.AppDatabase
import com.saltfish.assistant.data.local.PreferencesManager
import com.saltfish.assistant.data.remote.ApiClient
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
        val id = db.taskDao().insert(task.copy(status = TaskStatus.PENDING))
        return id
    }

    suspend fun updateStatus(id: Long, status: TaskStatus, error: String? = null) {
        db.taskDao().updateStatus(id, status, error)
    }

    suspend fun reportResult(task: TaskEntity): Boolean {
        return try {
            val body = JsonObject().apply {
                addProperty("taskId", task.id)
                addProperty("status", task.status.name)
                task.errorMessage?.let { addProperty("error", it) }
            }
            apiClient.api.reportTaskResult(body)
            true
        } catch (e: Exception) { false }
    }

    suspend fun fetchTasks(): List<TaskEntity> {
        return try {
            val response = apiClient.api.getTasks(prefs.deviceId ?: "")
            val arr = response.getAsJsonArray("data")
            arr.map { gson.fromJson(it, TaskEntity::class.java) }
        } catch (e: Exception) { emptyList() }
    }
}
