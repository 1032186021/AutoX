package com.saltfish.assistant.data.local;

import java.lang.System;

/**
 * 设备唯一标识生成器 — ported from lib/libs/DeviceIdentity.ts
 *
 * 基于 Widevine DRM、Android ID 等硬件特征，降级生成稳定的 MD5 标识。
 * 支持包隔离开关：开启后不同包名生成不同 ID，关闭则所有包共用相同 ID。
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0012\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0004J\u001e\u0010\u0016\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0013\u001a\u00020\u00142\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0004H\u0007J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0015\u001a\u00020\u0004H\u0002J\f\u0010\u0019\u001a\u00020\u0004*\u00020\u0004H\u0002J\f\u0010\u001a\u001a\u00020\u0004*\u00020\u001bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\u00a8\u0006\u001c"}, d2 = {"Lcom/saltfish/assistant/data/local/DeviceIdentity;", "", "()V", "STORE_NAME", "", "STORE_PREFIX", "WIDEVINE_SCHEME_UUID", "Ljava/util/UUID;", "kotlin.jvm.PlatformType", "cache", "", "enablePackageIsolation", "", "getEnablePackageIsolation", "()Z", "setEnablePackageIsolation", "(Z)V", "clearCache", "", "context", "Landroid/content/Context;", "isolationKey", "getUUID", "packageName", "getWidevineId", "md5", "toHex", "", "saltfish_debug"})
public final class DeviceIdentity {
    @org.jetbrains.annotations.NotNull
    public static final com.saltfish.assistant.data.local.DeviceIdentity INSTANCE = null;
    private static final java.util.UUID WIDEVINE_SCHEME_UUID = null;
    private static final java.lang.String STORE_NAME = "device_identity";
    private static final java.lang.String STORE_PREFIX = "device_identity_";
    
    /**
     * 是否启用包隔离，默认 true。设为 false 则所有包共用同一标识。
     */
    private static boolean enablePackageIsolation = true;
    private static final java.util.Map<java.lang.String, java.lang.String> cache = null;
    
    private DeviceIdentity() {
        super();
    }
    
    public final boolean getEnablePackageIsolation() {
        return false;
    }
    
    public final void setEnablePackageIsolation(boolean p0) {
    }
    
    /**
     * 获取当前设备的唯一标识符（32 位 MD5 哈希值）
     */
    @org.jetbrains.annotations.Nullable
    @android.annotation.SuppressLint(value = {"HardwareIds"})
    public final java.lang.String getUUID(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    java.lang.String packageName) {
        return null;
    }
    
    private final java.lang.String getWidevineId(java.lang.String isolationKey) {
        return null;
    }
    
    /**
     * 清除指定隔离键的缓存
     */
    public final void clearCache(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    java.lang.String isolationKey) {
    }
    
    private final java.lang.String toHex(byte[] $this$toHex) {
        return null;
    }
    
    private final java.lang.String md5(java.lang.String $this$md5) {
        return null;
    }
}