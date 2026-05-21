package com.saltfish.assistant.data.remote.api;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J+\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ!\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0003\u0010\r\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ!\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0010\u001a\u00020\u0004H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J!\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\b\b\u0001\u0010\u0010\u001a\u00020\u0013H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0015"}, d2 = {"Lcom/saltfish/assistant/data/remote/api/ConfigApi;", "", "checkUpgrade", "Lcom/saltfish/assistant/data/remote/ApiResponse;", "Lcom/google/gson/JsonObject;", "scriptVersion", "", "packageName", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAppLatest", "Lcom/saltfish/assistant/domain/entity/SaltfishAppEntity;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getConfig", "id", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendLog", "body", "(Lcom/google/gson/JsonObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadFile", "Lokhttp3/MultipartBody;", "(Lokhttp3/MultipartBody;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saltfish_release"})
public abstract interface ConfigApi {
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "/admin/aios-saltfish/config/info")
    public abstract java.lang.Object getConfig(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Query(value = "id")
    java.lang.String id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.saltfish.assistant.data.remote.ApiResponse<com.google.gson.JsonObject>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "/admin/aios-saltfish/upgrade/check")
    public abstract java.lang.Object checkUpgrade(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Query(value = "scriptVersion")
    java.lang.String scriptVersion, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Query(value = "packageName")
    java.lang.String packageName, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.saltfish.assistant.data.remote.ApiResponse<com.google.gson.JsonObject>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "/app/saltfish/comm/applatest")
    public abstract java.lang.Object getAppLatest(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.saltfish.assistant.data.remote.ApiResponse<com.saltfish.assistant.domain.entity.SaltfishAppEntity>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "/admin/aios-saltfish/log/add")
    public abstract java.lang.Object sendLog(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.google.gson.JsonObject body, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.saltfish.assistant.data.remote.ApiResponse<com.google.gson.JsonObject>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "/admin/base/comm/upload")
    public abstract java.lang.Object uploadFile(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    okhttp3.MultipartBody body, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.saltfish.assistant.data.remote.ApiResponse<java.lang.String>> continuation);
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 3)
    public final class DefaultImpls {
    }
}