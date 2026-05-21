package com.saltfish.assistant.engine;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0005\u00a2\u0006\u0002\u0010\tJ\u000e\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010\u001e\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\u0006J\u0019\u0010 \u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\u0006H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010!J\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00060#J\u0018\u0010$\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010%\u001a\u00020&H\u0002J\u0006\u0010\'\u001a\u00020\bJ\u0006\u0010(\u001a\u00020\bJ\b\u0010)\u001a\u00020\bH\u0002J\b\u0010*\u001a\u00020\bH\u0002R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R \u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\f0\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006+"}, d2 = {"Lcom/saltfish/assistant/engine/TaskScheduler;", "", "scriptBridge", "Lcom/saltfish/assistant/engine/ScriptBridge;", "onTaskCompleted", "Lkotlin/Function2;", "Lcom/saltfish/assistant/domain/model/TaskEntity;", "Lcom/saltfish/assistant/domain/model/TaskResult;", "", "(Lcom/saltfish/assistant/engine/ScriptBridge;Lkotlin/jvm/functions/Function2;)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/saltfish/assistant/engine/SchedulerState;", "currentJob", "Lkotlinx/coroutines/Job;", "currentTask", "isRunning", "", "paused", "queue", "Ljava/util/concurrent/PriorityBlockingQueue;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "cancel", "taskId", "", "enqueue", "task", "executeTask", "(Lcom/saltfish/assistant/domain/model/TaskEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getQueue", "", "onTaskFailed", "error", "", "pause", "resume", "saveTasksToDb", "scheduleNext", "saltfish_debug"})
public final class TaskScheduler {
    private final com.saltfish.assistant.engine.ScriptBridge scriptBridge = null;
    private final kotlin.jvm.functions.Function2<com.saltfish.assistant.domain.model.TaskEntity, com.saltfish.assistant.domain.model.TaskResult, kotlin.Unit> onTaskCompleted = null;
    private final kotlinx.coroutines.CoroutineScope scope = null;
    private final java.util.concurrent.PriorityBlockingQueue<com.saltfish.assistant.domain.model.TaskEntity> queue = null;
    private kotlinx.coroutines.Job currentJob;
    private com.saltfish.assistant.domain.model.TaskEntity currentTask;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.saltfish.assistant.engine.SchedulerState> _state = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.saltfish.assistant.engine.SchedulerState> state = null;
    private boolean isRunning = false;
    @kotlin.jvm.Volatile
    private volatile boolean paused = false;
    
    public TaskScheduler(@org.jetbrains.annotations.NotNull
    com.saltfish.assistant.engine.ScriptBridge scriptBridge, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function2<? super com.saltfish.assistant.domain.model.TaskEntity, ? super com.saltfish.assistant.domain.model.TaskResult, kotlin.Unit> onTaskCompleted) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.saltfish.assistant.engine.SchedulerState> getState() {
        return null;
    }
    
    public final void pause() {
    }
    
    public final void resume() {
    }
    
    public final void enqueue(@org.jetbrains.annotations.NotNull
    com.saltfish.assistant.domain.model.TaskEntity task) {
    }
    
    public final void cancel(long taskId) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.saltfish.assistant.domain.model.TaskEntity> getQueue() {
        return null;
    }
    
    private final void scheduleNext() {
    }
    
    private final java.lang.Object executeTask(com.saltfish.assistant.domain.model.TaskEntity task, kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final void onTaskFailed(com.saltfish.assistant.domain.model.TaskEntity task, java.lang.String error) {
    }
    
    private final void saveTasksToDb() {
    }
}