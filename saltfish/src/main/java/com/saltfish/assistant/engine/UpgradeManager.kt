package com.saltfish.assistant.engine

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.FileProvider
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.saltfish.assistant.data.local.PreferencesManager
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.FileOutputStream
import java.util.zip.ZipInputStream

data class CloudVersion(
    @SerializedName("appVersion") val appVersion: String = "",
    @SerializedName("scriptVersion") val scriptVersion: String = "",
    @SerializedName("url") val url: String = "",
    @SerializedName("type") val type: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("isForce") val isForce: Int = 0
)

enum class UpgradeType { NONE, APK, ADAPTER }

data class UpgradeState(
    val type: UpgradeType = UpgradeType.NONE,
    val title: String = "",
    val description: String = "",
    val progress: Int = 0,        // 0-100, -1 = error
    val isDownloading: Boolean = false,
    val isInstalling: Boolean = false,
    val error: String? = null,
    val latestVersion: CloudVersion? = null
)

class UpgradeManager(
    private val context: Context,
    private val prefs: PreferencesManager,
    private val onRestart: () -> Unit
) {
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val gson = Gson()
    private val httpClient = OkHttpClient()

    private val _state = MutableStateFlow(UpgradeState())
    val state: StateFlow<UpgradeState> = _state.asStateFlow()

    fun checkUpdate(checkUrl: String, currentAppVersion: String, currentScriptVersion: String) {
        scope.launch {
            try {
                val request = Request.Builder().url(checkUrl).build()
                val response = httpClient.newCall(request).execute()
                val body = response.body?.string() ?: return@launch

                val latest = gson.fromJson(body, CloudVersion::class.java)
                val appVersionNum = parseVersionNumber(currentAppVersion)
                val latestAppNum = parseVersionNumber(latest.appVersion)
                val scriptVersionNum = parseVersionNumber(currentScriptVersion)
                val latestScriptNum = parseVersionNumber(latest.scriptVersion)

                when {
                    appVersionNum < latestAppNum -> {
                        _state.value = UpgradeState(
                            type = UpgradeType.APK,
                            title = "发现新版本 v${latest.appVersion}",
                            description = latest.description
                                ?: "检测到APK更新，请下载安装",
                            latestVersion = latest
                        )
                    }
                    scriptVersionNum < latestScriptNum -> {
                        _state.value = UpgradeState(
                            type = UpgradeType.ADAPTER,
                            title = "适配器更新 v${latest.scriptVersion}",
                            description = latest.description
                                ?: "检测到适配器脚本更新",
                            latestVersion = latest
                        )
                    }
                    else -> {
                        _state.value = UpgradeState(type = UpgradeType.NONE)
                    }
                }
            } catch (e: Exception) {
                _state.value = UpgradeState(
                    error = "检查更新失败: ${e.message}"
                )
            }
        }
    }

    fun downloadAndInstall() {
        val info = _state.value.latestVersion ?: return
        if (_state.value.type == UpgradeType.NONE) return

        scope.launch {
            _state.value = _state.value.copy(isDownloading = true, progress = 0)

            val savePath = if (_state.value.type == UpgradeType.APK) {
                File(context.cacheDir, "upgrade.apk")
            } else {
                File(context.cacheDir, "adapter_update.zip")
            }

            try {
                val request = Request.Builder().url(info.url).build()
                val response = httpClient.newCall(request).execute()
                val body = response.body ?: run {
                    _state.value = _state.value.copy(
                        isDownloading = false,
                        error = "下载失败：响应为空"
                    )
                    return@launch
                }

                val totalBytes = body.contentLength()
                var downloadedBytes = 0L
                val buffer = ByteArray(4096)
                var lastPercent = -1

                FileOutputStream(savePath).use { output ->
                    body.byteStream().use { input ->
                        var read: Int
                        while (input.read(buffer).also { read = it } != -1) {
                            downloadedBytes += read
                            output.write(buffer, 0, read)
                            if (totalBytes > 0) {
                                val percent = (downloadedBytes * 100 / totalBytes).toInt()
                                if (percent != lastPercent) {
                                    lastPercent = percent
                                    _state.value = _state.value.copy(progress = percent)
                                }
                            }
                        }
                    }
                }

                _state.value = _state.value.copy(
                    isDownloading = false,
                    progress = 100,
                    isInstalling = true
                )

                when (_state.value.type) {
                    UpgradeType.APK -> installApk(savePath)
                    UpgradeType.ADAPTER -> installAdapter(savePath)
                    else -> {}
                }
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isDownloading = false,
                    progress = -1,
                    error = "下载失败: ${e.message}"
                )
            }
        }
    }

    private fun installApk(file: File) {
        try {
            val uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                file
            )
            val intent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(uri, "application/vnd.android.package-archive")
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
            _state.value = _state.value.copy(isInstalling = false)
        } catch (e: Exception) {
            _state.value = _state.value.copy(
                isInstalling = false,
                error = "安装失败: ${e.message}"
            )
        }
    }

    private suspend fun installAdapter(zipFile: File) {
        try {
            val adaptersDir = File(context.filesDir, "adapters")
            adaptersDir.mkdirs()

            ZipInputStream(zipFile.inputStream()).use { zip ->
                var entry = zip.nextEntry
                while (entry != null) {
                    val entryFile = File(adaptersDir, entry.name)
                    if (entry.isDirectory) {
                        entryFile.mkdirs()
                    } else {
                        entryFile.parentFile?.mkdirs()
                        FileOutputStream(entryFile).use { zip.copyTo(it) }
                    }
                    entry = zip.nextEntry
                }
            }
            zipFile.delete()
            _state.value = _state.value.copy(isInstalling = false)

            // 通知重载（需在主线程操作Service）
            withContext(Dispatchers.Main) { onRestart() }
        } catch (e: Exception) {
            _state.value = _state.value.copy(
                isInstalling = false,
                error = "适配器安装失败: ${e.message}"
            )
        }
    }

    fun dismiss() {
        _state.value = UpgradeState()
    }

    private fun parseVersionNumber(version: String): Long {
        return try {
            val parts = version.split(".").map { it.toIntOrNull() ?: 0 }
            (parts.getOrElse(0) { 0 } * 1000000L +
             parts.getOrElse(1) { 0 } * 1000L +
             parts.getOrElse(2) { 0 })
        } catch (_: Exception) { 0L }
    }
}
