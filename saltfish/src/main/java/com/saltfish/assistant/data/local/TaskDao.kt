package com.saltfish.assistant.data.local

import androidx.room.*
import com.saltfish.assistant.domain.model.TaskEntity
import com.saltfish.assistant.domain.model.TaskStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks ORDER BY priority DESC, createdAt ASC")
    fun observeAll(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE status = 'PENDING' ORDER BY priority DESC, createdAt ASC LIMIT 1")
    suspend fun getNextPending(): TaskEntity?

    @Query("SELECT * FROM tasks WHERE id = :id")
    suspend fun getById(id: Long): TaskEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: TaskEntity): Long

    @Update
    suspend fun update(task: TaskEntity)

    @Query("UPDATE tasks SET status = :status, errorMessage = :error WHERE id = :id")
    suspend fun updateStatus(id: Long, status: TaskStatus, error: String? = null)

    @Query("DELETE FROM tasks WHERE status IN ('COMPLETED', 'FAILED', 'CANCELLED') AND completedAt < :before")
    suspend fun deleteOldCompleted(before: Long)
}
