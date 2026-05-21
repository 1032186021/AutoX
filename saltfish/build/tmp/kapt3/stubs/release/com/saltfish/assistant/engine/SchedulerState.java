package com.saltfish.assistant.engine;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0007H\u00c6\u0003J)\u0010\u0011\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00072\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0005H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0017"}, d2 = {"Lcom/saltfish/assistant/engine/SchedulerState;", "", "currentTask", "Lcom/saltfish/assistant/domain/model/TaskEntity;", "queueSize", "", "isRunning", "", "(Lcom/saltfish/assistant/domain/model/TaskEntity;IZ)V", "getCurrentTask", "()Lcom/saltfish/assistant/domain/model/TaskEntity;", "()Z", "getQueueSize", "()I", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "toString", "", "saltfish_release"})
public final class SchedulerState {
    @org.jetbrains.annotations.Nullable
    private final com.saltfish.assistant.domain.model.TaskEntity currentTask = null;
    private final int queueSize = 0;
    private final boolean isRunning = false;
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.engine.SchedulerState copy(@org.jetbrains.annotations.Nullable
    com.saltfish.assistant.domain.model.TaskEntity currentTask, int queueSize, boolean isRunning) {
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
    
    public SchedulerState() {
        super();
    }
    
    public SchedulerState(@org.jetbrains.annotations.Nullable
    com.saltfish.assistant.domain.model.TaskEntity currentTask, int queueSize, boolean isRunning) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.saltfish.assistant.domain.model.TaskEntity component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.saltfish.assistant.domain.model.TaskEntity getCurrentTask() {
        return null;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final int getQueueSize() {
        return 0;
    }
    
    public final boolean component3() {
        return false;
    }
    
    public final boolean isRunning() {
        return false;
    }
}