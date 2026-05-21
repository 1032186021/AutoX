package com.saltfish.assistant.data.remote.api;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0005J\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0007"}, d2 = {"Lcom/saltfish/assistant/data/remote/api/UserApi;", "", "getPermissions", "Lcom/saltfish/assistant/data/remote/ApiResponse;", "Lcom/google/gson/JsonObject;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserInfo", "saltfish_release"})
public abstract interface UserApi {
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "/admin/base/comm/person")
    public abstract java.lang.Object getUserInfo(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.saltfish.assistant.data.remote.ApiResponse<com.google.gson.JsonObject>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "/admin/base/comm/permmenu")
    public abstract java.lang.Object getPermissions(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.saltfish.assistant.data.remote.ApiResponse<com.google.gson.JsonObject>> continuation);
}