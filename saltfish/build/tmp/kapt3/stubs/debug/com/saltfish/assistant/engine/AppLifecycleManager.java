package com.saltfish.assistant.engine;

import java.lang.System;

/**
 * Centralized app lifecycle state machine.
 * Mirrors the TS AppLifecycle design:
 *  SPLASH → LOGIN → startGuidedFlow → GUIDE → PERMISSION_GUIDE → MAIN
 *
 * The MAIN gate checks device activation, then guide/permission/hone.
 * Engine startup (DeviceManager, etc.) converges in [onMainEntered].
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0011\u0010\u0005\u001a\u00020\u0006H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J\u0006\u0010\b\u001a\u00020\u0006J\u0011\u0010\t\u001a\u00020\u0006H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\f\u001a\u00020\u0006J\b\u0010\r\u001a\u00020\u0006H\u0002J\u001b\u0010\u000e\u001a\u00020\u00062\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J\u0011\u0010\u0012\u001a\u00020\u0006H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0013"}, d2 = {"Lcom/saltfish/assistant/engine/AppLifecycleManager;", "", "app", "Lcom/saltfish/assistant/SaltfishApp;", "(Lcom/saltfish/assistant/SaltfishApp;)V", "onDeviceActivated", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onGuideDone", "onLoginSuccess", "onMainEntered", "", "onPermissionsDone", "postDeviceGateRoute", "resolveStartRoute", "splashLoggedIn", "", "(Ljava/lang/Boolean;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startGuidedFlow", "saltfish_debug"})
public final class AppLifecycleManager {
    private final com.saltfish.assistant.SaltfishApp app = null;
    
    public AppLifecycleManager(@org.jetbrains.annotations.NotNull
    com.saltfish.assistant.SaltfishApp app) {
        super();
    }
    
    /**
     * Resolves the initial route. May suspend to sync device info
     * when the user is logged in but local device data is missing.
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object resolveStartRoute(@org.jetbrains.annotations.Nullable
    java.lang.Boolean splashLoggedIn, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.String> continuation) {
        return null;
    }
    
    /**
     * Called after login succeeds. Syncs device info if missing,
     * then runs the device-activation gate.
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object onLoginSuccess(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.String> continuation) {
        return null;
    }
    
    /**
     * Called after device activation completes. Re-checks device state
     * (activation may have failed), then proceeds to guide/permissions/home.
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object onDeviceActivated(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.String> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String onGuideDone() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String onPermissionsDone() {
        return null;
    }
    
    /**
     * Called when Home screen first renders. Centralizes engine startup.
     */
    public final void onMainEntered() {
    }
    
    /**
     * TS startGuidedFlow: if logged in but no deviceId/device,
     * sync from server before continuing.
     */
    private final java.lang.Object startGuidedFlow(kotlin.coroutines.Continuation<? super java.lang.String> continuation) {
        return null;
    }
    
    /**
     * TS continueGuidedFlow + goMain gate: device → guide → permissions → home.
     */
    private final java.lang.String postDeviceGateRoute() {
        return null;
    }
}