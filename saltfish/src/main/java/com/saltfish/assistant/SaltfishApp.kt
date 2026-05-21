package com.saltfish.assistant

import android.app.Application
import android.content.Context
import com.saltfish.assistant.data.local.AppDatabase
import com.saltfish.assistant.data.local.DeviceInfoCollector
import com.saltfish.assistant.data.local.PreferencesManager
import com.saltfish.assistant.data.remote.ApiClient
import com.saltfish.assistant.data.remote.SocketIOManager
import com.saltfish.assistant.data.repository.AccountRepository
import com.saltfish.assistant.data.repository.DeviceRepository
import com.saltfish.assistant.data.repository.TaskRepository
import com.saltfish.assistant.engine.ScriptBridge
import com.saltfish.assistant.engine.ScriptManager
import com.saltfish.assistant.engine.UpgradeManager
import com.saltfish.assistant.service.NotificationHelper
import com.stardust.app.GlobalAppContext
import com.stardust.autojs.engine.ScriptEngineManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class SaltfishApp : Application() {

    lateinit var scriptEngineManager: ScriptEngineManager
        private set
    lateinit var scriptManager: ScriptManager
        private set
    lateinit var scriptBridge: ScriptBridge
        private set

    val appScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    val db by lazy { AppDatabase.getInstance(this) }
    val preferencesManager by lazy { PreferencesManager(this) }
    val apiClient by lazy { ApiClient(preferencesManager) }
    val socketIOManager by lazy { SocketIOManager(preferencesManager) }
    val deviceRepository by lazy { DeviceRepository(apiClient, preferencesManager, deviceInfoCollector) }
    val deviceInfoCollector by lazy { DeviceInfoCollector(this, preferencesManager) }
    val accountRepository by lazy { AccountRepository(apiClient, preferencesManager, deviceRepository) }
    val taskRepository by lazy { TaskRepository(apiClient, preferencesManager, db) }
    val upgradeManager by lazy {
        UpgradeManager(this, preferencesManager) {
            // 适配器更新后重启 ScriptService
            val intent = android.content.Intent(this, com.saltfish.assistant.service.ScriptService::class.java)
            stopService(intent)
            startForegroundService(intent)
        }
    }
    val deviceManager by lazy { com.saltfish.assistant.engine.DeviceManager(this) }
    val lifecycleManager by lazy { com.saltfish.assistant.engine.AppLifecycleManager(this) }
    val notificationHelper by lazy { NotificationHelper(this) }

    override fun onCreate() {
        super.onCreate()
        instance = this
        GlobalAppContext.set(
            this,
            com.stardust.app.BuildConfig(
                DEBUG = BuildConfig.DEBUG,
                APPLICATION_ID = BuildConfig.APPLICATION_ID,
                BUILD_TYPE = BuildConfig.BUILD_TYPE,
                FLAVOR = "",
                VERSION_CODE = BuildConfig.VERSION_CODE.toLong(),
                VERSION_NAME = BuildConfig.VERSION_NAME
            )
        )
        setupCrashHandler()

        scriptEngineManager = ScriptEngineManager(this)
        scriptManager = ScriptManager(this)
        scriptBridge = ScriptBridge(scriptEngineManager, scriptManager)
        notificationHelper.createChannels()

        apiClient.onUnauthorized = { preferencesManager.logout() }
    }

    private fun setupCrashHandler() {
        val currentHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { thread, ex ->
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
