package com.saltfish.assistant.data.local;

import java.lang.System;

@androidx.room.Dao
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u001b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0013\u0010\n\u001a\u0004\u0018\u00010\bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0019\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0014\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00110\u0010H\'J\u0019\u0010\u0012\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ-\u0010\u0013\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0019"}, d2 = {"Lcom/saltfish/assistant/data/local/TaskDao;", "", "deleteOldCompleted", "", "before", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getById", "Lcom/saltfish/assistant/domain/model/TaskEntity;", "id", "getNextPending", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "task", "(Lcom/saltfish/assistant/domain/model/TaskEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeAll", "Lkotlinx/coroutines/flow/Flow;", "", "update", "updateStatus", "status", "Lcom/saltfish/assistant/domain/model/TaskStatus;", "error", "", "(JLcom/saltfish/assistant/domain/model/TaskStatus;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saltfish_debug"})
public abstract interface TaskDao {
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM tasks ORDER BY priority DESC, createdAt ASC")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.saltfish.assistant.domain.model.TaskEntity>> observeAll();
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "SELECT * FROM tasks WHERE status = \'PENDING\' ORDER BY priority DESC, createdAt ASC LIMIT 1")
    public abstract java.lang.Object getNextPending(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.saltfish.assistant.domain.model.TaskEntity> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "SELECT * FROM tasks WHERE id = :id")
    public abstract java.lang.Object getById(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.saltfish.assistant.domain.model.TaskEntity> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull
    com.saltfish.assistant.domain.model.TaskEntity task, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Update
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull
    com.saltfish.assistant.domain.model.TaskEntity task, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "UPDATE tasks SET status = :status, errorMessage = :error WHERE id = :id")
    public abstract java.lang.Object updateStatus(long id, @org.jetbrains.annotations.NotNull
    com.saltfish.assistant.domain.model.TaskStatus status, @org.jetbrains.annotations.Nullable
    java.lang.String error, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "DELETE FROM tasks WHERE status IN (\'COMPLETED\', \'FAILED\', \'CANCELLED\') AND completedAt < :before")
    public abstract java.lang.Object deleteOldCompleted(long before, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 3)
    public final class DefaultImpls {
    }
}