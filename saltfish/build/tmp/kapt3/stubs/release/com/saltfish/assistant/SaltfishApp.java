package com.saltfish.assistant;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 T2\u00020\u0001:\u0001TB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010Q\u001a\u00020RH\u0016J\b\u0010S\u001a\u00020RH\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\n8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\b\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0012\u001a\u00020\u00138FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0016\u0010\b\u001a\u0004\b\u0014\u0010\u0015R\u001b\u0010\u0017\u001a\u00020\u00188FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001b\u0010\b\u001a\u0004\b\u0019\u0010\u001aR\u001b\u0010\u001c\u001a\u00020\u001d8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b \u0010\b\u001a\u0004\b\u001e\u0010\u001fR\u001b\u0010!\u001a\u00020\"8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b%\u0010\b\u001a\u0004\b#\u0010$R\u001b\u0010&\u001a\u00020\'8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b*\u0010\b\u001a\u0004\b(\u0010)R\u001b\u0010+\u001a\u00020,8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b/\u0010\b\u001a\u0004\b-\u0010.R\u001b\u00100\u001a\u0002018FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b4\u0010\b\u001a\u0004\b2\u00103R\u001e\u00107\u001a\u0002062\u0006\u00105\u001a\u000206@BX\u0086.\u00a2\u0006\b\n\u0000\u001a\u0004\b8\u00109R\u001e\u0010;\u001a\u00020:2\u0006\u00105\u001a\u00020:@BX\u0086.\u00a2\u0006\b\n\u0000\u001a\u0004\b<\u0010=R\u001e\u0010?\u001a\u00020>2\u0006\u00105\u001a\u00020>@BX\u0086.\u00a2\u0006\b\n\u0000\u001a\u0004\b@\u0010AR\u001b\u0010B\u001a\u00020C8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bF\u0010\b\u001a\u0004\bD\u0010ER\u001b\u0010G\u001a\u00020H8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bK\u0010\b\u001a\u0004\bI\u0010JR\u001b\u0010L\u001a\u00020M8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bP\u0010\b\u001a\u0004\bN\u0010O\u00a8\u0006U"}, d2 = {"Lcom/saltfish/assistant/SaltfishApp;", "Landroid/app/Application;", "()V", "accountRepository", "Lcom/saltfish/assistant/data/repository/AccountRepository;", "getAccountRepository", "()Lcom/saltfish/assistant/data/repository/AccountRepository;", "accountRepository$delegate", "Lkotlin/Lazy;", "apiClient", "Lcom/saltfish/assistant/data/remote/ApiClient;", "getApiClient", "()Lcom/saltfish/assistant/data/remote/ApiClient;", "apiClient$delegate", "appScope", "Lkotlinx/coroutines/CoroutineScope;", "getAppScope", "()Lkotlinx/coroutines/CoroutineScope;", "db", "Lcom/saltfish/assistant/data/local/AppDatabase;", "getDb", "()Lcom/saltfish/assistant/data/local/AppDatabase;", "db$delegate", "deviceInfoCollector", "Lcom/saltfish/assistant/data/local/DeviceInfoCollector;", "getDeviceInfoCollector", "()Lcom/saltfish/assistant/data/local/DeviceInfoCollector;", "deviceInfoCollector$delegate", "deviceManager", "Lcom/saltfish/assistant/engine/DeviceManager;", "getDeviceManager", "()Lcom/saltfish/assistant/engine/DeviceManager;", "deviceManager$delegate", "deviceRepository", "Lcom/saltfish/assistant/data/repository/DeviceRepository;", "getDeviceRepository", "()Lcom/saltfish/assistant/data/repository/DeviceRepository;", "deviceRepository$delegate", "lifecycleManager", "Lcom/saltfish/assistant/engine/AppLifecycleManager;", "getLifecycleManager", "()Lcom/saltfish/assistant/engine/AppLifecycleManager;", "lifecycleManager$delegate", "notificationHelper", "Lcom/saltfish/assistant/service/NotificationHelper;", "getNotificationHelper", "()Lcom/saltfish/assistant/service/NotificationHelper;", "notificationHelper$delegate", "preferencesManager", "Lcom/saltfish/assistant/data/local/PreferencesManager;", "getPreferencesManager", "()Lcom/saltfish/assistant/data/local/PreferencesManager;", "preferencesManager$delegate", "<set-?>", "Lcom/saltfish/assistant/engine/ScriptBridge;", "scriptBridge", "getScriptBridge", "()Lcom/saltfish/assistant/engine/ScriptBridge;", "Lcom/stardust/autojs/engine/ScriptEngineManager;", "scriptEngineManager", "getScriptEngineManager", "()Lcom/stardust/autojs/engine/ScriptEngineManager;", "Lcom/saltfish/assistant/engine/ScriptManager;", "scriptManager", "getScriptManager", "()Lcom/saltfish/assistant/engine/ScriptManager;", "socketIOManager", "Lcom/saltfish/assistant/data/remote/SocketIOManager;", "getSocketIOManager", "()Lcom/saltfish/assistant/data/remote/SocketIOManager;", "socketIOManager$delegate", "taskRepository", "Lcom/saltfish/assistant/data/repository/TaskRepository;", "getTaskRepository", "()Lcom/saltfish/assistant/data/repository/TaskRepository;", "taskRepository$delegate", "upgradeManager", "Lcom/saltfish/assistant/engine/UpgradeManager;", "getUpgradeManager", "()Lcom/saltfish/assistant/engine/UpgradeManager;", "upgradeManager$delegate", "onCreate", "", "setupCrashHandler", "Companion", "saltfish_release"})
public final class SaltfishApp extends android.app.Application {
    private com.stardust.autojs.engine.ScriptEngineManager scriptEngineManager;
    private com.saltfish.assistant.engine.ScriptManager scriptManager;
    private com.saltfish.assistant.engine.ScriptBridge scriptBridge;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.CoroutineScope appScope = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy db$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy preferencesManager$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy apiClient$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy socketIOManager$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy deviceRepository$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy deviceInfoCollector$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy accountRepository$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy taskRepository$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy upgradeManager$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy deviceManager$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy lifecycleManager$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy notificationHelper$delegate = null;
    @org.jetbrains.annotations.NotNull
    public static final com.saltfish.assistant.SaltfishApp.Companion Companion = null;
    private static com.saltfish.assistant.SaltfishApp instance;
    
    public SaltfishApp() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.stardust.autojs.engine.ScriptEngineManager getScriptEngineManager() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.engine.ScriptManager getScriptManager() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.engine.ScriptBridge getScriptBridge() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.CoroutineScope getAppScope() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.data.local.AppDatabase getDb() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.data.local.PreferencesManager getPreferencesManager() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.data.remote.ApiClient getApiClient() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.data.remote.SocketIOManager getSocketIOManager() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.data.repository.DeviceRepository getDeviceRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.data.local.DeviceInfoCollector getDeviceInfoCollector() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.data.repository.AccountRepository getAccountRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.data.repository.TaskRepository getTaskRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.engine.UpgradeManager getUpgradeManager() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.engine.DeviceManager getDeviceManager() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.engine.AppLifecycleManager getLifecycleManager() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.service.NotificationHelper getNotificationHelper() {
        return null;
    }
    
    @java.lang.Override
    public void onCreate() {
    }
    
    private final void setupCrashHandler() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R$\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t\u00a8\u0006\n"}, d2 = {"Lcom/saltfish/assistant/SaltfishApp$Companion;", "", "()V", "<set-?>", "Lcom/saltfish/assistant/SaltfishApp;", "instance", "getInstance", "()Lcom/saltfish/assistant/SaltfishApp;", "setInstance", "(Lcom/saltfish/assistant/SaltfishApp;)V", "saltfish_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.saltfish.assistant.SaltfishApp getInstance() {
            return null;
        }
        
        private final void setInstance(com.saltfish.assistant.SaltfishApp p0) {
        }
    }
}