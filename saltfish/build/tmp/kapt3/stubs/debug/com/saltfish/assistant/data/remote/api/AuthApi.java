package com.saltfish.assistant.data.remote.api;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J5\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00062\b\b\u0003\u0010\u0007\u001a\u00020\b2\b\b\u0003\u0010\t\u001a\u00020\bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ!\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00032\b\b\u0001\u0010\r\u001a\u00020\u000eH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ!\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\f0\u00032\b\b\u0001\u0010\r\u001a\u00020\u000eH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ!\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\u00032\b\b\u0001\u0010\u0012\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0014"}, d2 = {"Lcom/saltfish/assistant/data/remote/api/AuthApi;", "", "getCaptcha", "Lcom/saltfish/assistant/data/remote/ApiResponse;", "Lcom/saltfish/assistant/domain/entity/CaptchaInfo;", "type", "", "height", "", "width", "(Ljava/lang/String;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "login", "Lcom/saltfish/assistant/domain/entity/UserAuthorization;", "body", "Lcom/google/gson/JsonObject;", "(Lcom/google/gson/JsonObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loginWithCaptcha", "refreshToken", "token", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saltfish_debug"})
public abstract interface AuthApi {
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "/admin/aios-saltfish/open/login")
    public abstract java.lang.Object login(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.google.gson.JsonObject body, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.saltfish.assistant.data.remote.ApiResponse<com.saltfish.assistant.domain.entity.UserAuthorization>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "/admin/base/open/login")
    public abstract java.lang.Object loginWithCaptcha(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.google.gson.JsonObject body, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.saltfish.assistant.data.remote.ApiResponse<com.saltfish.assistant.domain.entity.UserAuthorization>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "/admin/base/open/refreshToken")
    public abstract java.lang.Object refreshToken(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Query(value = "refreshToken")
    java.lang.String token, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.saltfish.assistant.data.remote.ApiResponse<com.saltfish.assistant.domain.entity.UserAuthorization>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "/admin/base/open/captcha")
    public abstract java.lang.Object getCaptcha(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Query(value = "type")
    java.lang.String type, @retrofit2.http.Query(value = "height")
    int height, @retrofit2.http.Query(value = "width")
    int width, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.saltfish.assistant.data.remote.ApiResponse<com.saltfish.assistant.domain.entity.CaptchaInfo>> continuation);
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 3)
    public final class DefaultImpls {
    }
}