package com.saltfish.assistant.data.remote;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0012\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\"\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\b2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\rJ\b\u0010\u0012\u001a\u00020\bH\u0002J\u0010\u0010\u0013\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0018\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\bH\u0002J\f\u0010\u0017\u001a\u00020\b*\u00020\u0018H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/saltfish/assistant/data/remote/SignHelper;", "", "()V", "APP_ID", "", "DEVICE_KEY_BYTES", "", "NONCE_CHARS", "", "NONCE_LENGTH", "random", "Ljava/security/SecureRandom;", "buildSignedBody", "Lcom/google/gson/JsonObject;", "prefs", "Lcom/saltfish/assistant/data/local/PreferencesManager;", "secret", "extra", "generateNonce", "getDeviceKey", "hmacSha256", "message", "key", "toHex", "", "saltfish_debug"})
public final class SignHelper {
    @org.jetbrains.annotations.NotNull
    public static final com.saltfish.assistant.data.remote.SignHelper INSTANCE = null;
    private static final long APP_ID = 1L;
    private static final java.lang.String NONCE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int NONCE_LENGTH = 16;
    private static final int DEVICE_KEY_BYTES = 32;
    private static final java.security.SecureRandom random = null;
    
    private SignHelper() {
        super();
    }
    
    /**
     * Get or create a persistent random device key (64 hex chars)
     */
    private final java.lang.String getDeviceKey(com.saltfish.assistant.data.local.PreferencesManager prefs) {
        return null;
    }
    
    /**
     * Generate a random 16-character alphanumeric nonce
     */
    private final java.lang.String generateNonce() {
        return null;
    }
    
    /**
     * HMAC-SHA256 → lowercase hex string
     */
    private final java.lang.String hmacSha256(java.lang.String message, java.lang.String key) {
        return null;
    }
    
    /**
     * Random bytes → lowercase hex string
     */
    private final java.lang.String toHex(byte[] $this$toHex) {
        return null;
    }
    
    /**
     * Build a signed JSON body for device registration / renewal.
     * sign = HMAC-SHA256(secret + uuid + timestamp + nonce, deviceKey)
     */
    @org.jetbrains.annotations.NotNull
    public final com.google.gson.JsonObject buildSignedBody(@org.jetbrains.annotations.NotNull
    com.saltfish.assistant.data.local.PreferencesManager prefs, @org.jetbrains.annotations.NotNull
    java.lang.String secret, @org.jetbrains.annotations.Nullable
    com.google.gson.JsonObject extra) {
        return null;
    }
}