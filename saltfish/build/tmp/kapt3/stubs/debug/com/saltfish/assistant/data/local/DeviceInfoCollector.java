package com.saltfish.assistant.data.local;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\t\u001a\u00020\bH\u0002J\b\u0010\n\u001a\u00020\bH\u0002J\b\u0010\u000b\u001a\u00020\bH\u0002J\b\u0010\f\u001a\u00020\bH\u0002J\b\u0010\r\u001a\u00020\bH\u0002J\b\u0010\u000e\u001a\u00020\bH\u0002J\u0006\u0010\u000f\u001a\u00020\u0010J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0014H\u0002J\b\u0010\u0017\u001a\u00020\u0012H\u0002J\b\u0010\u0018\u001a\u00020\u0012H\u0002J\b\u0010\u0019\u001a\u00020\u0012H\u0002J\b\u0010\u001a\u001a\u00020\u0012H\u0002J\n\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020\u0012H\u0002J\b\u0010 \u001a\u00020!H\u0002J\b\u0010\"\u001a\u00020\u0012H\u0002J\b\u0010#\u001a\u00020\u0012H\u0003J\b\u0010$\u001a\u00020\u0012H\u0002J\b\u0010%\u001a\u00020!H\u0002J\b\u0010&\u001a\u00020\u001eH\u0002J\b\u0010\'\u001a\u00020\u001eH\u0002J\b\u0010(\u001a\u00020\u0012H\u0002J\b\u0010)\u001a\u00020\u0012H\u0002J\b\u0010*\u001a\u00020\bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006+"}, d2 = {"Lcom/saltfish/assistant/data/local/DeviceInfoCollector;", "", "context", "Landroid/content/Context;", "prefs", "Lcom/saltfish/assistant/data/local/PreferencesManager;", "(Landroid/content/Context;Lcom/saltfish/assistant/data/local/PreferencesManager;)V", "checkBuildTags", "", "checkDangerousProps", "checkMagisk", "checkRoot", "checkRwPaths", "checkSuBinary", "checkSuperuserApk", "collect", "Lcom/saltfish/assistant/domain/model/DeviceInfo;", "formatBytes", "", "bytes", "", "formatMemory", "kb", "getAppVersion", "getAvailableMemory", "getAvailableStorage", "getBatteryHealth", "getBatteryIntent", "Landroid/content/Intent;", "getBatteryLevel", "", "getBatteryStatus", "getBatteryTemperature", "", "getLocalIpAddress", "getNetworkType", "getOrCreateUuid", "getScreenDensity", "getScreenHeight", "getScreenWidth", "getTotalMemory", "getTotalStorage", "isDeviceCharging", "saltfish_debug"})
public final class DeviceInfoCollector {
    private final android.content.Context context = null;
    private final com.saltfish.assistant.data.local.PreferencesManager prefs = null;
    
    public DeviceInfoCollector(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    com.saltfish.assistant.data.local.PreferencesManager prefs) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.domain.model.DeviceInfo collect() {
        return null;
    }
    
    private final java.lang.String getOrCreateUuid() {
        return null;
    }
    
    private final boolean checkRoot() {
        return false;
    }
    
    private final boolean checkSuBinary() {
        return false;
    }
    
    private final boolean checkMagisk() {
        return false;
    }
    
    private final boolean checkSuperuserApk() {
        return false;
    }
    
    private final boolean checkBuildTags() {
        return false;
    }
    
    private final boolean checkDangerousProps() {
        return false;
    }
    
    private final boolean checkRwPaths() {
        return false;
    }
    
    private final java.lang.String getTotalMemory() {
        return null;
    }
    
    private final java.lang.String getAvailableMemory() {
        return null;
    }
    
    private final android.content.Intent getBatteryIntent() {
        return null;
    }
    
    private final int getBatteryLevel() {
        return 0;
    }
    
    private final java.lang.String getBatteryHealth() {
        return null;
    }
    
    private final java.lang.String getBatteryStatus() {
        return null;
    }
    
    private final float getBatteryTemperature() {
        return 0.0F;
    }
    
    private final boolean isDeviceCharging() {
        return false;
    }
    
    private final java.lang.String getTotalStorage() {
        return null;
    }
    
    private final java.lang.String getAvailableStorage() {
        return null;
    }
    
    private final int getScreenWidth() {
        return 0;
    }
    
    private final int getScreenHeight() {
        return 0;
    }
    
    private final float getScreenDensity() {
        return 0.0F;
    }
    
    @android.annotation.SuppressLint(value = {"MissingPermission"})
    private final java.lang.String getNetworkType() {
        return null;
    }
    
    private final java.lang.String getLocalIpAddress() {
        return null;
    }
    
    private final java.lang.String getAppVersion() {
        return null;
    }
    
    private final java.lang.String formatMemory(long kb) {
        return null;
    }
    
    private final java.lang.String formatBytes(long bytes) {
        return null;
    }
}