package com.saltfish.assistant

import android.app.Application
import android.content.Context
import com.saltfish.assistant.data.local.AppDatabase
import com.saltfish.assistant.data.local.PreferencesManager
import com.saltfish.assistant.data.remote.ApiClient
import com.saltfish.assistant.data.remote.SocketIOManager
import com.saltfish.assistant.data.repository.AccountRepository
import com.saltfish.assistant.data.repository.DeviceRepository
import com.saltfish.assistant.data.repository.TaskRepository
import com.saltfish.assistant.engine.ScriptBridge
import com.saltfish.assistant.engine.ScriptManager
import com.saltfish.assistant.engine.UpgradeManager
import com.stardust.autojs.engine.ScriptEngineManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

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
    val deviceRepository by lazy { DeviceRepository(apiClient, preferencesManager) }
    val accountRepository by lazy { AccountRepository(apiClient, preferencesManager) }
    val taskRepository by lazy { TaskRepository(apiClient, preferencesManager, db) }
    val upgradeManager by lazy {
        UpgradeManager(this, preferencesManager) {
            // 适配器更新后重启 ScriptService
            val intent = android.content.Intent(this, com.saltfish.assistant.service.ScriptService::class.java)
            stopService(intent)
            startForegroundService(intent)
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        setupCrashHandler()

        scriptEngineManager = ScriptEngineManager(this)
        scriptManager = ScriptManager(this)
        scriptBridge = ScriptBridge(scriptEngineManager, scriptManager)
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
