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
