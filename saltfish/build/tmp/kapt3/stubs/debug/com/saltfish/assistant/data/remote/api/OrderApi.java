package com.saltfish.assistant.data.remote.api;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J!\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\b\b\u0001\u0010\t\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ!\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\r\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ!\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000f"}, d2 = {"Lcom/saltfish/assistant/data/remote/api/OrderApi;", "", "add", "Lcom/saltfish/assistant/data/remote/ApiResponse;", "Lcom/saltfish/assistant/domain/entity/SaltfishOrderEntity;", "body", "Lcom/google/gson/JsonObject;", "(Lcom/google/gson/JsonObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "ids", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getInfo", "id", "update", "saltfish_debug"})
public abstract interface OrderApi {
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "/admin/aios-saltfish/order/add")
    public abstract java.lang.Object add(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.google.gson.JsonObject body, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.saltfish.assistant.data.remote.ApiResponse<com.saltfish.assistant.domain.entity.SaltfishOrderEntity>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "/admin/aios-saltfish/order/update")
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.google.gson.JsonObject body, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.saltfish.assistant.data.remote.ApiResponse<com.saltfish.assistant.domain.entity.SaltfishOrderEntity>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "/admin/aios-saltfish/order/info")
    public abstract java.lang.Object getInfo(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Query(value = "id")
    java.lang.String id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.saltfish.assistant.data.remote.ApiResponse<com.saltfish.assistant.domain.entity.SaltfishOrderEntity>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "/admin/aios-saltfish/order/delete")
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Query(value = "ids")
    java.lang.String ids, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.saltfish.assistant.data.remote.ApiResponse<com.google.gson.JsonObject>> continuation);
}