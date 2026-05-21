package com.saltfish.assistant.engine;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0012\u001a\u00020\bJ\u0006\u0010\u0013\u001a\u00020\bJ\u0011\u0010\u0014\u001a\u00020\u0015H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016J\b\u0010\u0017\u001a\u00020\u0015H\u0002J!\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001bR \u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00070\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000e8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001c"}, d2 = {"Lcom/saltfish/assistant/engine/ScriptManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_adapterVersions", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "", "adapterVersions", "Lkotlinx/coroutines/flow/StateFlow;", "getAdapterVersions", "()Lkotlinx/coroutines/flow/StateFlow;", "adaptersDir", "Ljava/io/File;", "getAdaptersDir", "()Ljava/io/File;", "getAdapterScript", "platform", "getBridgeScript", "initialize", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "scanLocalVersions", "updateFromCloud", "", "downloadUrl", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saltfish_release"})
public final class ScriptManager {
    private final android.content.Context context = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.Map<java.lang.String, java.lang.String>> _adapterVersions = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.Map<java.lang.String, java.lang.String>> adapterVersions = null;
    
    public ScriptManager(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.Map<java.lang.String, java.lang.String>> getAdapterVersions() {
        return null;
    }
    
    private final java.io.File getAdaptersDir() {
        return null;
    }
    
    /**
     * Initialize: extract built-in adapters from assets to filesDir.
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object initialize(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final void scanLocalVersions() {
    }
    
    /**
     * Get the adapter script content for a specific platform.
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getAdapterScript(@org.jetbrains.annotations.NotNull
    java.lang.String platform) {
        return null;
    }
    
    /**
     * Get bridge injection script (injected into each adapter scope).
     */
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getBridgeScript() {
        return null;
    }
    
    /**
     * Download ZIP update for an adapter from cloud.
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object updateFromCloud(@org.jetbrains.annotations.NotNull
    java.lang.String downloadUrl, @org.jetbrains.annotations.NotNull
    java.lang.String platform, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
}