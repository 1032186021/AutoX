package com.saltfish.assistant.ui.home;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\b\u001d\b\u0086\b\u0018\u00002\u00020\u0001Bk\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\t\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\u0014\b\u0002\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\u0011\u00a2\u0006\u0002\u0010\u0012J\t\u0010 \u001a\u00020\u0003H\u00c6\u0003J\t\u0010!\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0007H\u00c6\u0003J\t\u0010#\u001a\u00020\tH\u00c6\u0003J\t\u0010$\u001a\u00020\tH\u00c6\u0003J\t\u0010%\u001a\u00020\tH\u00c6\u0003J\t\u0010&\u001a\u00020\rH\u00c6\u0003J\t\u0010\'\u001a\u00020\u000fH\u00c6\u0003J\u0015\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\u0011H\u00c6\u0003Jo\u0010)\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u0014\b\u0002\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\u0011H\u00c6\u0001J\u0013\u0010*\u001a\u00020\t2\b\u0010+\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010,\u001a\u00020\u000fH\u00d6\u0001J\t\u0010-\u001a\u00020\rH\u00d6\u0001R\u001d\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0017R\u0011\u0010\n\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0017R\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f\u00a8\u0006."}, d2 = {"Lcom/saltfish/assistant/ui/home/HomeUiState;", "", "wsState", "Lcom/saltfish/assistant/data/remote/SocketIOManager$ConnectionState;", "taskState", "Lcom/saltfish/assistant/engine/TaskExecutionState;", "deviceInfo", "Lcom/saltfish/assistant/domain/model/DeviceInfo;", "isAccessibilityEnabled", "", "isFloatyPermissionGranted", "isIgnoringBattery", "username", "", "pendingTaskCount", "", "adapterVersions", "", "(Lcom/saltfish/assistant/data/remote/SocketIOManager$ConnectionState;Lcom/saltfish/assistant/engine/TaskExecutionState;Lcom/saltfish/assistant/domain/model/DeviceInfo;ZZZLjava/lang/String;ILjava/util/Map;)V", "getAdapterVersions", "()Ljava/util/Map;", "getDeviceInfo", "()Lcom/saltfish/assistant/domain/model/DeviceInfo;", "()Z", "getPendingTaskCount", "()I", "getTaskState", "()Lcom/saltfish/assistant/engine/TaskExecutionState;", "getUsername", "()Ljava/lang/String;", "getWsState", "()Lcom/saltfish/assistant/data/remote/SocketIOManager$ConnectionState;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "saltfish_debug"})
public final class HomeUiState {
    @org.jetbrains.annotations.NotNull
    private final com.saltfish.assistant.data.remote.SocketIOManager.ConnectionState wsState = null;
    @org.jetbrains.annotations.NotNull
    private final com.saltfish.assistant.engine.TaskExecutionState taskState = null;
    @org.jetbrains.annotations.NotNull
    private final com.saltfish.assistant.domain.model.DeviceInfo deviceInfo = null;
    private final boolean isAccessibilityEnabled = false;
    private final boolean isFloatyPermissionGranted = false;
    private final boolean isIgnoringBattery = false;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String username = null;
    private final int pendingTaskCount = 0;
    @org.jetbrains.annotations.NotNull
    private final java.util.Map<java.lang.String, java.lang.String> adapterVersions = null;
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.ui.home.HomeUiState copy(@org.jetbrains.annotations.NotNull
    com.saltfish.assistant.data.remote.SocketIOManager.ConnectionState wsState, @org.jetbrains.annotations.NotNull
    com.saltfish.assistant.engine.TaskExecutionState taskState, @org.jetbrains.annotations.NotNull
    com.saltfish.assistant.domain.model.DeviceInfo deviceInfo, boolean isAccessibilityEnabled, boolean isFloatyPermissionGranted, boolean isIgnoringBattery, @org.jetbrains.annotations.NotNull
    java.lang.String username, int pendingTaskCount, @org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.String, java.lang.String> adapterVersions) {
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
    
    public HomeUiState() {
        super();
    }
    
    public HomeUiState(@org.jetbrains.annotations.NotNull
    com.saltfish.assistant.data.remote.SocketIOManager.ConnectionState wsState, @org.jetbrains.annotations.NotNull
    com.saltfish.assistant.engine.TaskExecutionState taskState, @org.jetbrains.annotations.NotNull
    com.saltfish.assistant.domain.model.DeviceInfo deviceInfo, boolean isAccessibilityEnabled, boolean isFloatyPermissionGranted, boolean isIgnoringBattery, @org.jetbrains.annotations.NotNull
    java.lang.String username, int pendingTaskCount, @org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.String, java.lang.String> adapterVersions) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.data.remote.SocketIOManager.ConnectionState component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.data.remote.SocketIOManager.ConnectionState getWsState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.engine.TaskExecutionState component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.engine.TaskExecutionState getTaskState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.domain.model.DeviceInfo component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.saltfish.assistant.domain.model.DeviceInfo getDeviceInfo() {
        return null;
    }
    
    public final boolean component4() {
        return false;
    }
    
    public final boolean isAccessibilityEnabled() {
        return false;
    }
    
    public final boolean component5() {
        return false;
    }
    
    public final boolean isFloatyPermissionGranted() {
        return false;
    }
    
    public final boolean component6() {
        return false;
    }
    
    public final boolean isIgnoringBattery() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getUsername() {
        return null;
    }
    
    public final int component8() {
        return 0;
    }
    
    public final int getPendingTaskCount() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Map<java.lang.String, java.lang.String> component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Map<java.lang.String, java.lang.String> getAdapterVersions() {
        return null;
    }
}