package com.saltfish.assistant.service;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u0000 /2\u00020\u0001:\u0001/B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ\u0018\u0010\u001f\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0002J\u0014\u0010$\u001a\u0004\u0018\u00010%2\b\u0010&\u001a\u0004\u0018\u00010\'H\u0016J\b\u0010(\u001a\u00020\u001cH\u0016J\b\u0010)\u001a\u00020\u001cH\u0016J\"\u0010*\u001a\u00020+2\b\u0010&\u001a\u0004\u0018\u00010\'2\u0006\u0010,\u001a\u00020+2\u0006\u0010-\u001a\u00020+H\u0016J\u000e\u0010.\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020!R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\f8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u001e\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0010@BX\u0086.\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00158F\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00060"}, d2 = {"Lcom/saltfish/assistant/service/ScriptService;", "Landroid/app/Service;", "()V", "app", "Lcom/saltfish/assistant/SaltfishApp;", "getApp", "()Lcom/saltfish/assistant/SaltfishApp;", "deviceMonitor", "Lcom/saltfish/assistant/service/DeviceMonitor;", "isRunning", "", "notify", "Lcom/saltfish/assistant/service/NotificationHelper;", "getNotify", "()Lcom/saltfish/assistant/service/NotificationHelper;", "<set-?>", "Lcom/saltfish/assistant/engine/TaskScheduler;", "scheduler", "getScheduler", "()Lcom/saltfish/assistant/engine/TaskScheduler;", "schedulerState", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/saltfish/assistant/engine/SchedulerState;", "getSchedulerState", "()Lkotlinx/coroutines/flow/StateFlow;", "serviceScope", "Lkotlinx/coroutines/CoroutineScope;", "cancelTask", "", "taskId", "", "handleTaskCompleted", "task", "Lcom/saltfish/assistant/domain/model/TaskEntity;", "result", "Lcom/saltfish/assistant/domain/model/TaskResult;", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "onStartCommand", "", "flags", "startId", "submitTask", "Companion", "saltfish_debug"})
public final class ScriptService extends android.app.Service {
    private final kotlinx.coroutines.CoroutineScope serviceScope = null;
    private boolean isRunning = false;
    private com.saltfish.assistant.service.DeviceMonitor deviceMonitor;
    private com.saltfish.assistant.engine.TaskScheduler scheduler;
    @org.jetbrains.annotations.NotNull
    public static final com.saltfish.assistant.service.ScriptService.Companion Companion = null;
    public static final int NOTIFICATION_SERVICE = 1001;
    
    public ScriptService() {
        super();
    }
    
    private final com.saltfish.assistant.SaltfishApp getApp() {
        return null;
    }
    
    private final com.saltfish.assistant.service.NotificationHelper getNotify() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.engine.TaskScheduler getScheduler() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.saltfish.assistant.engine.SchedulerState> getSchedulerState() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public android.os.IBinder onBind(@org.jetbrains.annotations.Nullable
    android.content.Intent intent) {
        return null;
    }
    
    @java.lang.Override
    public void onCreate() {
    }
    
    @java.lang.Override
    public int onStartCommand(@org.jetbrains.annotations.Nullable
    android.content.Intent intent, int flags, int startId) {
        return 0;
    }
    
    public final void submitTask(@org.jetbrains.annotations.NotNull
    com.saltfish.assistant.domain.model.TaskEntity task) {
    }
    
    public final void cancelTask(long taskId) {
    }
    
    private final void handleTaskCompleted(com.saltfish.assistant.domain.model.TaskEntity task, com.saltfish.assistant.domain.model.TaskResult result) {
    }
    
    @java.lang.Override
    public void onDestroy() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/saltfish/assistant/service/ScriptService$Companion;", "", "()V", "NOTIFICATION_SERVICE", "", "saltfish_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}