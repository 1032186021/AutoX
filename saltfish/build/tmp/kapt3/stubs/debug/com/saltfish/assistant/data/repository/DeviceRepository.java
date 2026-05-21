package com.saltfish.assistant.data.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\u000b\u001a\u00020\fJ\u0018\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u0002J\u001f\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J$\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u00172\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0002J\u001b\u0010\u0018\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0019\u001a\u00020\u0014H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J\u001b\u0010\u001a\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0019\u001a\u00020\u0014H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J\u0011\u0010\u001b\u001a\u00020\u001cH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001dJ\u001d\u0010\u001e\u001a\u00020\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\"R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006#"}, d2 = {"Lcom/saltfish/assistant/data/repository/DeviceRepository;", "", "apiClient", "Lcom/saltfish/assistant/data/remote/ApiClient;", "prefs", "Lcom/saltfish/assistant/data/local/PreferencesManager;", "deviceInfoCollector", "Lcom/saltfish/assistant/data/local/DeviceInfoCollector;", "(Lcom/saltfish/assistant/data/remote/ApiClient;Lcom/saltfish/assistant/data/local/PreferencesManager;Lcom/saltfish/assistant/data/local/DeviceInfoCollector;)V", "gson", "Lcom/google/gson/Gson;", "collectDeviceInfo", "Lcom/saltfish/assistant/domain/model/DeviceInfo;", "extractAccountsList", "", "Lcom/saltfish/assistant/domain/entity/SaltfishAccountEntity;", "accounts", "getInfo", "Lcom/saltfish/assistant/domain/entity/SaltfishDeviceEntity;", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "groupAccountsByPlatform", "", "register", "secret", "renew", "sendHeartbeat", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateInfo", "", "data", "Lcom/google/gson/JsonObject;", "(Lcom/google/gson/JsonObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saltfish_debug"})
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
    public final java.lang.Object register(@org.jetbrains.annotations.NotNull
    java.lang.String secret, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.saltfish.assistant.domain.entity.SaltfishDeviceEntity> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object renew(@org.jetbrains.annotations.NotNull
    java.lang.String secret, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.saltfish.assistant.domain.entity.SaltfishDeviceEntity> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getInfo(@org.jetbrains.annotations.Nullable
    java.lang.String id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.saltfish.assistant.domain.entity.SaltfishDeviceEntity> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object updateInfo(@org.jetbrains.annotations.Nullable
    com.google.gson.JsonObject data, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object sendHeartbeat(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
    
    /**
     * 将 accounts (Any?) 转为 List<SaltfishAccountEntity>
     */
    private final java.util.List<com.saltfish.assistant.domain.entity.SaltfishAccountEntity> extractAccountsList(java.lang.Object accounts) {
        return null;
    }
    
    /**
     * 按 platform 分组，并按 updateTime 升序排列（晚更新的排后面）
     */
    private final java.util.Map<java.lang.String, com.saltfish.assistant.domain.entity.SaltfishAccountEntity> groupAccountsByPlatform(java.util.List<com.saltfish.assistant.domain.entity.SaltfishAccountEntity> accounts) {
        return null;
    }
}