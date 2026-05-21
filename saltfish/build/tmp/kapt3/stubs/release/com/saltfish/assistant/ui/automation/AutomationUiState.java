package com.saltfish.assistant.ui.automation;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bm\u0012\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0004\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\t\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0002\u0010\u0010J\u0015\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0004H\u00c6\u0003J\t\u0010 \u001a\u00020\u0004H\u00c6\u0003J\t\u0010!\u001a\u00020\u0004H\u00c6\u0003J\t\u0010\"\u001a\u00020\tH\u00c6\u0003J\t\u0010#\u001a\u00020\u000bH\u00c6\u0003J\t\u0010$\u001a\u00020\rH\u00c6\u0003J\t\u0010%\u001a\u00020\tH\u00c6\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0004H\u00c6\u0003Jq\u0010\'\u001a\u00020\u00002\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\t2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0004H\u00c6\u0001J\u0013\u0010(\u001a\u00020\t2\b\u0010)\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010*\u001a\u00020+H\u00d6\u0001J\t\u0010,\u001a\u00020\u0004H\u00d6\u0001R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0013R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u0011\u0010\u000e\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0015R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d\u00a8\u0006-"}, d2 = {"Lcom/saltfish/assistant/ui/automation/AutomationUiState;", "", "availablePlatforms", "", "", "selectedPlatform", "selectedTaskType", "taskParams", "isServiceRunning", "", "schedulerState", "Lcom/saltfish/assistant/engine/SchedulerState;", "taskState", "Lcom/saltfish/assistant/engine/TaskExecutionState;", "submitEnabled", "paramsError", "(Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLcom/saltfish/assistant/engine/SchedulerState;Lcom/saltfish/assistant/engine/TaskExecutionState;ZLjava/lang/String;)V", "getAvailablePlatforms", "()Ljava/util/Map;", "()Z", "getParamsError", "()Ljava/lang/String;", "getSchedulerState", "()Lcom/saltfish/assistant/engine/SchedulerState;", "getSelectedPlatform", "getSelectedTaskType", "getSubmitEnabled", "getTaskParams", "getTaskState", "()Lcom/saltfish/assistant/engine/TaskExecutionState;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "saltfish_release"})
public final class AutomationUiState {
    @org.jetbrains.annotations.NotNull
    private final java.util.Map<java.lang.String, java.lang.String> availablePlatforms = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String selectedPlatform = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String selectedTaskType = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String taskParams = null;
    private final boolean isServiceRunning = false;
    @org.jetbrains.annotations.NotNull
    private final com.saltfish.assistant.engine.SchedulerState schedulerState = null;
    @org.jetbrains.annotations.NotNull
    private final com.saltfish.assistant.engine.TaskExecutionState taskState = null;
    private final boolean submitEnabled = false;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String paramsError = null;
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.ui.automation.AutomationUiState copy(@org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.String, java.lang.String> availablePlatforms, @org.jetbrains.annotations.NotNull
    java.lang.String selectedPlatform, @org.jetbrains.annotations.NotNull
    java.lang.String selectedTaskType, @org.jetbrains.annotations.NotNull
    java.lang.String taskParams, boolean isServiceRunning, @org.jetbrains.annotations.NotNull
    com.saltfish.assistant.engine.SchedulerState schedulerState, @org.jetbrains.annotations.NotNull
    com.saltfish.assistant.engine.TaskExecutionState taskState, boolean submitEnabled, @org.jetbrains.annotations.Nullable
    java.lang.String paramsError) {
        return null;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String toString() {
        return null;
    }
    
    public AutomationUiState() {
        super();
    }
    
    public AutomationUiState(@org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.String, java.lang.String> availablePlatforms, @org.jetbrains.annotations.NotNull
    java.lang.String selectedPlatform, @org.jetbrains.annotations.NotNull
    java.lang.String selectedTaskType, @org.jetbrains.annotations.NotNull
    java.lang.String taskParams, boolean isServiceRunning, @org.jetbrains.annotations.NotNull
    com.saltfish.assistant.engine.SchedulerState schedulerState, @org.jetbrains.annotations.NotNull
    com.saltfish.assistant.engine.TaskExecutionState taskState, boolean submitEnabled, @org.jetbrains.annotations.Nullable
    java.lang.String paramsError) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Map<java.lang.String, java.lang.String> component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Map<java.lang.String, java.lang.String> getAvailablePlatforms() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getSelectedPlatform() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getSelectedTaskType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getTaskParams() {
        return null;
    }
    
    public final boolean component5() {
        return false;
    }
    
    public final boolean isServiceRunning() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.engine.SchedulerState component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.engine.SchedulerState getSchedulerState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.engine.TaskExecutionState component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.engine.TaskExecutionState getTaskState() {
        return null;
    }
    
    public final boolean component8() {
        return false;
    }
    
    public final boolean getSubmitEnabled() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getParamsError() {
        return null;
    }
}