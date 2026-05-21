package com.saltfish.assistant.data.remote

import com.google.gson.JsonObject
import com.saltfish.assistant.data.local.PreferencesManager
import java.security.SecureRandom
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

object SignHelper {

    private const val APP_ID = 1L
    private const val NONCE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
    private const val NONCE_LENGTH = 16
    private const val DEVICE_KEY_BYTES = 32

    private val random = SecureRandom()

    /** Get or create a persistent random device key (64 hex chars) */
    private fun getDeviceKey(prefs: PreferencesManager): String {
        prefs.deviceKey?.let { return it }
        val bytes = ByteArray(DEVICE_KEY_BYTES)
        random.nextBytes(bytes)
        val key = bytes.toHex()
        prefs.deviceKey = key
        return key
    }

    /** Generate a random 16-character alphanumeric nonce */
    private fun generateNonce(): String {
        return (1..NONCE_LENGTH)
            .map { NONCE_CHARS[random.nextInt(NONCE_CHARS.length)] }
            .joinToString("")
    }

    /** HMAC-SHA256 → lowercase hex string */
    private fun hmacSha256(message: String, key: String): String {
        val mac = Mac.getInstance("HmacSHA256")
        val secretKey = SecretKeySpec(key.toByteArray(Charsets.UTF_8), "HmacSHA256")
        mac.init(secretKey)
        val hash = mac.doFinal(message.toByteArray(Charsets.UTF_8))
        return hash.joinToString("") { "%02x".format(it.toInt() and 0xFF) }
    }

    /** Random bytes → lowercase hex string */
    private fun ByteArray.toHex(): String {
        return joinToString("") { "%02x".format(it.toInt() and 0xFF) }
    }

    /**
     * Build a signed JSON body for device registration / renewal.
     * sign = HMAC-SHA256(secret + uuid + timestamp + nonce, deviceKey)
     */
    fun buildSignedBody(prefs: PreferencesManager, secret: String, extra: JsonObject? = null): JsonObject {
        val deviceKey = getDeviceKey(prefs)
        val timestamp = System.currentTimeMillis()
        val nonce = generateNonce()
        val uuid = prefs.uuid
        val sign = hmacSha256(secret + uuid + timestamp + nonce, deviceKey)

        val body = extra ?: JsonObject()
        return body.apply {
            addProperty("uuid", uuid)
            addProperty("timestamp", timestamp)
            addProperty("nonce", nonce)
            addProperty("sign", sign)
            addProperty("deviceKey", deviceKey)
            addProperty("appId", APP_ID)
        }
    }
}
