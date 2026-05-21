package com.saltfish.assistant.engine;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\u0002\u0010\tJ\u001e\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u0019J\u0006\u0010\u001c\u001a\u00020\bJ\u0006\u0010\u001d\u001a\u00020\bJ\u0019\u0010\u001e\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020 H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010!J\u0010\u0010\"\u001a\u00020\b2\u0006\u0010#\u001a\u00020 H\u0002J\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0019H\u0002R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\f0\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\'"}, d2 = {"Lcom/saltfish/assistant/engine/UpgradeManager;", "", "context", "Landroid/content/Context;", "prefs", "Lcom/saltfish/assistant/data/local/PreferencesManager;", "onRestart", "Lkotlin/Function0;", "", "(Landroid/content/Context;Lcom/saltfish/assistant/data/local/PreferencesManager;Lkotlin/jvm/functions/Function0;)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/saltfish/assistant/engine/UpgradeState;", "gson", "Lcom/google/gson/Gson;", "httpClient", "Lokhttp3/OkHttpClient;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "checkUpdate", "checkUrl", "", "currentAppVersion", "currentScriptVersion", "dismiss", "downloadAndInstall", "installAdapter", "zipFile", "Ljava/io/File;", "(Ljava/io/File;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "installApk", "file", "parseVersionNumber", "", "version", "saltfish_debug"})
public final class UpgradeManager {
    private final android.content.Context context = null;
    private final com.saltfish.assistant.data.local.PreferencesManager prefs = null;
    private final kotlin.jvm.functions.Function0<kotlin.Unit> onRestart = null;
    private final kotlinx.coroutines.CoroutineScope scope = null;
    private final com.google.gson.Gson gson = null;
    private final okhttp3.OkHttpClient httpClient = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.saltfish.assistant.engine.UpgradeState> _state = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.saltfish.assistant.engine.UpgradeState> state = null;
    
    public UpgradeManager(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    com.saltfish.assistant.data.local.PreferencesManager prefs, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onRestart) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.saltfish.assistant.engine.UpgradeState> getState() {
        return null;
    }
    
    public final void checkUpdate(@org.jetbrains.annotations.NotNull
    java.lang.String checkUrl, @org.jetbrains.annotations.NotNull
    java.lang.String currentAppVersion, @org.jetbrains.annotations.NotNull
    java.lang.String currentScriptVersion) {
    }
    
    public final void downloadAndInstall() {
    }
    
    private final void installApk(java.io.File file) {
    }
    
    private final java.lang.Object installAdapter(java.io.File zipFile, kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    public final void dismiss() {
    }
    
    private final long parseVersionNumber(java.lang.String version) {
        return 0L;
    }
}