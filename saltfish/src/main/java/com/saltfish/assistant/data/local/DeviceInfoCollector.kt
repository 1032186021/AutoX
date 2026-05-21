package com.saltfish.assistant.data.local

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.BatteryManager
import android.os.Build
import android.os.Environment
import android.os.StatFs
import android.provider.Settings
import android.util.DisplayMetrics
import com.saltfish.assistant.domain.model.DeviceInfo
import java.io.File
import java.net.Inet4Address
import java.net.NetworkInterface
class DeviceInfoCollector(
    private val context: Context,
    private val prefs: PreferencesManager
) {

    fun collect(): DeviceInfo {
        return DeviceInfo(
            uuid = getOrCreateUuid(),
            name = "${Build.BRAND} ${Build.MODEL}",
            model = Build.MODEL,
            brand = Build.BRAND,
            androidVersion = Build.VERSION.RELEASE,
            sdkLevel = Build.VERSION.SDK_INT,
            securityPatch = Build.VERSION.SECURITY_PATCH ?: "",
            isRooted = checkRoot(),
            totalMemory = getTotalMemory(),
            availableMemory = getAvailableMemory(),
            batteryLevel = getBatteryLevel(),
            batteryHealth = getBatteryHealth(),
            batteryStatus = getBatteryStatus(),
            batteryTemperature = getBatteryTemperature(),
            isCharging = isDeviceCharging(),
            totalStorage = getTotalStorage(),
            availableStorage = getAvailableStorage(),
            screenWidth = getScreenWidth(),
            screenHeight = getScreenHeight(),
            screenDensity = getScreenDensity(),
            networkType = getNetworkType(),
            ipAddress = getLocalIpAddress(),
            cpuAbi = Build.SUPPORTED_ABIS.firstOrNull() ?: "",
            buildFingerprint = Build.FINGERPRINT,
            appVersion = getAppVersion()
        )
    }

    private fun getOrCreateUuid(): String = DeviceIdentity.getUUID(context) ?: ""

    // region Root Detection

    private fun checkRoot(): Boolean {
        return checkSuBinary() || checkMagisk() || checkSuperuserApk() ||
                checkBuildTags() || checkDangerousProps() || checkRwPaths()
    }

    private fun checkSuBinary(): Boolean {
        val paths = arrayOf(
            "/system/bin/su", "/system/xbin/su", "/system/sbin/su",
            "/sbin/su", "/vendor/bin/su", "/data/local/su",
            "/data/local/bin/su", "/data/local/xbin/su",
            "/su/bin/su", "/system/app/SuperSU"
        )
        return paths.any { File(it).exists() }
    }

    private fun checkMagisk(): Boolean {
        val paths = arrayOf(
            "/sbin/magisk", "/data/adb/magisk", "/data/adb/magisk.db",
            "/data/adb/modules", "/cache/magisk.log", "/data/magisk.img"
        )
        val magiskInPath = runCatching {
            val process = Runtime.getRuntime().exec(arrayOf("which", "magisk"))
            process.waitFor() == 0
        }.getOrDefault(false)
        return paths.any { File(it).exists() } || magiskInPath
    }

    private fun checkSuperuserApk(): Boolean {
        val packages = arrayOf(
            "com.noshufou.android.su", "com.thirdparty.superuser",
            "eu.chainfire.supersu", "com.koushikdutta.superuser",
            "com.zachspong.temprootremovejb", "com.ramdroid.appquarantine",
            "com.topjohnwu.magisk"
        )
        val pm = context.packageManager
        return packages.any {
            runCatching { pm.getPackageInfo(it, 0) }.getOrNull() != null
        }
    }

    private fun checkBuildTags(): Boolean {
        val buildTags = Build.TAGS
        return buildTags != null && buildTags.contains("test-keys")
    }

    private fun checkDangerousProps(): Boolean {
        val props = arrayOf("ro.debuggable", "ro.secure")
        return props.any { prop ->
            runCatching {
                val process = Runtime.getRuntime().exec(arrayOf("getprop", prop))
                val result = process.inputStream.bufferedReader().readText().trim()
                process.waitFor()
                when (prop) {
                    "ro.debuggable" -> result == "1"
                    "ro.secure" -> result == "0"
                    else -> false
                }
            }.getOrDefault(false)
        }
    }

    private fun checkRwPaths(): Boolean {
        val paths = arrayOf("/system", "/system/bin", "/system/xbin", "/vendor", "/etc")
        return paths.any { path ->
            runCatching {
                val file = File(path)
                file.exists() && file.canWrite()
            }.getOrDefault(false)
        }
    }

    // endregion

    // region Memory

    private fun getTotalMemory(): String {
        val memInfo = File("/proc/meminfo")
        if (!memInfo.exists()) return "N/A"
        for (line in memInfo.readLines()) {
            if (line.startsWith("MemTotal:")) {
                val kb = line.replace(Regex("[^0-9]"), "").toLongOrNull() ?: 0
                return formatMemory(kb)
            }
        }
        return "N/A"
    }

    private fun getAvailableMemory(): String {
        val memInfo = File("/proc/meminfo")
        if (!memInfo.exists()) return "N/A"
        for (line in memInfo.readLines()) {
            if (line.startsWith("MemAvailable:")) {
                val kb = line.replace(Regex("[^0-9]"), "").toLongOrNull() ?: 0
                return formatMemory(kb)
            }
        }
        return "N/A"
    }

    // endregion

    // region Battery

    private fun getBatteryIntent(): Intent? {
        return context.registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
    }

    private fun getBatteryLevel(): Int {
        val intent = getBatteryIntent() ?: return 0
        val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
        return if (scale > 0) (level * 100 / scale) else 0
    }

    private fun getBatteryHealth(): String {
        val intent = getBatteryIntent() ?: return "未知"
        return when (intent.getIntExtra(BatteryManager.EXTRA_HEALTH, BatteryManager.BATTERY_HEALTH_UNKNOWN)) {
            BatteryManager.BATTERY_HEALTH_GOOD -> "良好"
            BatteryManager.BATTERY_HEALTH_OVERHEAT -> "过热"
            BatteryManager.BATTERY_HEALTH_DEAD -> "损坏"
            BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE -> "过压"
            BatteryManager.BATTERY_HEALTH_COLD -> "过冷"
            else -> "未知"
        }
    }

    private fun getBatteryStatus(): String {
        val intent = getBatteryIntent() ?: return "未知"
        return when (intent.getIntExtra(BatteryManager.EXTRA_STATUS, BatteryManager.BATTERY_STATUS_UNKNOWN)) {
            BatteryManager.BATTERY_STATUS_CHARGING -> "充电中"
            BatteryManager.BATTERY_STATUS_DISCHARGING -> "放电中"
            BatteryManager.BATTERY_STATUS_FULL -> "已充满"
            BatteryManager.BATTERY_STATUS_NOT_CHARGING -> "未充电"
            else -> "未知"
        }
    }

    private fun getBatteryTemperature(): Float {
        val intent = getBatteryIntent() ?: return 0f
        val temp = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0)
        return temp / 10f
    }

    private fun isDeviceCharging(): Boolean {
        val intent = getBatteryIntent() ?: return false
        val status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, BatteryManager.BATTERY_STATUS_UNKNOWN)
        return status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL
    }

    // endregion

    // region Storage

    private fun getTotalStorage(): String {
        return try {
            val stat = StatFs(Environment.getDataDirectory().path)
            val bytes = stat.blockCountLong * stat.blockSizeLong
            formatBytes(bytes)
        } catch (e: Exception) {
            "N/A"
        }
    }

    private fun getAvailableStorage(): String {
        return try {
            val stat = StatFs(Environment.getDataDirectory().path)
            val bytes = stat.availableBlocksLong * stat.blockSizeLong
            formatBytes(bytes)
        } catch (e: Exception) {
            "N/A"
        }
    }

    // endregion

    // region Screen

    private fun getScreenWidth(): Int {
        val metrics: DisplayMetrics = context.resources.displayMetrics
        return metrics.widthPixels
    }

    private fun getScreenHeight(): Int {
        val metrics: DisplayMetrics = context.resources.displayMetrics
        return metrics.heightPixels
    }

    private fun getScreenDensity(): Float {
        return context.resources.displayMetrics.density
    }

    // endregion

    // region Network

    @SuppressLint("MissingPermission")
    private fun getNetworkType(): String {
        return try {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val network = cm.activeNetwork ?: return "无网络"
            val caps = cm.getNetworkCapabilities(network) ?: return "未知"
            when {
                caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> "WiFi"
                caps.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> "移动网络"
                caps.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> "以太网"
                caps.hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> "VPN"
                else -> "其他"
            }
        } catch (e: Exception) {
            "未知"
        }
    }

    private fun getLocalIpAddress(): String {
        return try {
            NetworkInterface.getNetworkInterfaces()?.toList()?.flatMap { it.inetAddresses.toList() }
                ?.firstOrNull { !it.isLoopbackAddress && it is Inet4Address }
                ?.hostAddress ?: "127.0.0.1"
        } catch (e: Exception) {
            "127.0.0.1"
        }
    }

    // endregion

    // region App

    private fun getAppVersion(): String {
        return try {
            val info = context.packageManager.getPackageInfo(context.packageName, 0)
            info.versionName ?: "1.0.0"
        } catch (e: PackageManager.NameNotFoundException) {
            "1.0.0"
        }
    }

    // endregion

    // region Formatting

    private fun formatMemory(kb: Long): String {
        return when {
            kb > 1024 * 1024 -> "%.1f GB".format(kb / (1024.0 * 1024.0))
            kb >= 1024 -> "%.0f MB".format(kb / 1024.0)
            else -> "${kb} KB"
        }
    }

    private fun formatBytes(bytes: Long): String {
        return when {
            bytes > 1024 * 1024 * 1024 -> "%.1f GB".format(bytes / (1024.0 * 1024.0 * 1024.0))
            bytes >= 1024 * 1024 -> "%.0f MB".format(bytes / (1024.0 * 1024.0))
            bytes >= 1024 -> "%.0f KB".format(bytes / 1024.0)
            else -> "${bytes} B"
        }
    }

    // endregion
}
