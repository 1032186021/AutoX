package com.saltfish.assistant.engine;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J)\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0013J\u000e\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u0013J\u0018\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u00132\b\b\u0002\u0010\u001f\u001a\u00020\u0013J\u000e\u0010 \u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u0013J\u0012\u0010!\u001a\u00020\f2\b\u0010\"\u001a\u0004\u0018\u00010\u0001H\u0002R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006#"}, d2 = {"Lcom/saltfish/assistant/engine/ScriptBridge;", "", "engineManager", "Lcom/stardust/autojs/engine/ScriptEngineManager;", "scriptManager", "Lcom/saltfish/assistant/engine/ScriptManager;", "(Lcom/stardust/autojs/engine/ScriptEngineManager;Lcom/saltfish/assistant/engine/ScriptManager;)V", "_taskState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/saltfish/assistant/engine/TaskExecutionState;", "currentDeferred", "Lkotlinx/coroutines/CompletableDeferred;", "Lcom/saltfish/assistant/domain/model/TaskResult;", "taskState", "Lkotlinx/coroutines/flow/StateFlow;", "getTaskState", "()Lkotlinx/coroutines/flow/StateFlow;", "executeTask", "platform", "", "taskType", "params", "Lorg/json/JSONObject;", "(Ljava/lang/String;Ljava/lang/String;Lorg/json/JSONObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onTaskComplete", "", "data", "onTaskError", "error", "onTaskLog", "message", "level", "onTaskProgress", "parseResult", "raw", "saltfish_debug"})
public final class ScriptBridge {
    private final com.stardust.autojs.engine.ScriptEngineManager engineManager = null;
    private final com.saltfish.assistant.engine.ScriptManager scriptManager = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.saltfish.assistant.engine.TaskExecutionState> _taskState = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.saltfish.assistant.engine.TaskExecutionState> taskState = null;
    private kotlinx.coroutines.CompletableDeferred<com.saltfish.assistant.domain.model.TaskResult> currentDeferred;
    
    public ScriptBridge(@org.jetbrains.annotations.NotNull
    com.stardust.autojs.engine.ScriptEngineManager engineManager, @org.jetbrains.annotations.NotNull
    com.saltfish.assistant.engine.ScriptManager scriptManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.saltfish.assistant.engine.TaskExecutionState> getTaskState() {
        return null;
    }
    
    /**
     * Execute an automation task by running the adapter script in the Rhino engine.
     * Script runs synchronously in the calling thread.
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object executeTask(@org.jetbrains.annotations.NotNull
    java.lang.String platform, @org.jetbrains.annotations.NotNull
    java.lang.String taskType, @org.jetbrains.annotations.NotNull
    org.json.JSONObject params, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.saltfish.assistant.domain.model.TaskResult> continuation) {
        return null;
    }
    
    public final void onTaskProgress(@org.jetbrains.annotations.NotNull
    java.lang.String message) {
    }
    
    public final void onTaskLog(@org.jetbrains.annotations.NotNull
    java.lang.String message, @org.jetbrains.annotations.NotNull
    java.lang.String level) {
    }
    
    public final void onTaskComplete(@org.jetbrains.annotations.NotNull
    java.lang.String data) {
    }
    
    public final void onTaskError(@org.jetbrains.annotations.NotNull
    java.lang.String error) {
    }
    
    private final com.saltfish.assistant.domain.model.TaskResult parseResult(java.lang.Object raw) {
        return null;
    }
}