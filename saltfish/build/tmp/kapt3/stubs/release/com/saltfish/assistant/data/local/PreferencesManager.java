package com.saltfish.assistant.data.local;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u00109\u001a\u00020:J\u0006\u0010;\u001a\u00020\bJ\u000e\u0010<\u001a\u00020:2\u0006\u0010=\u001a\u00020>J\u0006\u0010?\u001a\u00020:R\u0016\u0010\u0005\u001a\n \u0006*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R$\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000f8F\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R(\u0010\u0012\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0007\u001a\u0004\u0018\u00010\u000f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0013\u0010\u0011\"\u0004\b\u0014\u0010\u0015R(\u0010\u0016\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0007\u001a\u0004\u0018\u00010\u000f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0017\u0010\u0011\"\u0004\b\u0018\u0010\u0015R$\u0010\u0019\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u001a\u0010\u000b\"\u0004\b\u001b\u0010\rR$\u0010\u001c\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u001c\u0010\u000b\"\u0004\b\u001d\u0010\rR(\u0010\u001e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0007\u001a\u0004\u0018\u00010\u000f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u001f\u0010\u0011\"\u0004\b \u0010\u0015R$\u0010!\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u000f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\"\u0010\u0011\"\u0004\b#\u0010\u0015R\u000e\u0010$\u001a\u00020%X\u0082\u0004\u00a2\u0006\u0002\n\u0000R(\u0010&\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0007\u001a\u0004\u0018\u00010\u000f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\'\u0010\u0011\"\u0004\b(\u0010\u0015R$\u0010)\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b*\u0010\u000b\"\u0004\b+\u0010\rR(\u0010,\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0007\u001a\u0004\u0018\u00010\u000f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b-\u0010\u0011\"\u0004\b.\u0010\u0015R(\u0010/\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0007\u001a\u0004\u0018\u00010\u000f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b0\u0010\u0011\"\u0004\b1\u0010\u0015R$\u00102\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u000f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b3\u0010\u0011\"\u0004\b4\u0010\u0015R\u0011\u00105\u001a\u00020\u000f8F\u00a2\u0006\u0006\u001a\u0004\b6\u0010\u0011R\u0011\u00107\u001a\u00020\u000f8F\u00a2\u0006\u0006\u001a\u0004\b8\u0010\u0011\u00a8\u0006@"}, d2 = {"Lcom/saltfish/assistant/data/local/PreferencesManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "appContext", "kotlin.jvm.PlatformType", "value", "", "autoUpgrade", "getAutoUpgrade", "()Z", "setAutoUpgrade", "(Z)V", "baseUrl", "", "getBaseUrl", "()Ljava/lang/String;", "deviceId", "getDeviceId", "setDeviceId", "(Ljava/lang/String;)V", "deviceKey", "getDeviceKey", "setDeviceKey", "guideShown", "getGuideShown", "setGuideShown", "isFirstLaunch", "setFirstLaunch", "nickName", "getNickName", "setNickName", "password", "getPassword", "setPassword", "prefs", "Landroid/content/SharedPreferences;", "refreshToken", "getRefreshToken", "setRefreshToken", "rememberAccount", "getRememberAccount", "setRememberAccount", "token", "getToken", "setToken", "userInfoJson", "getUserInfoJson", "setUserInfoJson", "username", "getUsername", "setUsername", "uuid", "getUuid", "wsUrl", "getWsUrl", "clearAll", "", "isLoggedIn", "logCrash", "ex", "", "logout", "saltfish_release"})
public final class PreferencesManager {
    private final android.content.Context appContext = null;
    private final android.content.SharedPreferences prefs = null;
    
    public PreferencesManager(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getBaseUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getToken() {
        return null;
    }
    
    public final void setToken(@org.jetbrains.annotations.Nullable
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getUuid() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getDeviceId() {
        return null;
    }
    
    public final void setDeviceId(@org.jetbrains.annotations.Nullable
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getWsUrl() {
        return null;
    }
    
    public final boolean getAutoUpgrade() {
        return false;
    }
    
    public final void setAutoUpgrade(boolean value) {
    }
    
    public final boolean isFirstLaunch() {
        return false;
    }
    
    public final void setFirstLaunch(boolean value) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getRefreshToken() {
        return null;
    }
    
    public final void setRefreshToken(@org.jetbrains.annotations.Nullable
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getUsername() {
        return null;
    }
    
    public final void setUsername(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getPassword() {
        return null;
    }
    
    public final void setPassword(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getDeviceKey() {
        return null;
    }
    
    public final void setDeviceKey(@org.jetbrains.annotations.Nullable
    java.lang.String value) {
    }
    
    public final boolean getGuideShown() {
        return false;
    }
    
    public final void setGuideShown(boolean value) {
    }
    
    public final boolean getRememberAccount() {
        return false;
    }
    
    public final void setRememberAccount(boolean value) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getNickName() {
        return null;
    }
    
    public final void setNickName(@org.jetbrains.annotations.Nullable
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getUserInfoJson() {
        return null;
    }
    
    public final void setUserInfoJson(@org.jetbrains.annotations.Nullable
    java.lang.String value) {
    }
    
    public final boolean isLoggedIn() {
        return false;
    }
    
    public final void logout() {
    }
    
    /**
     * 清除所有本地配置，用于重置设备
     */
    public final void clearAll() {
    }
    
    public final void logCrash(@org.jetbrains.annotations.NotNull
    java.lang.Throwable ex) {
    }
}