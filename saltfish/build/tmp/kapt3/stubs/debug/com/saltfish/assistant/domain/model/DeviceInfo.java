package com.saltfish.assistant.domain.model;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\bI\b\u0086\b\u0018\u00002\u00020\u0001B\u0089\u0002\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u001f\u001a\u00020\u0005\u0012\b\b\u0002\u0010 \u001a\u00020\u0005\u00a2\u0006\u0002\u0010!J\t\u0010?\u001a\u00020\u0003H\u00c6\u0003J\t\u0010@\u001a\u00020\u0005H\u00c6\u0003J\t\u0010A\u001a\u00020\u0005H\u00c6\u0003J\t\u0010B\u001a\u00020\u000bH\u00c6\u0003J\t\u0010C\u001a\u00020\u0005H\u00c6\u0003J\t\u0010D\u001a\u00020\u0005H\u00c6\u0003J\t\u0010E\u001a\u00020\u0015H\u00c6\u0003J\t\u0010F\u001a\u00020\u000eH\u00c6\u0003J\t\u0010G\u001a\u00020\u0005H\u00c6\u0003J\t\u0010H\u001a\u00020\u0005H\u00c6\u0003J\t\u0010I\u001a\u00020\u000bH\u00c6\u0003J\t\u0010J\u001a\u00020\u0005H\u00c6\u0003J\t\u0010K\u001a\u00020\u000bH\u00c6\u0003J\t\u0010L\u001a\u00020\u0015H\u00c6\u0003J\t\u0010M\u001a\u00020\u0005H\u00c6\u0003J\t\u0010N\u001a\u00020\u0005H\u00c6\u0003J\t\u0010O\u001a\u00020\u0005H\u00c6\u0003J\t\u0010P\u001a\u00020\u0005H\u00c6\u0003J\t\u0010Q\u001a\u00020\u0005H\u00c6\u0003J\t\u0010R\u001a\u00020\u0005H\u00c6\u0003J\t\u0010S\u001a\u00020\u0005H\u00c6\u0003J\t\u0010T\u001a\u00020\u0005H\u00c6\u0003J\t\u0010U\u001a\u00020\u0005H\u00c6\u0003J\t\u0010V\u001a\u00020\u000bH\u00c6\u0003J\t\u0010W\u001a\u00020\u0005H\u00c6\u0003J\t\u0010X\u001a\u00020\u000eH\u00c6\u0003J\u008d\u0002\u0010Y\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010\u0011\u001a\u00020\u000b2\b\b\u0002\u0010\u0012\u001a\u00020\u00052\b\b\u0002\u0010\u0013\u001a\u00020\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u000e2\b\b\u0002\u0010\u0017\u001a\u00020\u00052\b\b\u0002\u0010\u0018\u001a\u00020\u00052\b\b\u0002\u0010\u0019\u001a\u00020\u000b2\b\b\u0002\u0010\u001a\u001a\u00020\u000b2\b\b\u0002\u0010\u001b\u001a\u00020\u00152\b\b\u0002\u0010\u001c\u001a\u00020\u00052\b\b\u0002\u0010\u001d\u001a\u00020\u00052\b\b\u0002\u0010\u001e\u001a\u00020\u00052\b\b\u0002\u0010\u001f\u001a\u00020\u00052\b\b\u0002\u0010 \u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010Z\u001a\u00020\u000e2\b\u0010[\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\\\u001a\u00020\u000bH\u00d6\u0001J\t\u0010]\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\t\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010 \u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010#R\u0011\u0010\u0010\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010#R\u0011\u0010\u0018\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010#R\u0011\u0010\u0012\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010#R\u0011\u0010\u0011\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\u0013\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010#R\u0011\u0010\u0014\u001a\u00020\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0011\u0010\b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010#R\u0011\u0010\u001f\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010#R\u0011\u0010\u001e\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010#R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0011\u0010\u001d\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u0010#R\u0011\u0010\u0016\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u00103R\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u00103R\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010#R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u0010#R\u0011\u0010\u001c\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b6\u0010#R\u0011\u0010\u001b\u001a\u00020\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u0010,R\u0011\u0010\u001a\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b8\u0010)R\u0011\u0010\u0019\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b9\u0010)R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b:\u0010)R\u0011\u0010\f\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b;\u0010#R\u0011\u0010\u000f\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b<\u0010#R\u0011\u0010\u0017\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b=\u0010#R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b>\u0010#\u00a8\u0006^"}, d2 = {"Lcom/saltfish/assistant/domain/model/DeviceInfo;", "", "id", "", "uuid", "", "name", "model", "brand", "androidVersion", "sdkLevel", "", "securityPatch", "isRooted", "", "totalMemory", "availableMemory", "batteryLevel", "batteryHealth", "batteryStatus", "batteryTemperature", "", "isCharging", "totalStorage", "availableStorage", "screenWidth", "screenHeight", "screenDensity", "networkType", "ipAddress", "cpuAbi", "buildFingerprint", "appVersion", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ZLjava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;FZLjava/lang/String;Ljava/lang/String;IIFLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAndroidVersion", "()Ljava/lang/String;", "getAppVersion", "getAvailableMemory", "getAvailableStorage", "getBatteryHealth", "getBatteryLevel", "()I", "getBatteryStatus", "getBatteryTemperature", "()F", "getBrand", "getBuildFingerprint", "getCpuAbi", "getId", "()J", "getIpAddress", "()Z", "getModel", "getName", "getNetworkType", "getScreenDensity", "getScreenHeight", "getScreenWidth", "getSdkLevel", "getSecurityPatch", "getTotalMemory", "getTotalStorage", "getUuid", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "saltfish_debug"})
public final class DeviceInfo {
    private final long id = 0L;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String uuid = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String name = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String model = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String brand = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String androidVersion = null;
    private final int sdkLevel = 0;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String securityPatch = null;
    private final boolean isRooted = false;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String totalMemory = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String availableMemory = null;
    private final int batteryLevel = 0;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String batteryHealth = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String batteryStatus = null;
    private final float batteryTemperature = 0.0F;
    private final boolean isCharging = false;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String totalStorage = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String availableStorage = null;
    private final int screenWidth = 0;
    private final int screenHeight = 0;
    private final float screenDensity = 0.0F;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String networkType = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String ipAddress = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String cpuAbi = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String buildFingerprint = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String appVersion = null;
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.domain.model.DeviceInfo copy(long id, @org.jetbrains.annotations.NotNull
    java.lang.String uuid, @org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    java.lang.String model, @org.jetbrains.annotations.NotNull
    java.lang.String brand, @org.jetbrains.annotations.NotNull
    java.lang.String androidVersion, int sdkLevel, @org.jetbrains.annotations.NotNull
    java.lang.String securityPatch, boolean isRooted, @org.jetbrains.annotations.NotNull
    java.lang.String totalMemory, @org.jetbrains.annotations.NotNull
    java.lang.String availableMemory, int batteryLevel, @org.jetbrains.annotations.NotNull
    java.lang.String batteryHealth, @org.jetbrains.annotations.NotNull
    java.lang.String batteryStatus, float batteryTemperature, boolean isCharging, @org.jetbrains.annotations.NotNull
    java.lang.String totalStorage, @org.jetbrains.annotations.NotNull
    java.lang.String availableStorage, int screenWidth, int screenHeight, float screenDensity, @org.jetbrains.annotations.NotNull
    java.lang.String networkType, @org.jetbrains.annotations.NotNull
    java.lang.String ipAddress, @org.jetbrains.annotations.NotNull
    java.lang.String cpuAbi, @org.jetbrains.annotations.NotNull
    java.lang.String buildFingerprint, @org.jetbrains.annotations.NotNull
    java.lang.String appVersion) {
        return null;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String toString() {
        return null;
    }
    
    public DeviceInfo() {
        super();
    }
    
    public DeviceInfo(long id, @org.jetbrains.annotations.NotNull
    java.lang.String uuid, @org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    java.lang.String model, @org.jetbrains.annotations.NotNull
    java.lang.String brand, @org.jetbrains.annotations.NotNull
    java.lang.String androidVersion, int sdkLevel, @org.jetbrains.annotations.NotNull
    java.lang.String securityPatch, boolean isRooted, @org.jetbrains.annotations.NotNull
    java.lang.String totalMemory, @org.jetbrains.annotations.NotNull
    java.lang.String availableMemory, int batteryLevel, @org.jetbrains.annotations.NotNull
    java.lang.String batteryHealth, @org.jetbrains.annotations.NotNull
    java.lang.String batteryStatus, float batteryTemperature, boolean isCharging, @org.jetbrains.annotations.NotNull
    java.lang.String totalStorage, @org.jetbrains.annotations.NotNull
    java.lang.String availableStorage, int screenWidth, int screenHeight, float screenDensity, @org.jetbrains.annotations.NotNull
    java.lang.String networkType, @org.jetbrains.annotations.NotNull
    java.lang.String ipAddress, @org.jetbrains.annotations.NotNull
    java.lang.String cpuAbi, @org.jetbrains.annotations.NotNull
    java.lang.String buildFingerprint, @org.jetbrains.annotations.NotNull
    java.lang.String appVersion) {
        super();
    }
    
    public final long component1() {
        return 0L;
    }
    
    public final long getId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getUuid() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getBrand() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getAndroidVersion() {
        return null;
    }
    
    public final int component7() {
        return 0;
    }
    
    public final int getSdkLevel() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getSecurityPatch() {
        return null;
    }
    
    public final boolean component9() {
        return false;
    }
    
    public final boolean isRooted() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getTotalMemory() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getAvailableMemory() {
        return null;
    }
    
    public final int component12() {
        return 0;
    }
    
    public final int getBatteryLevel() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component13() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getBatteryHealth() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component14() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getBatteryStatus() {
        return null;
    }
    
    public final float component15() {
        return 0.0F;
    }
    
    public final float getBatteryTemperature() {
        return 0.0F;
    }
    
    public final boolean component16() {
        return false;
    }
    
    public final boolean isCharging() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component17() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getTotalStorage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component18() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getAvailableStorage() {
        return null;
    }
    
    public final int component19() {
        return 0;
    }
    
    public final int getScreenWidth() {
        return 0;
    }
    
    public final int component20() {
        return 0;
    }
    
    public final int getScreenHeight() {
        return 0;
    }
    
    public final float component21() {
        return 0.0F;
    }
    
    public final float getScreenDensity() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component22() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getNetworkType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component23() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getIpAddress() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component24() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getCpuAbi() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component25() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getBuildFingerprint() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component26() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getAppVersion() {
        return null;
    }
}