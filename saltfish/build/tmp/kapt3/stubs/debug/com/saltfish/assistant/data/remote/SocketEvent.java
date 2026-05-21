package com.saltfish.assistant.data.remote;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n\u00a8\u0006\u000b"}, d2 = {"Lcom/saltfish/assistant/data/remote/SocketEvent;", "", "()V", "AdapterUpgrade", "ConfigUpdate", "DeviceCommand", "TaskExecute", "Lcom/saltfish/assistant/data/remote/SocketEvent$TaskExecute;", "Lcom/saltfish/assistant/data/remote/SocketEvent$ConfigUpdate;", "Lcom/saltfish/assistant/data/remote/SocketEvent$AdapterUpgrade;", "Lcom/saltfish/assistant/data/remote/SocketEvent$DeviceCommand;", "saltfish_debug"})
public abstract class SocketEvent {
    
    private SocketEvent() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/saltfish/assistant/data/remote/SocketEvent$TaskExecute;", "Lcom/saltfish/assistant/data/remote/SocketEvent;", "data", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "getData", "()Lorg/json/JSONObject;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "saltfish_debug"})
    public static final class TaskExecute extends com.saltfish.assistant.data.remote.SocketEvent {
        @org.jetbrains.annotations.NotNull
        private final org.json.JSONObject data = null;
        
        @org.jetbrains.annotations.NotNull
        public final com.saltfish.assistant.data.remote.SocketEvent.TaskExecute copy(@org.jetbrains.annotations.NotNull
        org.json.JSONObject data) {
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
        
        public TaskExecute(@org.jetbrains.annotations.NotNull
        org.json.JSONObject data) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final org.json.JSONObject component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final org.json.JSONObject getData() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/saltfish/assistant/data/remote/SocketEvent$ConfigUpdate;", "Lcom/saltfish/assistant/data/remote/SocketEvent;", "data", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "getData", "()Lorg/json/JSONObject;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "saltfish_debug"})
    public static final class ConfigUpdate extends com.saltfish.assistant.data.remote.SocketEvent {
        @org.jetbrains.annotations.NotNull
        private final org.json.JSONObject data = null;
        
        @org.jetbrains.annotations.NotNull
        public final com.saltfish.assistant.data.remote.SocketEvent.ConfigUpdate copy(@org.jetbrains.annotations.NotNull
        org.json.JSONObject data) {
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
        
        public ConfigUpdate(@org.jetbrains.annotations.NotNull
        org.json.JSONObject data) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final org.json.JSONObject component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final org.json.JSONObject getData() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/saltfish/assistant/data/remote/SocketEvent$AdapterUpgrade;", "Lcom/saltfish/assistant/data/remote/SocketEvent;", "data", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "getData", "()Lorg/json/JSONObject;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "saltfish_debug"})
    public static final class AdapterUpgrade extends com.saltfish.assistant.data.remote.SocketEvent {
        @org.jetbrains.annotations.NotNull
        private final org.json.JSONObject data = null;
        
        @org.jetbrains.annotations.NotNull
        public final com.saltfish.assistant.data.remote.SocketEvent.AdapterUpgrade copy(@org.jetbrains.annotations.NotNull
        org.json.JSONObject data) {
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
        
        public AdapterUpgrade(@org.jetbrains.annotations.NotNull
        org.json.JSONObject data) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final org.json.JSONObject component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final org.json.JSONObject getData() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/saltfish/assistant/data/remote/SocketEvent$DeviceCommand;", "Lcom/saltfish/assistant/data/remote/SocketEvent;", "data", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "getData", "()Lorg/json/JSONObject;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "saltfish_debug"})
    public static final class DeviceCommand extends com.saltfish.assistant.data.remote.SocketEvent {
        @org.jetbrains.annotations.NotNull
        private final org.json.JSONObject data = null;
        
        @org.jetbrains.annotations.NotNull
        public final com.saltfish.assistant.data.remote.SocketEvent.DeviceCommand copy(@org.jetbrains.annotations.NotNull
        org.json.JSONObject data) {
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
        
        public DeviceCommand(@org.jetbrains.annotations.NotNull
        org.json.JSONObject data) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final org.json.JSONObject component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final org.json.JSONObject getData() {
            return null;
        }
    }
}