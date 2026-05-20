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
