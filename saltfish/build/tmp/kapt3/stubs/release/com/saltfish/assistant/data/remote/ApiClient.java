package com.saltfish.assistant.data.remote;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00b6\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004JA\u0010^\u001a\b\u0012\u0004\u0012\u0002H`0_\"\u0004\b\u0000\u0010`2\"\u0010a\u001a\u001e\b\u0001\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H`0d0c\u0012\u0006\u0012\u0004\u0018\u00010\u00010bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010eR\u001b\u0010\u0005\u001a\u00020\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0010\u001a\u00020\u00118FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\n\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0015\u001a\u00020\u00168FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0019\u0010\n\u001a\u0004\b\u0017\u0010\u0018R\u001b\u0010\u001a\u001a\u00020\u001b8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001e\u0010\n\u001a\u0004\b\u001c\u0010\u001dR\u001b\u0010\u001f\u001a\u00020 8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b#\u0010\n\u001a\u0004\b!\u0010\"R\u001b\u0010$\u001a\u00020%8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b(\u0010\n\u001a\u0004\b&\u0010\'R\u001b\u0010)\u001a\u00020*8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b-\u0010\n\u001a\u0004\b+\u0010,R\u001b\u0010.\u001a\u00020/8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b2\u0010\n\u001a\u0004\b0\u00101R\"\u00103\u001a\n\u0012\u0004\u0012\u000205\u0018\u000104X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u001b\u0010:\u001a\u00020;8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b>\u0010\n\u001a\u0004\b<\u0010=R\u001b\u0010?\u001a\u00020@8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bC\u0010\n\u001a\u0004\bA\u0010BR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010D\u001a\u00020E8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bH\u0010\n\u001a\u0004\bF\u0010GR\u001b\u0010I\u001a\u00020J8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bM\u0010\n\u001a\u0004\bK\u0010LR#\u0010N\u001a\n P*\u0004\u0018\u00010O0O8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\bS\u0010\n\u001a\u0004\bQ\u0010RR\u001b\u0010T\u001a\u00020U8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bX\u0010\n\u001a\u0004\bV\u0010WR\u001b\u0010Y\u001a\u00020Z8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b]\u0010\n\u001a\u0004\b[\u0010\\\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006f"}, d2 = {"Lcom/saltfish/assistant/data/remote/ApiClient;", "", "prefs", "Lcom/saltfish/assistant/data/local/PreferencesManager;", "(Lcom/saltfish/assistant/data/local/PreferencesManager;)V", "account", "Lcom/saltfish/assistant/data/remote/api/AccountApi;", "getAccount", "()Lcom/saltfish/assistant/data/remote/api/AccountApi;", "account$delegate", "Lkotlin/Lazy;", "auth", "Lcom/saltfish/assistant/data/remote/api/AuthApi;", "getAuth", "()Lcom/saltfish/assistant/data/remote/api/AuthApi;", "auth$delegate", "bug", "Lcom/saltfish/assistant/data/remote/api/BugApi;", "getBug", "()Lcom/saltfish/assistant/data/remote/api/BugApi;", "bug$delegate", "cashback", "Lcom/saltfish/assistant/data/remote/api/CashbackApi;", "getCashback", "()Lcom/saltfish/assistant/data/remote/api/CashbackApi;", "cashback$delegate", "config", "Lcom/saltfish/assistant/data/remote/api/ConfigApi;", "getConfig", "()Lcom/saltfish/assistant/data/remote/api/ConfigApi;", "config$delegate", "copywriting", "Lcom/saltfish/assistant/data/remote/api/CopywritingApi;", "getCopywriting", "()Lcom/saltfish/assistant/data/remote/api/CopywritingApi;", "copywriting$delegate", "device", "Lcom/saltfish/assistant/data/remote/api/DeviceApi;", "getDevice", "()Lcom/saltfish/assistant/data/remote/api/DeviceApi;", "device$delegate", "goods", "Lcom/saltfish/assistant/data/remote/api/GoodsApi;", "getGoods", "()Lcom/saltfish/assistant/data/remote/api/GoodsApi;", "goods$delegate", "okHttpClient", "Lokhttp3/OkHttpClient;", "getOkHttpClient", "()Lokhttp3/OkHttpClient;", "okHttpClient$delegate", "onUnauthorized", "Lkotlin/Function0;", "", "getOnUnauthorized", "()Lkotlin/jvm/functions/Function0;", "setOnUnauthorized", "(Lkotlin/jvm/functions/Function0;)V", "order", "Lcom/saltfish/assistant/data/remote/api/OrderApi;", "getOrder", "()Lcom/saltfish/assistant/data/remote/api/OrderApi;", "order$delegate", "post", "Lcom/saltfish/assistant/data/remote/api/PostApi;", "getPost", "()Lcom/saltfish/assistant/data/remote/api/PostApi;", "post$delegate", "product", "Lcom/saltfish/assistant/data/remote/api/ProductApi;", "getProduct", "()Lcom/saltfish/assistant/data/remote/api/ProductApi;", "product$delegate", "receipt", "Lcom/saltfish/assistant/data/remote/api/ReceiptApi;", "getReceipt", "()Lcom/saltfish/assistant/data/remote/api/ReceiptApi;", "receipt$delegate", "retrofit", "Lretrofit2/Retrofit;", "kotlin.jvm.PlatformType", "getRetrofit", "()Lretrofit2/Retrofit;", "retrofit$delegate", "task", "Lcom/saltfish/assistant/data/remote/api/TaskApi;", "getTask", "()Lcom/saltfish/assistant/data/remote/api/TaskApi;", "task$delegate", "user", "Lcom/saltfish/assistant/data/remote/api/UserApi;", "getUser", "()Lcom/saltfish/assistant/data/remote/api/UserApi;", "user$delegate", "safeApiCall", "Lcom/saltfish/assistant/data/remote/ApiResult;", "T", "call", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "Lcom/saltfish/assistant/data/remote/ApiResponse;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saltfish_release"})
public final class ApiClient {
    private final com.saltfish.assistant.data.local.PreferencesManager prefs = null;
    @org.jetbrains.annotations.Nullable
    private kotlin.jvm.functions.Function0<kotlin.Unit> onUnauthorized;
    private final kotlin.Lazy okHttpClient$delegate = null;
    private final kotlin.Lazy retrofit$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy auth$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy user$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy device$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy account$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy goods$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy post$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy order$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy product$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy cashback$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy receipt$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy copywriting$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy task$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy config$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy bug$delegate = null;
    
    public ApiClient(@org.jetbrains.annotations.NotNull
    com.saltfish.assistant.data.local.PreferencesManager prefs) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnUnauthorized() {
        return null;
    }
    
    public final void setOnUnauthorized(@org.jetbrains.annotations.Nullable
    kotlin.jvm.functions.Function0<kotlin.Unit> p0) {
    }
    
    private final okhttp3.OkHttpClient getOkHttpClient() {
        return null;
    }
    
    private final retrofit2.Retrofit getRetrofit() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.data.remote.api.AuthApi getAuth() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.data.remote.api.UserApi getUser() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.data.remote.api.DeviceApi getDevice() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.data.remote.api.AccountApi getAccount() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.data.remote.api.GoodsApi getGoods() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.data.remote.api.PostApi getPost() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.data.remote.api.OrderApi getOrder() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.data.remote.api.ProductApi getProduct() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.data.remote.api.CashbackApi getCashback() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.data.remote.api.ReceiptApi getReceipt() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.data.remote.api.CopywritingApi getCopywriting() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.data.remote.api.TaskApi getTask() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.data.remote.api.ConfigApi getConfig() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.data.remote.api.BugApi getBug() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final <T extends java.lang.Object>java.lang.Object safeApiCall(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super com.saltfish.assistant.data.remote.ApiResponse<T>>, ? extends java.lang.Object> call, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.saltfish.assistant.data.remote.ApiResult<? extends T>> continuation) {
        return null;
    }
}