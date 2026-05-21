package com.saltfish.assistant.data.local

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaDrm
import android.media.UnsupportedSchemeException
import android.provider.Settings
import java.security.MessageDigest
import java.util.UUID

/**
 * 设备唯一标识生成器 — ported from lib/libs/DeviceIdentity.ts
 *
 * 基于 Widevine DRM、Android ID 等硬件特征，降级生成稳定的 MD5 标识。
 * 支持包隔离开关：开启后不同包名生成不同 ID，关闭则所有包共用相同 ID。
 */
object DeviceIdentity {

    private val WIDEVINE_SCHEME_UUID = UUID.fromString("edef8ba9-79d6-4ace-a3c8-27dcd51d21ed")

    private const val STORE_NAME = "device_identity"
    private const val STORE_PREFIX = "device_identity_"

    /** 是否启用包隔离，默认 true。设为 false 则所有包共用同一标识。 */
    var enablePackageIsolation: Boolean = true

    private val cache = mutableMapOf<String, String>()

    /** 获取当前设备的唯一标识符（32 位 MD5 哈希值） */
    @SuppressLint("HardwareIds")
    fun getUUID(context: Context, packageName: String? = null): String? {
        val isolationKey = if (enablePackageIsolation) {
            packageName ?: context.packageName
        } else {
            "__global__"
        }

        // 1. 内存缓存
        cache[isolationKey]?.let { return it }

        // 2. 持久化缓存
        val storeName = if (enablePackageIsolation) STORE_PREFIX + isolationKey else STORE_NAME
        val prefs = context.getSharedPreferences(storeName, Context.MODE_PRIVATE)
        prefs.getString("uuid", null)?.let {
            cache[isolationKey] = it
            return it
        }

        // 3. 硬件推导
        var uuid: String? = getWidevineId(isolationKey)

        // 3b. ANDROID_ID → MD5
        if (uuid == null) {
            try {
                val androidId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
                if (!androidId.isNullOrEmpty() && androidId != "0000000000000000") {
                    val raw = if (enablePackageIsolation) "$isolationKey|$androidId" else androidId
                    uuid = raw.md5()
                }
            } catch (_: Exception) { }
        }

        // 3c. 随机 UUID → MD5 (always succeeds)
        if (uuid == null) {
            val randomUuid = UUID.randomUUID().toString()
            val raw = if (enablePackageIsolation) "$isolationKey|$randomUuid" else randomUuid
            uuid = raw.md5()
        }

        // 4. 持久化 + 内存缓存
        prefs.edit().putString("uuid", uuid).apply()
        cache[isolationKey] = uuid
        return uuid
    }

    private fun getWidevineId(isolationKey: String): String? {
        var mediaDrm: MediaDrm? = null
        return try {
            mediaDrm = MediaDrm(WIDEVINE_SCHEME_UUID)
            val deviceUniqueId = mediaDrm.getPropertyByteArray("deviceUniqueId")
            val hex = deviceUniqueId.toHex()
            val raw = if (enablePackageIsolation) "$isolationKey|$hex" else hex
            raw.md5()
        } catch (_: UnsupportedSchemeException) {
            null
        } catch (_: Exception) {
            null
        } finally {
            mediaDrm?.close()
        }
    }

    /** 清除指定隔离键的缓存 */
    fun clearCache(context: Context, isolationKey: String?) {
        if (isolationKey != null) {
            cache.remove(isolationKey)
            val storeName = if (enablePackageIsolation) STORE_PREFIX + isolationKey else STORE_NAME
            context.getSharedPreferences(storeName, Context.MODE_PRIVATE)
                .edit().remove("uuid").apply()
        } else {
            cache.clear()
        }
    }

    // ── helpers ──

    private fun ByteArray.toHex(): String {
        return joinToString("") { "%02x".format(it.toInt() and 0xFF) }
    }

    private fun String.md5(): String {
        val digest = MessageDigest.getInstance("MD5")
        val hash = digest.digest(toByteArray(Charsets.UTF_8))
        return hash.joinToString("") { "%02x".format(it.toInt() and 0xFF) }
    }
}
