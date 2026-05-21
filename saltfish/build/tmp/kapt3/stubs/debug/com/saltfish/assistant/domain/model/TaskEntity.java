package com.saltfish.assistant.domain.model;

import java.lang.System;

@androidx.room.Entity(tableName = "tasks")
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b(\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B}\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0010\u001a\u00020\t\u0012\b\b\u0002\u0010\u0011\u001a\u00020\t\u00a2\u0006\u0002\u0010\u0012J\t\u0010%\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010\'\u001a\u00020\tH\u00c6\u0003J\t\u0010(\u001a\u00020\tH\u00c6\u0003J\t\u0010)\u001a\u00020\u0005H\u00c6\u0003J\t\u0010*\u001a\u00020\u0005H\u00c6\u0003J\t\u0010+\u001a\u00020\u0005H\u00c6\u0003J\t\u0010,\u001a\u00020\tH\u00c6\u0003J\t\u0010-\u001a\u00020\u000bH\u00c6\u0003J\t\u0010.\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010/\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0014J\u0010\u00100\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0014J\u008c\u0001\u00101\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0010\u001a\u00020\t2\b\b\u0002\u0010\u0011\u001a\u00020\tH\u00c6\u0001\u00a2\u0006\u0002\u00102J\u0013\u00103\u001a\u0002042\b\u00105\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00106\u001a\u00020\tH\u00d6\u0001J\t\u00107\u001a\u00020\u0005H\u00d6\u0001R\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017R\u0011\u0010\u0011\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0019R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001cR\u0011\u0010\u0010\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001cR\u0015\u0010\r\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b!\u0010\u0014R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0019\u00a8\u00068"}, d2 = {"Lcom/saltfish/assistant/domain/model/TaskEntity;", "", "id", "", "platform", "", "taskType", "params", "priority", "", "status", "Lcom/saltfish/assistant/domain/model/TaskStatus;", "createdAt", "startedAt", "completedAt", "errorMessage", "retryCount", "maxRetries", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;ILcom/saltfish/assistant/domain/model/TaskStatus;JLjava/lang/Long;Ljava/lang/Long;Ljava/lang/String;II)V", "getCompletedAt", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getCreatedAt", "()J", "getErrorMessage", "()Ljava/lang/String;", "getId", "getMaxRetries", "()I", "getParams", "getPlatform", "getPriority", "getRetryCount", "getStartedAt", "getStatus", "()Lcom/saltfish/assistant/domain/model/TaskStatus;", "getTaskType", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;ILcom/saltfish/assistant/domain/model/TaskStatus;JLjava/lang/Long;Ljava/lang/Long;Ljava/lang/String;II)Lcom/saltfish/assistant/domain/model/TaskEntity;", "equals", "", "other", "hashCode", "toString", "saltfish_debug"})
public final class TaskEntity {
    @androidx.room.PrimaryKey
    private final long id = 0L;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String platform = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String taskType = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String params = null;
    private final int priority = 0;
    @org.jetbrains.annotations.NotNull
    private final com.saltfish.assistant.domain.model.TaskStatus status = null;
    private final long createdAt = 0L;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Long startedAt = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Long completedAt = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String errorMessage = null;
    private final int retryCount = 0;
    private final int maxRetries = 0;
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.domain.model.TaskEntity copy(long id, @org.jetbrains.annotations.NotNull
    java.lang.String platform, @org.jetbrains.annotations.NotNull
    java.lang.String taskType, @org.jetbrains.annotations.NotNull
    java.lang.String params, int priority, @org.jetbrains.annotations.NotNull
    com.saltfish.assistant.domain.model.TaskStatus status, long createdAt, @org.jetbrains.annotations.Nullable
    java.lang.Long startedAt, @org.jetbrains.annotations.Nullable
    java.lang.Long completedAt, @org.jetbrains.annotations.Nullable
    java.lang.String errorMessage, int retryCount, int maxRetries) {
        return null;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String toString() {
        return null;
    }
    
    public TaskEntity(long id, @org.jetbrains.annotations.NotNull
    java.lang.String platform, @org.jetbrains.annotations.NotNull
    java.lang.String taskType, @org.jetbrains.annotations.NotNull
    java.lang.String params, int priority, @org.jetbrains.annotations.NotNull
    com.saltfish.assistant.domain.model.TaskStatus status, long createdAt, @org.jetbrains.annotations.Nullable
    java.lang.Long startedAt, @org.jetbrains.annotations.Nullable
    java.lang.Long completedAt, @org.jetbrains.annotations.Nullable
    java.lang.String errorMessage, int retryCount, int maxRetries) {
        super();
    }
    
    public final long component1() {
        return 0L;
    }
    
    public final long getId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getPlatform() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getTaskType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getParams() {
        return null;
    }
    
    public final int component5() {
        return 0;
    }
    
    public final int getPriority() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.domain.model.TaskStatus component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.domain.model.TaskStatus getStatus() {
        return null;
    }
    
    public final long component7() {
        return 0L;
    }
    
    public final long getCreatedAt() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long getStartedAt() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long component9() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long getCompletedAt() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getErrorMessage() {
        return null;
    }
    
    public final int component11() {
        return 0;
    }
    
    public final int getRetryCount() {
        return 0;
    }
    
    public final int component12() {
        return 0;
    }
    
    public final int getMaxRetries() {
        return 0;
    }
}