package com.saltfish.assistant.data.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J\u0011\u0010\u0012\u001a\u00020\nH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J!\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0017"}, d2 = {"Lcom/saltfish/assistant/data/repository/AccountRepository;", "", "apiClient", "Lcom/saltfish/assistant/data/remote/ApiClient;", "prefs", "Lcom/saltfish/assistant/data/local/PreferencesManager;", "(Lcom/saltfish/assistant/data/remote/ApiClient;Lcom/saltfish/assistant/data/local/PreferencesManager;)V", "gson", "Lcom/google/gson/Gson;", "addAccount", "", "account", "Lcom/saltfish/assistant/domain/model/AccountInfo;", "(Lcom/saltfish/assistant/domain/model/AccountInfo;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAccounts", "", "Lcom/saltfish/assistant/domain/entity/SaltfishAccountEntity;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refreshToken", "updateAccount", "id", "", "(JLcom/saltfish/assistant/domain/model/AccountInfo;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saltfish_release"})
public final class AccountRepository {
    private final com.saltfish.assistant.data.remote.ApiClient apiClient = null;
    private final com.saltfish.assistant.data.local.PreferencesManager prefs = null;
    private final com.google.gson.Gson gson = null;
    
    public AccountRepository(@org.jetbrains.annotations.NotNull
    com.saltfish.assistant.data.remote.ApiClient apiClient, @org.jetbrains.annotations.NotNull
    com.saltfish.assistant.data.local.PreferencesManager prefs) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object addAccount(@org.jetbrains.annotations.NotNull
    com.saltfish.assistant.domain.model.AccountInfo account, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object updateAccount(long id, @org.jetbrains.annotations.NotNull
    com.saltfish.assistant.domain.model.AccountInfo account, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getAccounts(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.saltfish.assistant.domain.entity.SaltfishAccountEntity>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object refreshToken(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
}