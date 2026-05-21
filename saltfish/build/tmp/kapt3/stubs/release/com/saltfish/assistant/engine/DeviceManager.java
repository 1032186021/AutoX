package com.saltfish.assistant.engine;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\n\u0018\u0000 A2\u00020\u0001:\u0001AB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010)\u001a\u00020\bJ\b\u0010*\u001a\u00020\u0015H\u0002J\u000e\u0010+\u001a\u00020\u00152\u0006\u0010,\u001a\u00020-JB\u0010.\u001a\u00020\u00152:\u0010/\u001a6\u0012\u0013\u0012\u00110\b\u00a2\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\b\u00a2\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00150\u0010j\u0002`0J\u0017\u00101\u001a\u0004\u0018\u00010 2\u0006\u00102\u001a\u00020-H\u0002\u00a2\u0006\u0002\u00103J\u001b\u00104\u001a\u0004\u0018\u00010\u00062\u0006\u00105\u001a\u00020-H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00106J\u0019\u00107\u001a\u0002082\u0006\u00105\u001a\u00020-H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00106J\u0010\u00109\u001a\u00020\u00152\u0006\u0010:\u001a\u00020\bH\u0002J\u0006\u0010;\u001a\u00020\u0015J\b\u0010<\u001a\u00020\u0015H\u0002J\u0006\u0010=\u001a\u00020\u0015J\b\u0010>\u001a\u00020\u0015H\u0002J\u0011\u0010?\u001a\u00020\u0015H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010@R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0013\u0010\t\u001a\u0004\u0018\u00010\u00068F\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000RD\u0010\u000e\u001a8\u00124\u00122\u0012\u0013\u0012\u00110\b\u00a2\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\b\u00a2\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00150\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\"\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0017X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\"\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0017X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0019\"\u0004\b\u001e\u0010\u001bR\u0011\u0010\u001f\u001a\u00020 8F\u00a2\u0006\u0006\u001a\u0004\b!\u0010\"R\u000e\u0010#\u001a\u00020$X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010%\u001a\u00020\b8F\u00a2\u0006\u0006\u001a\u0004\b&\u0010\'R\u0010\u0010(\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006B"}, d2 = {"Lcom/saltfish/assistant/engine/DeviceManager;", "", "app", "Lcom/saltfish/assistant/SaltfishApp;", "(Lcom/saltfish/assistant/SaltfishApp;)V", "_device", "Lcom/saltfish/assistant/domain/entity/SaltfishDeviceEntity;", "_state", "Lcom/saltfish/assistant/engine/DeviceState;", "device", "getDevice", "()Lcom/saltfish/assistant/domain/entity/SaltfishDeviceEntity;", "expiryJob", "Lkotlinx/coroutines/Job;", "listeners", "", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "from", "to", "", "onActivationRequired", "Lkotlin/Function0;", "getOnActivationRequired", "()Lkotlin/jvm/functions/Function0;", "setOnActivationRequired", "(Lkotlin/jvm/functions/Function0;)V", "onActivationResolved", "getOnActivationResolved", "setOnActivationResolved", "remainingDays", "", "getRemainingDays", "()J", "scope", "Lkotlinx/coroutines/CoroutineScope;", "state", "getState", "()Lcom/saltfish/assistant/engine/DeviceState;", "updateJob", "checkDevice", "checkExpiry", "handleServerCommand", "cmd", "", "onStateChange", "cb", "Lcom/saltfish/assistant/engine/DeviceStateCallback;", "parseExpiryTime", "time", "(Ljava/lang/String;)Ljava/lang/Long;", "register", "secret", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "renew", "", "setState", "newState", "start", "startTimers", "stop", "stopTimers", "sync", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "saltfish_release"})
public final class DeviceManager {
    private final com.saltfish.assistant.SaltfishApp app = null;
    @org.jetbrains.annotations.NotNull
    public static final com.saltfish.assistant.engine.DeviceManager.Companion Companion = null;
    private static final long EXPIRY_CHECK_INTERVAL = 60000L;
    private static final long DEVICE_UPDATE_INTERVAL = 60000L;
    private static final int EXPIRY_WARN_DAYS = 3;
    private static final java.text.SimpleDateFormat EXPIRY_FORMAT = null;
    private final kotlinx.coroutines.CoroutineScope scope = null;
    private kotlinx.coroutines.Job expiryJob;
    private kotlinx.coroutines.Job updateJob;
    private com.saltfish.assistant.engine.DeviceState _state = com.saltfish.assistant.engine.DeviceState.Idle;
    private com.saltfish.assistant.domain.entity.SaltfishDeviceEntity _device;
    private final java.util.List<kotlin.jvm.functions.Function2<com.saltfish.assistant.engine.DeviceState, com.saltfish.assistant.engine.DeviceState, kotlin.Unit>> listeners = null;
    @org.jetbrains.annotations.Nullable
    private kotlin.jvm.functions.Function0<kotlin.Unit> onActivationRequired;
    @org.jetbrains.annotations.Nullable
    private kotlin.jvm.functions.Function0<kotlin.Unit> onActivationResolved;
    
    public DeviceManager(@org.jetbrains.annotations.NotNull
    com.saltfish.assistant.SaltfishApp app) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.engine.DeviceState getState() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.saltfish.assistant.domain.entity.SaltfishDeviceEntity getDevice() {
        return null;
    }
    
    public final long getRemainingDays() {
        return 0L;
    }
    
    private final void setState(com.saltfish.assistant.engine.DeviceState newState) {
    }
    
    public final void onStateChange(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function2<? super com.saltfish.assistant.engine.DeviceState, ? super com.saltfish.assistant.engine.DeviceState, kotlin.Unit> cb) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object register(@org.jetbrains.annotations.NotNull
    java.lang.String secret, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.saltfish.assistant.domain.entity.SaltfishDeviceEntity> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object renew(@org.jetbrains.annotations.NotNull
    java.lang.String secret, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object sync(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.engine.DeviceState checkDevice() {
        return null;
    }
    
    private final void checkExpiry() {
    }
    
    private final java.lang.Long parseExpiryTime(java.lang.String time) {
        return null;
    }
    
    private final void startTimers() {
    }
    
    private final void stopTimers() {
    }
    
    public final void start() {
    }
    
    public final void stop() {
    }
    
    @org.jetbrains.annotations.Nullable
    public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnActivationRequired() {
        return null;
    }
    
    public final void setOnActivationRequired(@org.jetbrains.annotations.Nullable
    kotlin.jvm.functions.Function0<kotlin.Unit> p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnActivationResolved() {
        return null;
    }
    
    public final void setOnActivationResolved(@org.jetbrains.annotations.Nullable
    kotlin.jvm.functions.Function0<kotlin.Unit> p0) {
    }
    
    public final void handleServerCommand(@org.jetbrains.annotations.NotNull
    java.lang.String cmd) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/saltfish/assistant/engine/DeviceManager$Companion;", "", "()V", "DEVICE_UPDATE_INTERVAL", "", "EXPIRY_CHECK_INTERVAL", "EXPIRY_FORMAT", "Ljava/text/SimpleDateFormat;", "EXPIRY_WARN_DAYS", "", "saltfish_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}