package com.saltfish.assistant.data.remote;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001 B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0018\u001a\u00020\u0019J\u0006\u0010\u001a\u001a\u00020\u0019J\u0016\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/saltfish/assistant/data/remote/SocketIOManager;", "", "prefs", "Lcom/saltfish/assistant/data/local/PreferencesManager;", "(Lcom/saltfish/assistant/data/local/PreferencesManager;)V", "_connectionState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/saltfish/assistant/data/remote/SocketIOManager$ConnectionState;", "_eventChannel", "Lkotlinx/coroutines/channels/Channel;", "Lcom/saltfish/assistant/data/remote/SocketEvent;", "connectionState", "Lkotlinx/coroutines/flow/StateFlow;", "getConnectionState", "()Lkotlinx/coroutines/flow/StateFlow;", "events", "Lkotlinx/coroutines/flow/Flow;", "getEvents", "()Lkotlinx/coroutines/flow/Flow;", "maxRetries", "", "retryCount", "socket", "Lio/socket/client/Socket;", "connect", "", "disconnect", "emit", "event", "", "data", "Lorg/json/JSONObject;", "ConnectionState", "saltfish_release"})
public final class SocketIOManager {
    private final com.saltfish.assistant.data.local.PreferencesManager prefs = null;
    private io.socket.client.Socket socket;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.saltfish.assistant.data.remote.SocketIOManager.ConnectionState> _connectionState = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.saltfish.assistant.data.remote.SocketIOManager.ConnectionState> connectionState = null;
    private final kotlinx.coroutines.channels.Channel<com.saltfish.assistant.data.remote.SocketEvent> _eventChannel = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.Flow<com.saltfish.assistant.data.remote.SocketEvent> events = null;
    private int retryCount = 0;
    private final int maxRetries = 20000;
    
    public SocketIOManager(@org.jetbrains.annotations.NotNull
    com.saltfish.assistant.data.local.PreferencesManager prefs) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.saltfish.assistant.data.remote.SocketIOManager.ConnectionState> getConnectionState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<com.saltfish.assistant.data.remote.SocketEvent> getEvents() {
        return null;
    }
    
    public final void connect() {
    }
    
    public final void disconnect() {
    }
    
    public final void emit(@org.jetbrains.annotations.NotNull
    java.lang.String event, @org.jetbrains.annotations.NotNull
    org.json.JSONObject data) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/saltfish/assistant/data/remote/SocketIOManager$ConnectionState;", "", "(Ljava/lang/String;I)V", "DISCONNECTED", "CONNECTING", "CONNECTED", "RECONNECTING", "saltfish_release"})
    public static enum ConnectionState {
        /*public static final*/ DISCONNECTED /* = new DISCONNECTED() */,
        /*public static final*/ CONNECTING /* = new CONNECTING() */,
        /*public static final*/ CONNECTED /* = new CONNECTED() */,
        /*public static final*/ RECONNECTING /* = new RECONNECTING() */;
        
        ConnectionState() {
        }
    }
}