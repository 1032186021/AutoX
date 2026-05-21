package com.saltfish.assistant.data.remote.api;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0004H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J!\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\b\b\u0001\u0010\t\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\'\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\r0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0004H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J!\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0004H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000f"}, d2 = {"Lcom/saltfish/assistant/data/remote/api/CashbackApi;", "", "add", "Lcom/saltfish/assistant/data/remote/ApiResponse;", "Lcom/google/gson/JsonObject;", "body", "(Lcom/google/gson/JsonObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getInfo", "Lcom/saltfish/assistant/domain/entity/SaltfishOrderCashbackEntity;", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPage", "Lcom/saltfish/assistant/domain/entity/PaginationResponse;", "update", "saltfish_debug"})
public abstract interface CashbackApi {
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "/admin/aios-saltfish/cashback/info")
    public abstract java.lang.Object getInfo(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Query(value = "id")
    java.lang.String id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.saltfish.assistant.data.remote.ApiResponse<com.saltfish.assistant.domain.entity.SaltfishOrderCashbackEntity>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "/admin/aios-saltfish/cashback/add")
    public abstract java.lang.Object add(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.google.gson.JsonObject body, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.saltfish.assistant.data.remote.ApiResponse<com.google.gson.JsonObject>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "/admin/aios-saltfish/cashback/update")
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.google.gson.JsonObject body, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.saltfish.assistant.data.remote.ApiResponse<com.google.gson.JsonObject>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "/admin/aios-saltfish/cashback/page")
    public abstract java.lang.Object getPage(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.google.gson.JsonObject body, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.saltfish.assistant.data.remote.ApiResponse<com.saltfish.assistant.domain.entity.PaginationResponse<com.google.gson.JsonObject>>> continuation);
}