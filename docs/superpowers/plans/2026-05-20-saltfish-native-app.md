# Saltfish Native Android App Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** 将 client-autojs（咸鱼助手）从 TypeScript/Auto.js 脚本重构为基于 AutoX 的原生 Kotlin Android 应用，复用 autojs/automator/common 模块作为脚本引擎和自动化核心。

**Architecture:** 新建 `:saltfish` 独立 application 模块，依赖 `:autojs`、`:automator`、`:common`。MVVM + Repository 架构，Compose UI + Material 3。原生层通过 Rhino `ScriptEngine.put()` 注入桥接对象与 Adapter 脚本双向通信。

**Tech Stack:** Kotlin 1.6.21, Compose 1.2.0-rc01, AGP 8.0.2, Room, Retrofit 2.9.0, Socket.IO client 2.0.0, Coroutines 1.6.2, OkHttp 4.10.0, EventBus 3.3.1, Gson 2.9.1

---

## Phase 1: 模块搭建

### Task 1: 创建 saltfish 模块的 Gradle 构建配置

**Files:**
- Create: `saltfish/build.gradle.kts`
- Modify: `settings.gradle:3`

- [ ] **Step 1: 编写 saltfish/build.gradle.kts**

```kotlin
plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdk = versions.compile

    defaultConfig {
        applicationId = "com.saltfish.assistant"
        minSdk = versions.mini
        targetSdk = versions.target
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    composeOptions {
        kotlinCompilerExtensionVersion = compose_version
    }

    lint {
        abortOnError = false
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            setProguardFiles(
                listOf(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            )
        }
    }

    namespace = "com.saltfish.assistant"
}

dependencies {
    // AutoX 核心模块
    implementation(project(":autojs"))
    implementation(project(":automator"))
    implementation(project(":common"))

    // Compose
    implementation(libs.compose.ui)
    implementation(libs.compose.material)
    implementation(libs.compose.ui.tooling.preview)
    debugImplementation(libs.compose.ui.tooling)
    implementation(libs.compose.foundation)
    implementation(libs.compose.foundation.layout)
    implementation(libs.activity.compose)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.accompanist.insets)

    // Lifecycle
    val lifecycleVersion = "2.5.1"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-service:$lifecycleVersion")

    // Navigation Compose
    implementation("androidx.navigation:navigation-compose:2.5.3")

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // Room
    val roomVersion = "2.4.3"
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation(libs.okhttp)

    // Socket.IO
    implementation("io.socket:socket.io-client:2.0.0")

    // EventBus (复用 autojs 已有的)
    // Gson (复用)
    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Coil for image loading
    implementation("io.coil-kt:coil-compose:2.2.2")

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.espresso.core)
}
```

- [ ] **Step 2: 在 settings.gradle 添加 saltfish 模块**

在 `settings.gradle` 的 include 列表末尾添加：

```
include ':saltfish'
```

- [ ] **Step 3: 验证模块可被 Gradle 解析**

Run: `cd D:\code\AutoX && ./gradlew :saltfish:tasks --group=build 2>&1 | head -20`
Expected: 列出 saltfish 模块的 build tasks，无报错

---

### Task 2: 创建盐鱼助手 AndroidManifest

**Files:**
- Create: `saltfish/src/main/AndroidManifest.xml`

- [ ] **Step 1: 编写 AndroidManifest.xml**

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <!-- 网络 -->
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

    <!-- 文件存储 -->
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

    <!-- 前台服务 -->
    <uses-permission android:name="android.permission.FOREGROUND_SERVICE" />
    <uses-permission android:name="android.permission.FOREGROUND_SERVICE_SPECIAL_USE" />

    <!-- 悬浮窗 -->
    <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />

    <!-- 电池优化 -->
    <uses-permission android:name="android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS" />

    <!-- 开机自启 -->
    <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />

    <!-- 无障碍服务 -->
    <uses-permission
        android:name="android.permission.BIND_ACCESSIBILITY_SERVICE"
        tools:ignore="ProtectedPermissions" />

    <!-- 屏幕截图 -->
    <uses-permission android:name="android.permission.FOREGROUND_SERVICE_MEDIA_PROJECTION" />

    <!-- 通知 -->
    <uses-permission android:name="android.permission.POST_NOTIFICATIONS" />

    <!-- 安装 APK -->
    <uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES" />

    <!-- 震动 -->
    <uses-permission android:name="android.permission.VIBRATE" />

    <!-- 唤醒锁 -->
    <uses-permission android:name="android.permission.WAKE_LOCK" />

    <application
        android:name=".SaltfishApp"
        android:allowBackup="false"
        android:icon="@mipmap/ic_launcher"
        android:label="咸鱼助手"
        android:supportsRtl="true"
        android:theme="@style/Theme.Saltfish"
        android:resizeableActivity="true">

        <activity
            android:name=".MainActivity"
            android:exported="true"
            android:launchMode="singleTop"
            android:theme="@style/Theme.Saltfish">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

        <service
            android:name=".service.ScriptService"
            android:exported="false"
            android:foregroundServiceType="specialUse" />

        <service
            android:name="com.stardust.autojs.core.accessibility.AccessibilityService"
            android:exported="true"
            android:permission="android.permission.BIND_ACCESSIBILITY_SERVICE">
            <intent-filter>
                <action android:name="android.accessibilityservice.AccessibilityService" />
            </intent-filter>
            <meta-data
                android:name="android.accessibilityservice"
                android:resource="@xml/accessibility_service_config" />
        </service>

        <receiver
            android:name=".receiver.BootReceiver"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.BOOT_COMPLETED" />
            </intent-filter>
        </receiver>

    </application>
</manifest>
```

- [ ] **Step 2: 创建无障碍服务配置文件**

Create `saltfish/src/main/res/xml/accessibility_service_config.xml`:

```xml
<?xml version="1.0" encoding="utf-8"?>
<accessibility-service
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:accessibilityEventTypes="typeAllMask"
    android:accessibilityFeedbackType="feedbackGeneric"
    android:accessibilityFlags="flagDefault|flagRetrieveInteractiveWindows|flagRequestTouchExplorationMode"
    android:canRetrieveWindowContent="true"
    android:canPerformGestures="true"
    android:canTakeScreenshot="true"
    android:notificationTimeout="100" />
```

- [ ] **Step 3: 创建 Themes.xml**

Create `saltfish/src/main/res/values/themes.xml`:

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <style name="Theme.Saltfish" parent="android:Theme.Material.Light.NoActionBar">
        <item name="android:statusBarColor">@android:color/transparent</item>
        <item name="android:navigationBarColor">@android:color/transparent</item>
    </style>
</resources>
```

- [ ] **Step 4: 创建默认图标 placeholder**

Run:
```bash
mkdir -p D:\code\AutoX\saltfish\src\main\res\mipmap-hdpi
mkdir -p D:\code\AutoX\saltfish\src\main\res\mipmap-mdpi
mkdir -p D:\code\AutoX\saltfish\src\main\res\mipmap-xhdpi
mkdir -p D:\code\AutoX\saltfish\src\main\res\mipmap-xxhdpi
mkdir -p D:\code\AutoX\saltfish\src\main\res\xml
```

Copy a placeholder `ic_launcher.png` from `app/src/main/res/` or create simple XML adaptive icon.

- [ ] **Step 5: Commit**

```bash
git add saltfish/ settings.gradle
git commit -m "feat: 创建 saltfish 模块骨架（build.gradle, manifest, theme）"
```

---

## Phase 2: 核心引擎

### Task 3: 创建 SaltfishApp 入口

**Files:**
- Create: `saltfish/src/main/java/com/saltfish/assistant/SaltfishApp.kt`

- [ ] **Step 1: 编写 SaltfishApp.kt**

```kotlin
package com.saltfish.assistant

import android.app.Application
import android.content.Context
import com.saltfish.assistant.data.local.PreferencesManager
import com.saltfish.assistant.data.remote.ApiClient
import com.saltfish.assistant.data.remote.SocketIOManager
import com.saltfish.assistant.data.repository.AccountRepository
import com.saltfish.assistant.data.repository.DeviceRepository
import com.saltfish.assistant.data.repository.TaskRepository
import com.saltfish.assistant.engine.ScriptBridge
import com.saltfish.assistant.engine.ScriptManager
import com.stardust.autojs.engine.ScriptEngineManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import java.lang.Thread.UncaughtExceptionHandler

class SaltfishApp : Application() {

    lateinit var scriptEngineManager: ScriptEngineManager
        private set
    lateinit var scriptManager: ScriptManager
        private set
    lateinit var scriptBridge: ScriptBridge
        private set

    val appScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    val preferencesManager by lazy { PreferencesManager(this) }
    val apiClient by lazy { ApiClient(preferencesManager) }
    val socketIOManager by lazy { SocketIOManager(preferencesManager) }
    val deviceRepository by lazy { DeviceRepository(apiClient, preferencesManager) }
    val accountRepository by lazy { AccountRepository(apiClient, preferencesManager) }
    val taskRepository by lazy { TaskRepository(apiClient, preferencesManager) }

    override fun onCreate() {
        super.onCreate()
        instance = this
        setupCrashHandler()

        scriptEngineManager = ScriptEngineManager(this)
        scriptManager = ScriptManager(this)
        scriptBridge = ScriptBridge(scriptEngineManager, scriptManager)
    }

    private fun setupCrashHandler() {
        val currentHandler = UncaughtExceptionHandler.getDefaultUncaughtExceptionHandler()
        UncaughtExceptionHandler.setDefaultUncaughtExceptionHandler { thread, ex ->
            preferencesManager.logCrash(ex)
            currentHandler?.uncaughtException(thread, ex)
        }
    }

    companion object {
        lateinit var instance: SaltfishApp
            private set
    }
}

val Context.app: SaltfishApp get() = applicationContext as SaltfishApp
```

- [ ] **Step 2: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/SaltfishApp.kt
git commit -m "feat: 创建 SaltfishApp 入口，初始化依赖图"
```

---

### Task 4: 创建 ScriptBridge 桥接层

**Files:**
- Create: `saltfish/src/main/java/com/saltfish/assistant/engine/ScriptBridge.kt`

- [ ] **Step 1: 编写 ScriptBridge.kt**

```kotlin
package com.saltfish.assistant.engine

import com.saltfish.assistant.domain.model.TaskResult
import com.stardust.autojs.engine.ScriptEngine
import com.stardust.autojs.engine.ScriptEngineManager
import com.stardust.autojs.runtime.api.Console
import com.stardust.autojs.script.JavaScriptSource
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.json.JSONObject

class ScriptBridge(
    private val engineManager: ScriptEngineManager,
    private val scriptManager: ScriptManager
) {
    private val _taskState = MutableStateFlow<TaskExecutionState>(TaskExecutionState.Idle)
    val taskState: StateFlow<TaskExecutionState> = _taskState

    private var currentDeferred: CompletableDeferred<TaskResult>? = null

    /**
     * 执行自动化任务。
     * 在当前线程同步执行脚本，通过 callback 上报进度/结果。
     */
    suspend fun executeTask(
        platform: String,
        taskType: String,
        params: JSONObject
    ): TaskResult {
        val deferred = CompletableDeferred<TaskResult>()
        currentDeferred = deferred

        val scriptContent = scriptManager.getAdapterScript(platform)
            ?: return TaskResult.Error("适配器脚本未找到: $platform")

        val engine = engineManager.createEngineOfSourceOrThrow(
            JavaScriptSource("adapter_${platform}", scriptContent)
        )
        try {
            engine.put("__scriptBridge__", this)
            engine.put("__platform__", platform)
            engine.put("__taskType__", taskType)
            engine.put("__params__", params.toString())
            engine.init()

            _taskState.value = TaskExecutionState.Running(platform, taskType, 0)

            val result = engine.execute(
                JavaScriptSource("adapter_${platform}", scriptContent)
            )

            val finalResult = parseResult(result)
            deferred.complete(finalResult)
            _taskState.value = TaskExecutionState.Idle
            return finalResult
        } catch (e: Exception) {
            val errorResult = TaskResult.Error(e.message ?: "脚本执行异常")
            deferred.complete(errorResult)
            _taskState.value = TaskExecutionState.Idle
            return errorResult
        } finally {
            engine.destroy()
        }
    }

    // 供脚本侧调用的回调方法
    fun onTaskProgress(message: String) {
        _taskState.value = (_taskState.value as? TaskExecutionState.Running)?.let {
            it.copy(progress = it.progress + 1)
        } ?: TaskExecutionState.Running("", "", 0)
        currentDeferred?.let {
            // 进度回调不完成 deferred，只更新状态
        }
    }

    fun onTaskLog(message: String, level: String = "info") {
        // 日志转发到 UI
    }

    fun onTaskComplete(data: String) {
        val current = currentDeferred
        currentDeferred = null
        current?.complete(TaskResult.Success(JSONObject(data)))
    }

    fun onTaskError(error: String) {
        val current = currentDeferred
        currentDeferred = null
        current?.complete(TaskResult.Error(error))
    }

    private fun parseResult(raw: Any?): TaskResult {
        return when (raw) {
            is String -> TaskResult.Success(JSONObject(raw))
            is JSONObject -> TaskResult.Success(raw)
            null -> TaskResult.Success(JSONObject())
            else -> TaskResult.Success(JSONObject(raw.toString()))
        }
    }
}

sealed class TaskExecutionState {
    object Idle : TaskExecutionState()
    data class Running(
        val platform: String,
        val taskType: String,
        val progress: Int
    ) : TaskExecutionState()
}
```

- [ ] **Step 2: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/engine/ScriptBridge.kt
git commit -m "feat: 创建 ScriptBridge 桥接层"
```

---

### Task 5: 创建 ScriptManager 脚本管理器

**Files:**
- Create: `saltfish/src/main/java/com/saltfish/assistant/engine/ScriptManager.kt`

- [ ] **Step 1: 编写 ScriptManager.kt**

```kotlin
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
     * 初始化：从 assets 解压内置适配器到 filesDir
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
     * 获取指定平台的适配器脚本内容
     */
    fun getAdapterScript(platform: String): String? {
        val platformDir = File(adaptersDir, platform)
        if (!platformDir.isDirectory) return null
        val adapterFile = File(platformDir, "index.js")
        if (!adapterFile.isFile) return null
        return adapterFile.readText()
    }

    /**
     * 获取 scriptBridge 注入脚本 (注入到每个 adapter scope)
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
     * 从云端下载 ZIP 更新适配器
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
```

- [ ] **Step 2: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/engine/ScriptManager.kt
git commit -m "feat: 创建 ScriptManager 脚本管理器"
```

---

### Task 6: 创建 ScriptService 前台服务

**Files:**
- Create: `saltfish/src/main/java/com/saltfish/assistant/service/ScriptService.kt`

- [ ] **Step 1: 编写 ScriptService.kt**

```kotlin
package com.saltfish.assistant.service

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.saltfish.assistant.MainActivity
import com.saltfish.assistant.SaltfishApp
import com.saltfish.assistant.domain.model.TaskEntity
import com.saltfish.assistant.engine.ScriptBridge
import com.saltfish.assistant.engine.TaskExecutionState
import kotlinx.coroutines.*
import org.json.JSONObject

class ScriptService : Service() {

    private val serviceScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private val app get() = applicationContext as SaltfishApp
    private var isRunning = false

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        startForeground(NOTIFICATION_ID, createNotification("咸鱼助手运行中"))
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (!isRunning) {
            isRunning = true
            app.scriptManager.initialize()
        }
        return START_STICKY
    }

    fun submitTask(task: TaskEntity, callback: (Boolean) -> Unit) {
        serviceScope.launch {
            try {
                val result = app.scriptBridge.executeTask(
                    task.platform,
                    task.taskType,
                    JSONObject(task.params)
                )
                callback(result is com.saltfish.assistant.domain.model.TaskResult.Success)
            } catch (e: Exception) {
                callback(false)
            }
        }
    }

    private fun createNotification(text: String) =
        NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("咸鱼助手")
            .setContentText(text)
            .setSmallIcon(android.R.drawable.ic_menu_manage)
            .setContentIntent(
                PendingIntent.getActivity(
                    this, 0,
                    Intent(this, MainActivity::class.java),
                    PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
                )
            )
            .build()

    override fun onDestroy() {
        serviceScope.cancel()
        isRunning = false
        super.onDestroy()
    }

    companion object {
        const val CHANNEL_ID = "saltfish_script"
        const val NOTIFICATION_ID = 1001
    }
}
```

- [ ] **Step 2: 创建 NotificationChannel 初始化**

Create `saltfish/src/main/java/com/saltfish/assistant/service/NotificationHelper.kt`:

```kotlin
package com.saltfish.assistant.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context

object NotificationHelper {
    fun createChannels(context: Context) {
        val channel = NotificationChannel(
            ScriptService.CHANNEL_ID,
            "脚本服务",
            NotificationManager.IMPORTANCE_LOW
        ).apply {
            description = "咸鱼助手脚本执行通知"
        }
        val manager = context.getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)
    }
}
```

- [ ] **Step 3: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/service/
git commit -m "feat: 创建 ScriptService 前台服务"
```

---

## Phase 3: 数据层

### Task 7: 创建网络层 (Retrofit + OkHttp)

**Files:**
- Create: `saltfish/src/main/java/com/saltfish/assistant/data/local/PreferencesManager.kt`
- Create: `saltfish/src/main/java/com/saltfish/assistant/data/remote/ApiClient.kt`
- Create: `saltfish/src/main/java/com/saltfish/assistant/data/remote/ApiService.kt`

- [ ] **Step 1: 编写 PreferencesManager.kt**

```kotlin
package com.saltfish.assistant.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class PreferencesManager(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("saltfish_prefs", Context.MODE_PRIVATE)

    var baseUrl: String
        get() = prefs.getString("base_url", "https://api.saltfish.example.com") ?: ""
        set(value) = prefs.edit { putString("base_url", value) }

    var token: String?
        get() = prefs.getString("token", null)
        set(value) = prefs.edit { putString("token", value) }

    var deviceId: String?
        get() = prefs.getString("device_id", null)
        set(value) = prefs.edit { putString("device_id", value) }

    var wsUrl: String
        get() = prefs.getString("ws_url", "wss://ws.saltfish.example.com") ?: ""
        set(value) = prefs.edit { putString("ws_url", value) }

    var autoUpgrade: Boolean
        get() = prefs.getBoolean("auto_upgrade", true)
        set(value) = prefs.edit { putBoolean("auto_upgrade", value) }

    var isFirstLaunch: Boolean
        get() = prefs.getBoolean("is_first_launch", true)
        set(value) = prefs.edit { putBoolean("is_first_launch", value) }

    fun logCrash(ex: Throwable) {
        val crashes = prefs.getStringSet("crashes", emptySet())?.toMutableSet() ?: mutableSetOf()
        crashes.add("${System.currentTimeMillis()}: ${ex.message}")
        if (crashes.size > 50) crashes.clear()
        prefs.edit { putStringSet("crashes", crashes) }
    }
}
```

- [ ] **Step 2: 编写 ApiService.kt**

```kotlin
package com.saltfish.assistant.data.remote

import com.google.gson.JsonObject
import retrofit2.http.*

interface ApiService {

    // 设备
    @POST("/admin/aios-saltfish/device/register")
    suspend fun registerDevice(@Body body: JsonObject): JsonObject

    @POST("/admin/aios-saltfish/device/heartbeat")
    suspend fun deviceHeartbeat(@Body body: JsonObject): JsonObject

    // 账号
    @POST("/admin/aios-saltfish/account/add")
    suspend fun addAccount(@Body body: JsonObject): JsonObject

    @POST("/admin/aios-saltfish/account/update")
    suspend fun updateAccount(@Body body: JsonObject): JsonObject

    @GET("/admin/aios-saltfish/account/list")
    suspend fun getAccounts(@Query("deviceId") deviceId: String): JsonObject

    // 任务
    @GET("/admin/aios-saltfish/task/list")
    suspend fun getTasks(@Query("deviceId") deviceId: String): JsonObject

    @POST("/admin/aios-saltfish/task/report")
    suspend fun reportTaskResult(@Body body: JsonObject): JsonObject

    // 升级
    @GET("/admin/aios-saltfish/upgrade/check")
    suspend fun checkUpgrade(
        @Query("scriptVersion") scriptVersion: String,
        @Query("packageName") packageName: String
    ): JsonObject

    // 配置
    @GET("/admin/aios-saltfish/cloud-config")
    suspend fun getCloudConfig(@Query("deviceId") deviceId: String): JsonObject
}
```

- [ ] **Step 3: 编写 ApiClient.kt**

```kotlin
package com.saltfish.assistant.data.remote

import com.google.gson.GsonBuilder
import com.saltfish.assistant.data.local.PreferencesManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient(private val prefs: PreferencesManager) {

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                prefs.token?.let { request.addHeader("Authorization", "Bearer $it") }
                request.build().let { chain.proceed(it) }
            }
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(prefs.baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    val api: ApiService by lazy { retrofit.create(ApiService::class.java) }
}
```

- [ ] **Step 4: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/data/
git commit -m "feat: 创建网络层（Retrofit + Preferences）"
```

---

### Task 8: 创建 Socket.IO 管理器

**Files:**
- Create: `saltfish/src/main/java/com/saltfish/assistant/data/remote/SocketIOManager.kt`

- [ ] **Step 1: 编写 SocketIOManager.kt**

```kotlin
package com.saltfish.assistant.data.remote

import com.saltfish.assistant.data.local.PreferencesManager
import io.socket.client.IO
import io.socket.client.Socket
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import org.json.JSONObject

class SocketIOManager(private val prefs: PreferencesManager) {

    private var socket: Socket? = null

    private val _connectionState = MutableStateFlow(ConnectionState.DISCONNECTED)
    val connectionState: StateFlow<ConnectionState> = _connectionState

    private val _eventChannel = Channel<SocketEvent>(Channel.BUFFERED)
    val events: Flow<SocketEvent> = _eventChannel.receiveAsFlow()

    private var retryCount = 0
    private val maxRetries = 20000

    fun connect() {
        if (_connectionState.value == ConnectionState.CONNECTED) return

        _connectionState.value = ConnectionState.CONNECTING

        try {
            val options = IO.Options().apply {
                reconnection = true
                reconnectionAttempts = Int.MAX_VALUE
                reconnectionDelay = 20_000
                reconnectionDelayMax = 20_000
                timeout = 30_000
            }

            socket = IO.socket(prefs.wsUrl, options).apply {
                on(Socket.EVENT_CONNECT) {
                    _connectionState.value = ConnectionState.CONNECTED
                    retryCount = 0
                }

                on(Socket.EVENT_DISCONNECT) {
                    _connectionState.value = ConnectionState.DISCONNECTED
                }

                on(Socket.EVENT_CONNECT_ERROR) {
                    retryCount++
                    _connectionState.value = ConnectionState.RECONNECTING
                }

                on("task:execute") { args ->
                    if (args.isNotEmpty()) {
                        val data = JSONObject(args[0].toString())
                        _eventChannel.trySend(SocketEvent.TaskExecute(data))
                    }
                }

                on("config:update") { args ->
                    if (args.isNotEmpty()) {
                        val data = JSONObject(args[0].toString())
                        _eventChannel.trySend(SocketEvent.ConfigUpdate(data))
                    }
                }

                on("upgrade:adapter") { args ->
                    if (args.isNotEmpty()) {
                        val data = JSONObject(args[0].toString())
                        _eventChannel.trySend(SocketEvent.AdapterUpgrade(data))
                    }
                }

                connect()
            }
        } catch (e: Exception) {
            _connectionState.value = ConnectionState.DISCONNECTED
        }
    }

    fun disconnect() {
        socket?.disconnect()
        socket?.off()
        socket = null
        _connectionState.value = ConnectionState.DISCONNECTED
    }

    fun emit(event: String, data: JSONObject) {
        socket?.emit(event, data)
    }

    enum class ConnectionState { DISCONNECTED, CONNECTING, CONNECTED, RECONNECTING }
}

sealed class SocketEvent {
    data class TaskExecute(val data: JSONObject) : SocketEvent()
    data class ConfigUpdate(val data: JSONObject) : SocketEvent()
    data class AdapterUpgrade(val data: JSONObject) : SocketEvent()
}
```

- [ ] **Step 2: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/data/remote/SocketIOManager.kt
git commit -m "feat: 创建 Socket.IO 管理器"
```

---

### Task 9: 创建 Room 数据库

**Files:**
- Create: `saltfish/src/main/java/com/saltfish/assistant/data/local/AppDatabase.kt`
- Create: `saltfish/src/main/java/com/saltfish/assistant/data/local/TaskDao.kt`

- [ ] **Step 1: 先创建领域模型**

Create `saltfish/src/main/java/com/saltfish/assistant/domain/model/TaskEntity.kt`:

```kotlin
package com.saltfish.assistant.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey val id: Long = 0,
    val platform: String,
    val taskType: String,
    val params: String,
    val priority: Int = 0,
    val status: TaskStatus = TaskStatus.PENDING,
    val createdAt: Long = System.currentTimeMillis(),
    val startedAt: Long? = null,
    val completedAt: Long? = null,
    val errorMessage: String? = null,
    val retryCount: Int = 0,
    val maxRetries: Int = 3
)

enum class TaskStatus { PENDING, RUNNING, COMPLETED, FAILED, CANCELLED }
```

Create `saltfish/src/main/java/com/saltfish/assistant/domain/model/TaskResult.kt`:

```kotlin
package com.saltfish.assistant.domain.model

import org.json.JSONObject

sealed class TaskResult {
    data class Success(val data: JSONObject = JSONObject()) : TaskResult()
    data class Error(val message: String) : TaskResult()
}
```

Create `saltfish/src/main/java/com/saltfish/assistant/domain/model/DeviceInfo.kt`:

```kotlin
package com.saltfish.assistant.domain.model

data class DeviceInfo(
    val id: Long = 0,
    val uuid: String = "",
    val name: String = "",
    val model: String = "",
    val brand: String = "",
    val androidVersion: String = "",
    val isRooted: Boolean = false,
    val availableMemory: String = "",
    val batteryLevel: Int = 0
)
```

Create `saltfish/src/main/java/com/saltfish/assistant/domain/model/AccountInfo.kt`:

```kotlin
package com.saltfish.assistant.domain.model

data class AccountInfo(
    val id: Long = 0,
    val platform: String = "",
    val accountName: String = "",
    val nickName: String = "",
    val status: String = "",
    val deviceId: Long = 0
)
```

- [ ] **Step 2: 编写 TaskDao.kt**

```kotlin
package com.saltfish.assistant.data.local

import androidx.room.*
import com.saltfish.assistant.domain.model.TaskEntity
import com.saltfish.assistant.domain.model.TaskStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks ORDER BY priority DESC, createdAt ASC")
    fun observeAll(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE status = 'PENDING' ORDER BY priority DESC, createdAt ASC LIMIT 1")
    suspend fun getNextPending(): TaskEntity?

    @Query("SELECT * FROM tasks WHERE id = :id")
    suspend fun getById(id: Long): TaskEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: TaskEntity): Long

    @Update
    suspend fun update(task: TaskEntity)

    @Query("UPDATE tasks SET status = :status, errorMessage = :error WHERE id = :id")
    suspend fun updateStatus(id: Long, status: TaskStatus, error: String? = null)

    @Query("DELETE FROM tasks WHERE status IN ('COMPLETED', 'FAILED', 'CANCELLED') AND completedAt < :before")
    suspend fun deleteOldCompleted(before: Long)
}
```

- [ ] **Step 3: 编写 AppDatabase.kt**

```kotlin
package com.saltfish.assistant.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.saltfish.assistant.domain.model.TaskEntity

@Database(entities = [TaskEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "saltfish.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
```

- [ ] **Step 4: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/domain/ saltfish/src/main/java/com/saltfish/assistant/data/local/
git commit -m "feat: 创建领域模型 + Room 数据库"
```

---

### Task 10: 创建 Repository 层

**Files:**
- Create: `saltfish/src/main/java/com/saltfish/assistant/data/repository/DeviceRepository.kt`
- Create: `saltfish/src/main/java/com/saltfish/assistant/data/repository/AccountRepository.kt`
- Create: `saltfish/src/main/java/com/saltfish/assistant/data/repository/TaskRepository.kt`

- [ ] **Step 1: 编写 DeviceRepository.kt**

```kotlin
package com.saltfish.assistant.data.repository

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.saltfish.assistant.data.local.PreferencesManager
import com.saltfish.assistant.data.remote.ApiClient
import com.saltfish.assistant.domain.model.DeviceInfo

class DeviceRepository(
    private val apiClient: ApiClient,
    private val prefs: PreferencesManager
) {
    private val gson = Gson()

    suspend fun registerDevice(deviceInfo: DeviceInfo): Boolean {
        return try {
            val json = gson.toJsonTree(deviceInfo).asJsonObject
            apiClient.api.registerDevice(json)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun sendHeartbeat(): Boolean {
        return try {
            val body = JsonObject().apply {
                addProperty("deviceId", prefs.deviceId)
                addProperty("timestamp", System.currentTimeMillis())
            }
            apiClient.api.deviceHeartbeat(body)
            true
        } catch (e: Exception) {
            false
        }
    }
}
```

- [ ] **Step 2: 编写 AccountRepository.kt**

```kotlin
package com.saltfish.assistant.data.repository

import com.google.gson.Gson
import com.saltfish.assistant.data.local.PreferencesManager
import com.saltfish.assistant.data.remote.ApiClient
import com.saltfish.assistant.domain.model.AccountInfo

class AccountRepository(
    private val apiClient: ApiClient,
    private val prefs: PreferencesManager
) {
    private val gson = Gson()

    suspend fun addAccount(account: AccountInfo): Boolean {
        return try {
            val json = gson.toJsonTree(account).asJsonObject
            apiClient.api.addAccount(json)
            true
        } catch (e: Exception) { false }
    }

    suspend fun getAccounts(): List<AccountInfo> {
        return try {
            val response = apiClient.api.getAccounts(prefs.deviceId ?: "")
            val arr = response.getAsJsonArray("data")
            arr.map { gson.fromJson(it, AccountInfo::class.java) }
        } catch (e: Exception) { emptyList() }
    }
}
```

- [ ] **Step 3: 编写 TaskRepository.kt**

```kotlin
package com.saltfish.assistant.data.repository

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.saltfish.assistant.data.local.AppDatabase
import com.saltfish.assistant.data.local.PreferencesManager
import com.saltfish.assistant.data.remote.ApiClient
import com.saltfish.assistant.domain.model.TaskEntity
import com.saltfish.assistant.domain.model.TaskStatus
import kotlinx.coroutines.flow.Flow

class TaskRepository(
    private val apiClient: ApiClient,
    private val prefs: PreferencesManager,
    private val db: AppDatabase
) {
    private val gson = Gson()

    fun observeTasks(): Flow<List<TaskEntity>> = db.taskDao().observeAll()

    suspend fun getNextPending(): TaskEntity? = db.taskDao().getNextPending()

    suspend fun enqueue(task: TaskEntity): Long {
        val id = db.taskDao().insert(task.copy(status = TaskStatus.PENDING))
        return id
    }

    suspend fun updateStatus(id: Long, status: TaskStatus, error: String? = null) {
        db.taskDao().updateStatus(id, status, error)
    }

    suspend fun reportResult(task: TaskEntity): Boolean {
        return try {
            val body = JsonObject().apply {
                addProperty("taskId", task.id)
                addProperty("status", task.status.name)
                task.errorMessage?.let { addProperty("error", it) }
            }
            apiClient.api.reportTaskResult(body)
            true
        } catch (e: Exception) { false }
    }

    suspend fun fetchTasks(): List<TaskEntity> {
        return try {
            val response = apiClient.api.getTasks(prefs.deviceId ?: "")
            val arr = response.getAsJsonArray("data")
            arr.map { gson.fromJson(it, TaskEntity::class.java) }
        } catch (e: Exception) { emptyList() }
    }
}
```

- [ ] **Step 4: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/data/repository/
git commit -m "feat: 创建 Repository 层"
```

---

## Phase 4: ViewModels

### Task 11: 创建 HomeViewModel

**Files:**
- Create: `saltfish/src/main/java/com/saltfish/assistant/ui/home/HomeViewModel.kt`

- [ ] **Step 1: 编写 HomeViewModel.kt**

```kotlin
package com.saltfish.assistant.ui.home

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.provider.Settings
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.saltfish.assistant.SaltfishApp
import com.saltfish.assistant.data.remote.SocketIOManager.ConnectionState
import com.saltfish.assistant.engine.TaskExecutionState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.io.File

data class HomeUiState(
    val wsState: ConnectionState = ConnectionState.DISCONNECTED,
    val taskState: TaskExecutionState = TaskExecutionState.Idle,
    val deviceName: String = "",
    val availableMemory: String = "",
    val batteryLevel: Int = 0,
    val isAccessibilityEnabled: Boolean = false,
    val isFloatyPermissionGranted: Boolean = false,
    val isIgnoringBattery: Boolean = false,
    val username: String = "",
    val pendingTaskCount: Int = 0,
    val adapterVersions: Map<String, String> = emptyMap()
)

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val app = application as SaltfishApp

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                app.socketIOManager.connectionState,
                app.scriptBridge.taskState,
                app.scriptManager.adapterVersions
            ) { ws, task, adapters ->
                HomeUiState(
                    wsState = ws,
                    taskState = task,
                    deviceName = "${android.os.Build.BRAND} ${android.os.Build.MODEL}",
                    availableMemory = getAvailMem(),
                    batteryLevel = getBatteryLevel(),
                    isAccessibilityEnabled = isAccessibilityServiceEnabled(),
                    isFloatyPermissionGranted = Settings.canDrawOverlays(app),
                    isIgnoringBattery = isIgnoringBatteryOptimizations(),
                    username = app.preferencesManager.token?.let { "已登录" } ?: "未登录",
                    pendingTaskCount = 0, // will be updated by task VM
                    adapterVersions = adapters
                )
            }.collect { _uiState.value = it }
        }
    }

    fun connectWebSocket() = app.socketIOManager.connect()
    fun disconnectWebSocket() = app.socketIOManager.disconnect()

    private fun getAvailMem(): String {
        val memInfo = File("/proc/meminfo")
        if (!memInfo.exists()) return "N/A"
        val lines = memInfo.readLines()
        for (line in lines) {
            if (line.startsWith("MemAvailable:")) {
                val kb = line.replace(Regex("[^0-9]"), "").toLongOrNull() ?: 0
                return when {
                    kb > 1024 * 1024 -> "%.1f GB".format(kb / (1024.0 * 1024.0))
                    else -> "%.0f MB".format(kb / 1024.0)
                }
            }
        }
        return "N/A"
    }

    private fun getBatteryLevel(): Int {
        val intent = app.registerReceiver(null,
            IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        val level = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
        val scale = intent?.getIntExtra(BatteryManager.EXTRA_SCALE, -1) ?: -1
        return if (scale > 0) (level * 100 / scale) else 0
    }

    private fun isAccessibilityServiceEnabled(): Boolean {
        val service = "${app.packageName}/com.stardust.autojs.core.accessibility.AccessibilityService"
        val enabledServices = Settings.Secure.getString(
            app.contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
        ) ?: ""
        return enabledServices.contains(service)
    }

    private fun isIgnoringBatteryOptimizations(): Boolean {
        val powerManager = app.getSystemService(Context.POWER_SERVICE) as android.os.PowerManager
        return powerManager.isIgnoringBatteryOptimizations(app.packageName)
    }
}
```

- [ ] **Step 2: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/ui/home/
git commit -m "feat: 创建 HomeViewModel"
```

---

### Task 12: 创建 TaskViewModel

**Files:**
- Create: `saltfish/src/main/java/com/saltfish/assistant/ui/task/TaskViewModel.kt`

- [ ] **Step 1: 编写 TaskViewModel.kt**

```kotlin
package com.saltfish.assistant.ui.task

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.saltfish.assistant.SaltfishApp
import com.saltfish.assistant.data.remote.SocketEvent
import com.saltfish.assistant.domain.model.TaskEntity
import com.saltfish.assistant.domain.model.TaskStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject

data class TaskUiState(
    val tasks: List<TaskEntity> = emptyList(),
    val currentTask: TaskEntity? = null,
    val logs: List<String> = emptyList()
)

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val app = application as SaltfishApp

    private val _uiState = MutableStateFlow(TaskUiState())
    val uiState: StateFlow<TaskUiState> = _uiState.asStateFlow()

    init {
        // 监听本地任务队列
        viewModelScope.launch {
            app.taskRepository.observeTasks().collect { tasks ->
                _uiState.value = _uiState.value.copy(tasks = tasks)
            }
        }
        // 监听 Socket.IO 下发任务
        viewModelScope.launch {
            app.socketIOManager.events.collect { event ->
                when (event) {
                    is SocketEvent.TaskExecute -> handleRemoteTask(event.data)
                    else -> {}
                }
            }
        }
        // 同步云端任务
        viewModelScope.launch {
            val remoteTasks = app.taskRepository.fetchTasks()
            remoteTasks.forEach { app.taskRepository.enqueue(it) }
        }
    }

    private fun handleRemoteTask(data: JSONObject) {
        val task = TaskEntity(
            platform = data.optString("platform"),
            taskType = data.optString("taskType"),
            params = data.optJSONObject("params")?.toString() ?: "{}",
            priority = data.optInt("priority", 0)
        )
        viewModelScope.launch {
            app.taskRepository.enqueue(task)
        }
    }

    fun cancelTask(taskId: Long) {
        viewModelScope.launch {
            app.taskRepository.updateStatus(taskId, TaskStatus.CANCELLED)
        }
    }

    fun removeTask(taskId: Long) {
        viewModelScope.launch {
            app.taskRepository.updateStatus(taskId, TaskStatus.CANCELLED)
        }
    }
}
```

- [ ] **Step 2: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/ui/task/
git commit -m "feat: 创建 TaskViewModel"
```

---

## Phase 5: UI 层

### Task 13: 创建 Material 3 主题

**Files:**
- Create: `saltfish/src/main/java/com/saltfish/assistant/ui/theme/Theme.kt`
- Create: `saltfish/src/main/java/com/saltfish/assistant/ui/theme/Color.kt`

- [ ] **Step 1: 编写 Color.kt**

```kotlin
package com.saltfish.assistant.ui.theme

import androidx.compose.ui.graphics.Color

// 咸鱼黄色主题
val Yellow500 = Color(0xFFFFC107)
val Yellow700 = Color(0xFFFFA000)
val Yellow300 = Color(0xFFFFD54F)

val Orange500 = Color(0xFFFF9800)
val Green500 = Color(0xFF4CAF50)
val Red500 = Color(0xFFF44336)
val Blue500 = Color(0xFF2196F3)

val DarkSurface = Color(0xFF1E1E1E)
val DarkBackground = Color(0xFF121212)
val LightSurface = Color(0xFFFFFBFE)
val LightBackground = Color(0xFFF5F5F5)
```

- [ ] **Step 2: 编写 Theme.kt**

```kotlin
package com.saltfish.assistant.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColors(
    primary = Yellow700,
    primaryVariant = Orange500,
    secondary = Blue500,
    background = LightBackground,
    surface = LightSurface,
    error = Red500
)

private val DarkColorScheme = darkColors(
    primary = Yellow300,
    primaryVariant = Orange500,
    secondary = Blue500,
    background = DarkBackground,
    surface = DarkSurface,
    error = Red500
)

@Composable
fun SaltfishTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) DarkColorScheme else LightColorScheme,
        content = content
    )
}
```

- [ ] **Step 3: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/ui/theme/
git commit -m "feat: 创建 Material 主题"
```

---

### Task 14: 创建 MainActivity + 导航

**Files:**
- Create: `saltfish/src/main/java/com/saltfish/assistant/MainActivity.kt`
- Create: `saltfish/src/main/java/com/saltfish/assistant/ui/navigation/NavGraph.kt`

- [ ] **Step 1: 编写 MainActivity.kt**

```kotlin
package com.saltfish.assistant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.saltfish.assistant.ui.navigation.SaltfishNavGraph
import com.saltfish.assistant.ui.theme.SaltfishTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SaltfishTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SaltfishNavGraph()
                }
            }
        }
    }
}
```

- [ ] **Step 2: 编写 NavGraph.kt**

```kotlin
package com.saltfish.assistant.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.saltfish.assistant.ui.settings.SettingsScreen
import com.saltfish.assistant.ui.home.HomeScreen
import com.saltfish.assistant.ui.task.TaskScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Task : Screen("task")
    object Automation : Screen("automation")
    object Settings : Screen("settings")
}

@Composable
fun SaltfishNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.Task.route) {
            TaskScreen()
        }
        composable(Screen.Automation.route) {
            // AutomationScreen()
        }
        composable(Screen.Settings.route) {
            SettingsScreen()
        }
    }
}
```

- [ ] **Step 3: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/MainActivity.kt saltfish/src/main/java/com/saltfish/assistant/ui/navigation/
git commit -m "feat: 创建 MainActivity + 导航图"
```

---

### Task 15: 创建 HomeScreen

**Files:**
- Create: `saltfish/src/main/java/com/saltfish/assistant/ui/home/HomeScreen.kt`
- Create: `saltfish/src/main/java/com/saltfish/assistant/ui/components/StatusCard.kt`
- Create: `saltfish/src/main/java/com/saltfish/assistant/ui/components/PermissionItem.kt`

- [ ] **Step 1: 编写 StatusCard.kt**

```kotlin
package com.saltfish.assistant.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StatusCard(
    title: String,
    value: String,
    statusColor: androidx.compose.ui.graphics.Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = 2.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, fontSize = 13.sp, color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f))
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    modifier = Modifier.size(8.dp),
                    shape = MaterialTheme.shapes.small,
                    color = statusColor
                ) {}
                Spacer(modifier = Modifier.width(8.dp))
                Text(value, fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
        }
    }
}

@Composable
fun PermissionItem(
    label: String,
    isGranted: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(8.dp),
                shape = MaterialTheme.shapes.small,
                color = if (isGranted) {
                    androidx.compose.ui.graphics.Color(0xFF4CAF50)
                } else {
                    androidx.compose.ui.graphics.Color(0xFFFF5722)
                }
            ) {}
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = label,
                modifier = Modifier.weight(1f),
                fontSize = 15.sp
            )
            Text(
                text = if (isGranted) "已授权" else "未授权",
                fontSize = 13.sp,
                color = if (isGranted) {
                    androidx.compose.ui.graphics.Color(0xFF4CAF50)
                } else {
                    androidx.compose.ui.graphics.Color(0xFFFF5722)
                }
            )
        }
    }
}
```

- [ ] **Step 2: 编写 HomeScreen.kt**

```kotlin
package com.saltfish.assistant.ui.home

import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.saltfish.assistant.data.remote.SocketIOManager.ConnectionState
import com.saltfish.assistant.engine.TaskExecutionState
import com.saltfish.assistant.ui.components.PermissionItem
import com.saltfish.assistant.ui.components.StatusCard
import com.saltfish.assistant.ui.navigation.Screen

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("咸鱼助手") },
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.Settings.route) }) {
                        Icon(Icons.Default.Settings, contentDescription = "设置")
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    selected = true,
                    onClick = {},
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    label = { Text("首页") }
                )
                BottomNavigationItem(
                    selected = false,
                    onClick = { navController.navigate(Screen.Task.route) },
                    icon = { Icon(Icons.Default.List, contentDescription = null) },
                    label = { Text("任务") }
                )
                BottomNavigationItem(
                    selected = false,
                    onClick = {},
                    icon = { Icon(Icons.Default.PlayArrow, contentDescription = null) },
                    label = { Text("自动化") }
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // 连接状态
            StatusCard(
                title = "服务器连接",
                value = when (uiState.wsState) {
                    ConnectionState.CONNECTED -> "已连接"
                    ConnectionState.CONNECTING -> "连接中..."
                    ConnectionState.RECONNECTING -> "重连中..."
                    ConnectionState.DISCONNECTED -> "未连接"
                },
                statusColor = when (uiState.wsState) {
                    ConnectionState.CONNECTED -> Color(0xFF4CAF50)
                    else -> Color(0xFFFF5722)
                }
            )

            // 任务状态
            StatusCard(
                title = "当前任务",
                value = when (val ts = uiState.taskState) {
                    is TaskExecutionState.Idle -> "空闲"
                    is TaskExecutionState.Running -> "运行中: ${ts.taskType}"
                },
                statusColor = when (uiState.taskState) {
                    is TaskExecutionState.Idle -> Color(0xFF9E9E9E)
                    is TaskExecutionState.Running -> Color(0xFF2196F3)
                }
            )

            // 设备信息
            Card(elevation = 2.dp) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("设备信息", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("设备: ${uiState.deviceName}", fontSize = 14.sp)
                    Text("内存: ${uiState.availableMemory}", fontSize = 14.sp)
                    Text("电量: ${uiState.batteryLevel}%", fontSize = 14.sp)
                    Text("用户: ${uiState.username}", fontSize = 14.sp)
                }
            }

            // 权限状态
            Card(elevation = 2.dp) {
                Column {
                    Text(
                        "系统权限",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(16.dp, 12.dp)
                    )
                    PermissionItem(
                        label = "无障碍服务",
                        isGranted = uiState.isAccessibilityEnabled,
                        onClick = {
                            context.startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
                        }
                    )
                    PermissionItem(
                        label = "悬浮窗权限",
                        isGranted = uiState.isFloatyPermissionGranted,
                        onClick = {
                            context.startActivity(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION))
                        }
                    )
                    PermissionItem(
                        label = "电池优化白名单",
                        isGranted = uiState.isIgnoringBattery,
                        onClick = {
                            context.startActivity(Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS))
                        }
                    )
                }
            }

            // 适配器版本
            if (uiState.adapterVersions.isNotEmpty()) {
                Card(elevation = 2.dp) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("适配器版本", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        uiState.adapterVersions.forEach { (platform, version) ->
                            Text("$platform: v$version", fontSize = 13.sp)
                        }
                    }
                }
            }

            // WebSocket 控制按钮
            Button(
                onClick = {
                    if (uiState.wsState == ConnectionState.CONNECTED) {
                        viewModel.disconnectWebSocket()
                    } else {
                        viewModel.connectWebSocket()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    if (uiState.wsState == ConnectionState.CONNECTED) "断开连接" else "连接服务器"
                )
            }
        }
    }
}
```

- [ ] **Step 3: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/ui/home/ saltfish/src/main/java/com/saltfish/assistant/ui/components/
git commit -m "feat: 创建 HomeScreen + 公共组件"
```

---

### Task 16: 创建 TaskScreen

**Files:**
- Create: `saltfish/src/main/java/com/saltfish/assistant/ui/task/TaskScreen.kt`

- [ ] **Step 1: 编写 TaskScreen.kt**

```kotlin
package com.saltfish.assistant.ui.task

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.saltfish.assistant.domain.model.TaskEntity
import com.saltfish.assistant.domain.model.TaskStatus

@Composable
fun TaskScreen(viewModel: TaskViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("任务队列") }
            )
        }
    ) { padding ->
        if (uiState.tasks.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                Text("暂无任务", color = Color.Gray, fontSize = 16.sp)
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(uiState.tasks) { task ->
                    TaskItem(task = task, onCancel = { viewModel.cancelTask(task.id) })
                }
            }
        }
    }
}

@Composable
fun TaskItem(task: TaskEntity, onCancel: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = task.taskType,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "平台: ${task.platform} | 优先级: ${task.priority}",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Text(
                    text = "状态: ${statusText(task.status)}",
                    fontSize = 13.sp,
                    color = statusColor(task.status)
                )
                task.errorMessage?.let {
                    Text(
                        text = it,
                        fontSize = 12.sp,
                        color = Color(0xFFF44336),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            if (task.status == TaskStatus.PENDING || task.status == TaskStatus.RUNNING) {
                IconButton(onClick = onCancel) {
                    Icon(Icons.Default.Delete, contentDescription = "取消", tint = Color.Gray)
                }
            }
        }
    }
}

private fun statusText(status: TaskStatus): String = when (status) {
    TaskStatus.PENDING -> "等待中"
    TaskStatus.RUNNING -> "执行中"
    TaskStatus.COMPLETED -> "已完成"
    TaskStatus.FAILED -> "失败"
    TaskStatus.CANCELLED -> "已取消"
}

private fun statusColor(status: TaskStatus): Color = when (status) {
    TaskStatus.PENDING -> Color(0xFFFFA000)
    TaskStatus.RUNNING -> Color(0xFF2196F3)
    TaskStatus.COMPLETED -> Color(0xFF4CAF50)
    TaskStatus.FAILED -> Color(0xFFF44336)
    TaskStatus.CANCELLED -> Color(0xFF9E9E9E)
}
```

- [ ] **Step 2: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/ui/task/TaskScreen.kt
git commit -m "feat: 创建 TaskScreen"
```

---

### Task 17: 创建 SettingsScreen

**Files:**
- Create: `saltfish/src/main/java/com/saltfish/assistant/ui/settings/SettingsScreen.kt`

- [ ] **Step 1: 编写 SettingsScreen.kt**

```kotlin
package com.saltfish.assistant.ui.settings

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saltfish.assistant.SaltfishApp

@Composable
fun SettingsScreen() {
    val context = LocalContext.current
    val app = context.applicationContext as SaltfishApp
    var baseUrl by remember { mutableStateOf(app.preferencesManager.baseUrl) }
    var wsUrl by remember { mutableStateOf(app.preferencesManager.wsUrl) }
    var autoUpgrade by remember { mutableStateOf(app.preferencesManager.autoUpgrade) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("设置") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 服务器设置
            Text("服务器配置", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            OutlinedTextField(
                value = baseUrl,
                onValueChange = { baseUrl = it },
                label = { Text("API 地址") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = wsUrl,
                onValueChange = { wsUrl = it },
                label = { Text("WebSocket 地址") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Divider()

            // 自动更新
            Text("更新设置", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Row(modifier = Modifier.fillMaxWidth()) {
                Text("自动更新", modifier = Modifier.weight(1f), fontSize = 15.sp)
                Switch(
                    checked = autoUpgrade,
                    onCheckedChange = {
                        autoUpgrade = it
                        app.preferencesManager.autoUpgrade = it
                    }
                )
            }

            Divider()

            // 调试操作
            Text("调试", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Button(
                onClick = {
                    app.preferencesManager.baseUrl = baseUrl
                    app.preferencesManager.wsUrl = wsUrl
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("保存设置")
            }

            // 缓存管理
            OutlinedButton(
                onClick = {
                    context.cacheDir.deleteRecursively()
                    context.cacheDir.mkdirs()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("清除缓存")
            }

            Divider()

            Text("关于", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text("咸鱼助手 v1.0.0", fontSize = 14.sp)
            Text("基于 AutoX 引擎", fontSize = 12.sp, color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f))
        }
    }
}
```

- [ ] **Step 2: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/ui/settings/
git commit -m "feat: 创建 SettingsScreen"
```

---

## Phase 6: 集成收尾

### Task 18: 添加适配器脚本到 assets

- [ ] **Step 1: 创建 assets 目录并添加示例 adapter**

```bash
mkdir -p D:\code\AutoX\saltfish\src\main\assets\adapters\com.taobao.idlefish\7.26.30
```

Create `saltfish/src/main/assets/adapters/com.taobao.idlefish/7.26.30/index.js`:

```js
// 闲鱼适配器脚本 - 示例框架
// 由 ScriptBridge 注入: scriptBridge, __platform__, __taskType__, __params__

function main() {
    scriptBridge.onTaskProgress("开始执行闲鱼自动化任务: " + __taskType__);

    try {
        var params = JSON.parse(__params__);

        switch (__taskType__) {
            case "post_goods":
                // 自动化发帖逻辑
                scriptBridge.onTaskProgress("正在发布商品...");
                auto.waitFor();
                // 具体操作...
                scriptBridge.onTaskComplete(JSON.stringify({ success: true, msg: "发布成功" }));
                break;
            case "refresh":
                scriptBridge.onTaskProgress("正在刷新...");
                auto.waitFor();
                scriptBridge.onTaskComplete(JSON.stringify({ success: true, msg: "刷新成功" }));
                break;
            case "collect_coin":
                scriptBridge.onTaskProgress("正在领取闲鱼币...");
                auto.waitFor();
                scriptBridge.onTaskComplete(JSON.stringify({ success: true, msg: "领取成功" }));
                break;
            default:
                scriptBridge.onTaskError("未知任务类型: " + __taskType__);
        }
    } catch (e) {
        scriptBridge.onTaskError("脚本异常: " + e.message);
    }
}

main();
```

Create `saltfish/src/main/assets/adapters/com.taobao.idlefish/7.26.30/version.json`:

```json
{"platform": "com.taobao.idlefish", "version": "7.26.30"}
```

- [ ] **Step 2: Commit**

```bash
git add saltfish/src/main/assets/
git commit -m "feat: 添加闲鱼适配器脚本示例"
```

---

### Task 19: 创建 ProGuard 规则

**Files:**
- Create: `saltfish/proguard-rules.pro`

- [ ] **Step 1: 编写 proguard-rules.pro**

```
# AutoX 核心
-keep class com.stardust.autojs.** { *; }
-keep class com.stardust.automator.** { *; }
-keep class com.stardust.** { *; }

# Rhino 引擎
-keep class org.mozilla.javascript.** { *; }

# Saltfish model (Gson)
-keep class com.saltfish.assistant.domain.model.** { *; }

# Room entities
-keep class * extends androidx.room.RoomDatabase { *; }

# ScriptInterface 注解的方法
-keepclassmembers class * {
    @com.stardust.autojs.annotation.ScriptInterface *;
}

# Socket.IO
-keep class io.socket.** { *; }
-keep class org.json.** { *; }
-keep class com.google.gson.** { *; }

# EventBus
-keepclassmembers class * {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
```

- [ ] **Step 2: Commit**

```bash
git add saltfish/proguard-rules.pro
git commit -m "feat: 添加 ProGuard 规则"
```

---

### Task 20: 创建 BootReceiver

**Files:**
- Create: `saltfish/src/main/java/com/saltfish/assistant/receiver/BootReceiver.kt`

- [ ] **Step 1: 编写 BootReceiver.kt**

```kotlin
package com.saltfish.assistant.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.saltfish.assistant.SaltfishApp
import com.saltfish.assistant.service.ScriptService

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            val app = context.applicationContext as SaltfishApp
            if (app.preferencesManager.token != null) {
                val serviceIntent = Intent(context, ScriptService::class.java)
                context.startForegroundService(serviceIntent)
            }
        }
    }
}
```

- [ ] **Step 2: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/receiver/
git commit -m "feat: 添加开机自启 BroadcastReceiver"
```

---

### Task 21: 验证构建

- [ ] **Step 1: 构建 saltfish debug APK**

```bash
cd D:\code\AutoX && ./gradlew :saltfish:assembleDebug 2>&1 | tail -30
```

Expected: BUILD SUCCESSFUL，产出 `saltfish/build/outputs/apk/debug/saltfish-debug.apk`

- [ ] **Step 2: 修复构建问题（如有）**

检查编译错误、缺失依赖等，逐个修复。

---

## 后续迭代任务（不在本次计划）

这些任务后续按需实现：

1. **任务调度器 (TaskScheduler)**: 在 ScriptService 中实现串行任务队列 + 超时看门狗
2. **热更新完整实现**: UpgradeManager + 下载 + 重启
3. **AutomationScreen**: 自动化控制面板 UI
4. **PermissionsGuideScreen**: 首次启动权限引导向导
5. **完整 adapter 脚本移植**: 从 client-autojs 移植全部 6 个平台的适配器
6. **DeviceInfo 完整采集**: UUID 生成、Root 检测等
7. **通知渠道详细配置**: 任务完成、错误等通知
8. **日志面板**: 悬浮日志窗口
