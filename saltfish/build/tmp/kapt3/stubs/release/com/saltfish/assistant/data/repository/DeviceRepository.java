package com.saltfish.assistant.data.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\u000b\u001a\u00020\fJ\u0013\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u0011\u0010\u0010\u001a\u00020\u0011H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u001b\u0010\u0012\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0013\u001a\u00020\u0014H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J\u0019\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J\u0011\u0010\u0017\u001a\u00020\u0011H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0018"}, d2 = {"Lcom/saltfish/assistant/data/repository/DeviceRepository;", "", "apiClient", "Lcom/saltfish/assistant/data/remote/ApiClient;", "prefs", "Lcom/saltfish/assistant/data/local/PreferencesManager;", "deviceInfoCollector", "Lcom/saltfish/assistant/data/local/DeviceInfoCollector;", "(Lcom/saltfish/assistant/data/remote/ApiClient;Lcom/saltfish/assistant/data/local/PreferencesManager;Lcom/saltfish/assistant/data/local/DeviceInfoCollector;)V", "gson", "Lcom/google/gson/Gson;", "collectDeviceInfo", "Lcom/saltfish/assistant/domain/model/DeviceInfo;", "getDeviceInfo", "Lcom/saltfish/assistant/domain/entity/SaltfishDeviceEntity;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "registerDevice", "", "registerWithSecret", "secret", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "renewDevice", "sendHeartbeat", "saltfish_release"})
public final class DeviceRepository {
    private final com.saltfish.assistant.data.remote.ApiClient apiClient = null;
    private final com.saltfish.assistant.data.local.PreferencesManager prefs = null;
    private final com.saltfish.assistant.data.local.DeviceInfoCollector deviceInfoCollector = null;
    private final com.google.gson.Gson gson = null;
    
    public DeviceRepository(@org.jetbrains.annotations.NotNull
    com.saltfish.assistant.data.remote.ApiClient apiClient, @org.jetbrains.annotations.NotNull
    com.saltfish.assistant.data.local.PreferencesManager prefs, @org.jetbrains.annotations.NotNull
    com.saltfish.assistant.data.local.DeviceInfoCollector deviceInfoCollector) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.domain.model.DeviceInfo collectDeviceInfo() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object registerDevice(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object registerWithSecret(@org.jetbrains.annotations.NotNull
    java.lang.String secret, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.saltfish.assistant.domain.entity.SaltfishDeviceEntity> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getDeviceInfo(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.saltfish.assistant.domain.entity.SaltfishDeviceEntity> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object renewDevice(@org.jetbrains.annotations.NotNull
    java.lang.String secret, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object sendHeartbeat(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
}