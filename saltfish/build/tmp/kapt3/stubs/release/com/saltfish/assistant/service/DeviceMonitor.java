package com.saltfish.assistant.service;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 62\u00020\u0001:\u000267B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u001e\u001a\u00020\u0018H\u0002J\b\u0010\u001f\u001a\u00020\u0018H\u0002J\u0011\u0010 \u001a\u00020\u0018H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010!J\u0010\u0010\"\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u0012H\u0002J\u0006\u0010#\u001a\u00020\u0006J5\u0010$\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u00122%\u0010%\u001a!\u0012\u0013\u0012\u00110\u0012\u00a2\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u00180\u0014j\u0002`&J\b\u0010\'\u001a\u00020(H\u0002J<\u0010)\u001a\u00020\u00182\u0006\u0010*\u001a\u00020\u00122\u0006\u0010+\u001a\u00020\f2\u001c\u0010,\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180-\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0014\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010.J\u0011\u0010/\u001a\u00020\u0018H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010!J\u000e\u00100\u001a\u00020\u00182\u0006\u00101\u001a\u00020\u0006J\u0006\u00102\u001a\u00020\u0018J\b\u00103\u001a\u00020\u0018H\u0002J\u0006\u00104\u001a\u00020\u0018J\u000e\u00105\u001a\u00020\u00182\u0006\u0010*\u001a\u00020\u0012R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R;\u0010\u0010\u001a/\u0012\u0004\u0012\u00020\u0012\u0012%\u0012#\u0012\u001f\u0012\u001d\u0012\u0013\u0012\u00110\u0012\u00a2\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u00180\u00140\u00130\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u001d0\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00068"}, d2 = {"Lcom/saltfish/assistant/service/DeviceMonitor;", "", "app", "Lcom/saltfish/assistant/SaltfishApp;", "(Lcom/saltfish/assistant/SaltfishApp;)V", "accAlerted", "", "accJob", "Lkotlinx/coroutines/Job;", "accLostCount", "", "healthInterval", "", "healthJob", "isIdle", "isOverheat", "listeners", "", "", "", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "event", "", "running", "scope", "Lkotlinx/coroutines/CoroutineScope;", "tickTasks", "Lcom/saltfish/assistant/service/DeviceMonitor$TickTask;", "checkAccessibility", "checkHealth", "checkVpn", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "emit", "isRunning", "on", "cb", "Lcom/saltfish/assistant/service/DeviceEventCallback;", "readCpuTemp", "", "registerTick", "key", "interval", "fn", "Lkotlin/coroutines/Continuation;", "(Ljava/lang/String;JLkotlin/jvm/functions/Function1;)V", "runTickTasks", "setBusy", "busy", "start", "startHealthLoop", "stop", "unregisterTick", "Companion", "TickTask", "saltfish_release"})
public final class DeviceMonitor {
    private final com.saltfish.assistant.SaltfishApp app = null;
    @org.jetbrains.annotations.NotNull
    public static final com.saltfish.assistant.service.DeviceMonitor.Companion Companion = null;
    private static final long ACCESSIBILITY_CHECK_INTERVAL = 5000L;
    private static final int ACCESSIBILITY_LOST_THRESHOLD = 3;
    private static final float OVERHEAT_TEMP = 70.0F;
    private static final float OVERHEAT_RECOVERY_TEMP = 67.0F;
    private static final long LOW_MEM_THRESHOLD = 100L;
    private static final long HEALTH_INTERVAL_BUSY = 10000L;
    private static final long HEALTH_INTERVAL_IDLE = 30000L;
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String TICK_REFRESH_TOKEN = "refreshToken";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String TICK_CHECK_VPN = "checkVpn";
    public static final long REFRESH_TOKEN_INTERVAL = 300000L;
    public static final long CHECK_VPN_INTERVAL = 10000L;
    private final java.util.Map<java.lang.String, java.util.List<kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit>>> listeners = null;
    private final java.util.Map<java.lang.String, com.saltfish.assistant.service.DeviceMonitor.TickTask> tickTasks = null;
    private final kotlinx.coroutines.CoroutineScope scope = null;
    private kotlinx.coroutines.Job healthJob;
    private kotlinx.coroutines.Job accJob;
    private int accLostCount = 0;
    private boolean accAlerted = false;
    private boolean isOverheat = false;
    @kotlin.jvm.Volatile
    private volatile boolean running = false;
    @kotlin.jvm.Volatile
    private volatile boolean isIdle = true;
    private long healthInterval = 30000L;
    
    public DeviceMonitor(@org.jetbrains.annotations.NotNull
    com.saltfish.assistant.SaltfishApp app) {
        super();
    }
    
    public final void on(@org.jetbrains.annotations.NotNull
    java.lang.String event, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> cb) {
    }
    
    private final void emit(java.lang.String event) {
    }
    
    public final void registerTick(@org.jetbrains.annotations.NotNull
    java.lang.String key, long interval, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> fn) {
    }
    
    public final void unregisterTick(@org.jetbrains.annotations.NotNull
    java.lang.String key) {
    }
    
    private final java.lang.Object runTickTasks(kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final void checkAccessibility() {
    }
    
    private final void checkHealth() {
    }
    
    private final float readCpuTemp() {
        return 0.0F;
    }
    
    private final java.lang.Object checkVpn(kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final void startHealthLoop() {
    }
    
    public final void start() {
    }
    
    public final void stop() {
    }
    
    public final void setBusy(boolean busy) {
    }
    
    public final boolean isRunning() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B8\u0012\u001c\u0010\u0002\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\'\u0010\u0012\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003H\u00c6\u0003\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u0013\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0007H\u00c6\u0003JE\u0010\u0015\u001a\u00020\u00002\u001e\b\u0002\u0010\u0002\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007H\u00c6\u0001\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001a\u001a\u00020\u001bH\u00d6\u0001J\t\u0010\u001c\u001a\u00020\u001dH\u00d6\u0001R,\u0010\u0002\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003\u00f8\u0001\u0000\u00a2\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\b\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u000e\"\u0004\b\u0010\u0010\u0011\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001e"}, d2 = {"Lcom/saltfish/assistant/service/DeviceMonitor$TickTask;", "", "fn", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "interval", "", "lastRun", "(Lkotlin/jvm/functions/Function1;JJ)V", "getFn", "()Lkotlin/jvm/functions/Function1;", "Lkotlin/jvm/functions/Function1;", "getInterval", "()J", "getLastRun", "setLastRun", "(J)V", "component1", "component2", "component3", "copy", "(Lkotlin/jvm/functions/Function1;JJ)Lcom/saltfish/assistant/service/DeviceMonitor$TickTask;", "equals", "", "other", "hashCode", "", "toString", "", "saltfish_release"})
    static final class TickTask {
        @org.jetbrains.annotations.NotNull
        private final kotlin.jvm.functions.Function1<kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object> fn = null;
        private final long interval = 0L;
        private long lastRun;
        
        @org.jetbrains.annotations.NotNull
        public final com.saltfish.assistant.service.DeviceMonitor.TickTask copy(@org.jetbrains.annotations.NotNull
        kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> fn, long interval, long lastRun) {
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
        
        public TickTask(@org.jetbrains.annotations.NotNull
        kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> fn, long interval, long lastRun) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final kotlin.jvm.functions.Function1<kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final kotlin.jvm.functions.Function1<kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object> getFn() {
            return null;
        }
        
        public final long component2() {
            return 0L;
        }
        
        public final long getInterval() {
            return 0L;
        }
        
        public final long component3() {
            return 0L;
        }
        
        public final long getLastRun() {
            return 0L;
        }
        
        public final void setLastRun(long p0) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/saltfish/assistant/service/DeviceMonitor$Companion;", "", "()V", "ACCESSIBILITY_CHECK_INTERVAL", "", "ACCESSIBILITY_LOST_THRESHOLD", "", "CHECK_VPN_INTERVAL", "HEALTH_INTERVAL_BUSY", "HEALTH_INTERVAL_IDLE", "LOW_MEM_THRESHOLD", "OVERHEAT_RECOVERY_TEMP", "", "OVERHEAT_TEMP", "REFRESH_TOKEN_INTERVAL", "TICK_CHECK_VPN", "", "TICK_REFRESH_TOKEN", "saltfish_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}