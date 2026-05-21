package com.saltfish.assistant.ui.splash;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 2, d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a0\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0007\u001a\u0006\u0010\n\u001a\u00020\u0005\u001a\u0006\u0010\u000b\u001a\u00020\u0005\u00a8\u0006\f"}, d2 = {"SplashScreen", "", "countdown", "", "isChecking", "", "oemInfo", "Lcom/saltfish/assistant/ui/splash/SplashOemInfo;", "onSkip", "Lkotlin/Function0;", "isNetworkAvailable", "isVpnOrProxyActive", "saltfish_debug"})
public final class SplashScreenKt {
    
    @androidx.compose.runtime.Composable
    public static final void SplashScreen(int countdown, boolean isChecking, @org.jetbrains.annotations.NotNull
    com.saltfish.assistant.ui.splash.SplashOemInfo oemInfo, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onSkip) {
    }
    
    /**
     * Check if VPN/proxy is active
     */
    public static final boolean isVpnOrProxyActive() {
        return false;
    }
    
    /**
     * Check if network is available
     */
    public static final boolean isNetworkAvailable() {
        return false;
    }
}