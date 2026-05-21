package com.saltfish.assistant.data.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ#\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J\u0011\u0010\u0011\u001a\u00020\u0012H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013J#\u0010\u0014\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000f\u001a\u00020\fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0018"}, d2 = {"Lcom/saltfish/assistant/data/repository/AccountRepository;", "", "apiClient", "Lcom/saltfish/assistant/data/remote/ApiClient;", "prefs", "Lcom/saltfish/assistant/data/local/PreferencesManager;", "deviceRepository", "Lcom/saltfish/assistant/data/repository/DeviceRepository;", "(Lcom/saltfish/assistant/data/remote/ApiClient;Lcom/saltfish/assistant/data/local/PreferencesManager;Lcom/saltfish/assistant/data/repository/DeviceRepository;)V", "gson", "Lcom/google/gson/Gson;", "addAccountInfo", "Lcom/saltfish/assistant/domain/entity/SaltfishAccountEntity;", "platform", "", "info", "(Ljava/lang/String;Lcom/saltfish/assistant/domain/entity/SaltfishAccountEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refreshToken", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateAccountInfo", "id", "", "(JLcom/saltfish/assistant/domain/entity/SaltfishAccountEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saltfish_debug"})
public final class AccountRepository {
    private final com.saltfish.assistant.data.remote.ApiClient apiClient = null;
    private final com.saltfish.assistant.data.local.PreferencesManager prefs = null;
    private final com.saltfish.assistant.data.repository.DeviceRepository deviceRepository = null;
    private final com.google.gson.Gson gson = null;
    
    public AccountRepository(@org.jetbrains.annotations.NotNull
    com.saltfish.assistant.data.remote.ApiClient apiClient, @org.jetbrains.annotations.NotNull
    com.saltfish.assistant.data.local.PreferencesManager prefs, @org.jetbrains.annotations.NotNull
    com.saltfish.assistant.data.repository.DeviceRepository deviceRepository) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object addAccountInfo(@org.jetbrains.annotations.NotNull
    java.lang.String platform, @org.jetbrains.annotations.NotNull
    com.saltfish.assistant.domain.entity.SaltfishAccountEntity info, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.saltfish.assistant.domain.entity.SaltfishAccountEntity> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object updateAccountInfo(long id, @org.jetbrains.annotations.NotNull
    com.saltfish.assistant.domain.entity.SaltfishAccountEntity info, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.saltfish.assistant.domain.entity.SaltfishAccountEntity> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object refreshToken(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
}