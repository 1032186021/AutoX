package com.saltfish.assistant.engine

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.util.zip.ZipInputStream

class ScriptManager(private val context: Context) {

    private val _adapterVersions = MutableStateFlow<Map<String, String>>(emptyMap())
    val adapterVersions: StateFlow<Map<String, String>> = _adapterVersions

    private val adaptersDir: File
        get() = File(context.filesDir, "adapters")

    /**
     * Initialize: extract built-in adapters from assets to filesDir.
     */
    suspend fun initialize() = withContext(Dispatchers.IO) {
        if (!adaptersDir.exists()) {
            adaptersDir.mkdirs()
            val assetsDir = "adapters"
            context.assets.list(assetsDir)?.forEach { platform ->
                val platformDir = File(adaptersDir, platform)
                platformDir.mkdirs()
                context.assets.list("$assetsDir/$platform")?.forEach { file ->
                    val target = File(platformDir, file)
                    context.assets.open("$assetsDir/$platform/$file").use { input ->
                        FileOutputStream(target).use { output ->
                            input.copyTo(output)
                        }
                    }
                }
            }
        }
        scanLocalVersions()
    }

    private fun scanLocalVersions() {
        val versions = mutableMapOf<String, String>()
        adaptersDir.listFiles()?.forEach { platformDir ->
            if (platformDir.isDirectory) {
                val versionFile = File(platformDir, "version.json")
                if (versionFile.exists()) {
                    val json = JSONObject(versionFile.readText())
                    versions[platformDir.name] = json.optString("version", "0.0.0")
                }
            }
        }
        _adapterVersions.value = versions
    }

    /**
     * Get the adapter script content for a specific platform.
     */
    fun getAdapterScript(platform: String): String? {
        val platformDir = File(adaptersDir, platform)
        if (!platformDir.isDirectory) return null
        val adapterFile = File(platformDir, "index.js")
        if (!adapterFile.isFile) return null
        return adapterFile.readText()
    }

    /**
     * Get bridge injection script (injected into each adapter scope).
     */
    fun getBridgeScript(): String {
        return """
var scriptBridge = __scriptBridge__;
var platform = __platform__;
var taskType = __taskType__;
var params = JSON.parse(__params__);
        """.trimIndent()
    }

    /**
     * Download ZIP update for an adapter from cloud.
     */
    suspend fun updateFromCloud(downloadUrl: String, platform: String): Boolean =
        withContext(Dispatchers.IO) {
            try {
                val client = OkHttpClient()
                val request = Request.Builder().url(downloadUrl).build()
                val response = client.newCall(request).execute()
                val body = response.body ?: return@withContext false

                val zipFile = File(context.cacheDir, "adapter_update_$platform.zip")
                FileOutputStream(zipFile).use { body.byteStream().copyTo(it) }

                val targetDir = File(adaptersDir, platform)
                targetDir.mkdirs()
                ZipInputStream(zipFile.inputStream()).use { zip ->
                    var entry = zip.nextEntry
                    while (entry != null) {
                        val entryFile = File(targetDir, entry.name)
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
                scanLocalVersions()
                true
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }
}
