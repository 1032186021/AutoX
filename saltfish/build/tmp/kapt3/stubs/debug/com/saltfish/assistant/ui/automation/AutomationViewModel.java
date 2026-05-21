package com.saltfish.assistant.ui.automation;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\r\u0018\u0000 $2\u00020\u0001:\u0001$B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\bH\u0002J\u0006\u0010\u0016\u001a\u00020\u0014J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\u0006\u0010\u0019\u001a\u00020\u0014J\b\u0010\u001a\u001a\u00020\u0014H\u0002J\u000e\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\bJ\u000e\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\bJ\u0006\u0010\u001f\u001a\u00020\u0014J\u0006\u0010 \u001a\u00020\u0014J\u0006\u0010!\u001a\u00020\u0014J\u000e\u0010\"\u001a\u00020\u00142\u0006\u0010#\u001a\u00020\bR\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\n0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010\u00a8\u0006%"}, d2 = {"Lcom/saltfish/assistant/ui/automation/AutomationViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_logLines", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "", "_uiState", "Lcom/saltfish/assistant/ui/automation/AutomationUiState;", "app", "Lcom/saltfish/assistant/SaltfishApp;", "logLines", "Lkotlinx/coroutines/flow/StateFlow;", "getLogLines", "()Lkotlinx/coroutines/flow/StateFlow;", "uiState", "getUiState", "addLog", "", "message", "clearLogs", "isScriptServiceRunning", "", "refreshServiceState", "refreshSubmitEnabled", "selectPlatform", "platform", "selectTaskType", "taskType", "startService", "stopService", "submitTask", "updateParams", "params", "Companion", "saltfish_debug"})
public final class AutomationViewModel extends androidx.lifecycle.AndroidViewModel {
    private final com.saltfish.assistant.SaltfishApp app = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.saltfish.assistant.ui.automation.AutomationUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.saltfish.assistant.ui.automation.AutomationUiState> uiState = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<java.lang.String>> _logLines = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<java.lang.String>> logLines = null;
    @org.jetbrains.annotations.NotNull
    public static final com.saltfish.assistant.ui.automation.AutomationViewModel.Companion Companion = null;
    @org.jetbrains.annotations.NotNull
    private static final java.util.List<kotlin.Pair<java.lang.String, java.lang.String>> TASK_TYPES = null;
    
    public AutomationViewModel(@org.jetbrains.annotations.NotNull
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.saltfish.assistant.ui.automation.AutomationUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<java.lang.String>> getLogLines() {
        return null;
    }
    
    public final void selectPlatform(@org.jetbrains.annotations.NotNull
    java.lang.String platform) {
    }
    
    public final void selectTaskType(@org.jetbrains.annotations.NotNull
    java.lang.String taskType) {
    }
    
    public final void updateParams(@org.jetbrains.annotations.NotNull
    java.lang.String params) {
    }
    
    public final void startService() {
    }
    
    public final void stopService() {
    }
    
    public final void submitTask() {
    }
    
    public final void refreshServiceState() {
    }
    
    public final void clearLogs() {
    }
    
    private final void addLog(java.lang.String message) {
    }
    
    private final void refreshSubmitEnabled() {
    }
    
    private final boolean isScriptServiceRunning() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R#\u0010\u0003\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"Lcom/saltfish/assistant/ui/automation/AutomationViewModel$Companion;", "", "()V", "TASK_TYPES", "", "Lkotlin/Pair;", "", "getTASK_TYPES", "()Ljava/util/List;", "saltfish_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.util.List<kotlin.Pair<java.lang.String, java.lang.String>> getTASK_TYPES() {
            return null;
        }
    }
}