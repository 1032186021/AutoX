package com.saltfish.assistant.data.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0019\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0011H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u000eH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J\u0012\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00110\u0015J\u0019\u0010\u0016\u001a\u00020\u00172\u0006\u0010\r\u001a\u00020\u000eH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ!\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u001bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001cJ-\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020 2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010#J\f\u0010$\u001a\u00020\u000e*\u00020%H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006&"}, d2 = {"Lcom/saltfish/assistant/data/repository/TaskRepository;", "", "apiClient", "Lcom/saltfish/assistant/data/remote/ApiClient;", "prefs", "Lcom/saltfish/assistant/data/local/PreferencesManager;", "db", "Lcom/saltfish/assistant/data/local/AppDatabase;", "(Lcom/saltfish/assistant/data/remote/ApiClient;Lcom/saltfish/assistant/data/local/PreferencesManager;Lcom/saltfish/assistant/data/local/AppDatabase;)V", "gson", "Lcom/google/gson/Gson;", "enqueue", "", "task", "Lcom/saltfish/assistant/domain/model/TaskEntity;", "(Lcom/saltfish/assistant/domain/model/TaskEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchTasks", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getNextPending", "observeTasks", "Lkotlinx/coroutines/flow/Flow;", "reportResult", "", "updateRemoteTaskStatus", "taskId", "status", "", "(JILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateStatus", "", "id", "Lcom/saltfish/assistant/domain/model/TaskStatus;", "error", "", "(JLcom/saltfish/assistant/domain/model/TaskStatus;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toTaskEntity", "Lcom/saltfish/assistant/domain/entity/SaltfishTaskEntity;", "saltfish_release"})
public final class TaskRepository {
    private final com.saltfish.assistant.data.remote.ApiClient apiClient = null;
    private final com.saltfish.assistant.data.local.PreferencesManager prefs = null;
    private final com.saltfish.assistant.data.local.AppDatabase db = null;
    private final com.google.gson.Gson gson = null;
    
    public TaskRepository(@org.jetbrains.annotations.NotNull
    com.saltfish.assistant.data.remote.ApiClient apiClient, @org.jetbrains.annotations.NotNull
    com.saltfish.assistant.data.local.PreferencesManager prefs, @org.jetbrains.annotations.NotNull
    com.saltfish.assistant.data.local.AppDatabase db) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.saltfish.assistant.domain.model.TaskEntity>> observeTasks() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getNextPending(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.saltfish.assistant.domain.model.TaskEntity> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object enqueue(@org.jetbrains.annotations.NotNull
    com.saltfish.assistant.domain.model.TaskEntity task, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object updateStatus(long id, @org.jetbrains.annotations.NotNull
    com.saltfish.assistant.domain.model.TaskStatus status, @org.jetbrains.annotations.Nullable
    java.lang.String error, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object reportResult(@org.jetbrains.annotations.NotNull
    com.saltfish.assistant.domain.model.TaskEntity task, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object fetchTasks(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.saltfish.assistant.domain.model.TaskEntity>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object updateRemoteTaskStatus(long taskId, int status, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
    
    private final com.saltfish.assistant.domain.model.TaskEntity toTaskEntity(com.saltfish.assistant.domain.entity.SaltfishTaskEntity $this$toTaskEntity) {
        return null;
    }
}