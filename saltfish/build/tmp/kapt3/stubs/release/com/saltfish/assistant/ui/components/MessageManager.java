package com.saltfish.assistant.ui.components;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\rJ\u0018\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u0015J\u0018\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u0015J \u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u0014\u001a\u00020\u0015J\u0018\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u0015J\u0018\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u0015R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t8F\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/saltfish/assistant/ui/components/MessageManager;", "", "()V", "_messages", "Landroidx/compose/runtime/snapshots/SnapshotStateList;", "Lcom/saltfish/assistant/ui/components/MessageItem;", "handler", "Landroid/os/Handler;", "messages", "", "getMessages", "()Ljava/util/List;", "nextId", "", "dismiss", "", "id", "error", "text", "", "position", "Lcom/saltfish/assistant/ui/components/MessagePosition;", "info", "show", "type", "Lcom/saltfish/assistant/ui/components/MessageType;", "success", "warning", "saltfish_release"})
public final class MessageManager {
    @org.jetbrains.annotations.NotNull
    public static final com.saltfish.assistant.ui.components.MessageManager INSTANCE = null;
    private static final androidx.compose.runtime.snapshots.SnapshotStateList<com.saltfish.assistant.ui.components.MessageItem> _messages = null;
    private static long nextId = 0L;
    private static final android.os.Handler handler = null;
    
    private MessageManager() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.saltfish.assistant.ui.components.MessageItem> getMessages() {
        return null;
    }
    
    public final void show(@org.jetbrains.annotations.NotNull
    java.lang.String text, @org.jetbrains.annotations.NotNull
    com.saltfish.assistant.ui.components.MessageType type, @org.jetbrains.annotations.NotNull
    com.saltfish.assistant.ui.components.MessagePosition position) {
    }
    
    public final void success(@org.jetbrains.annotations.NotNull
    java.lang.String text, @org.jetbrains.annotations.NotNull
    com.saltfish.assistant.ui.components.MessagePosition position) {
    }
    
    public final void error(@org.jetbrains.annotations.NotNull
    java.lang.String text, @org.jetbrains.annotations.NotNull
    com.saltfish.assistant.ui.components.MessagePosition position) {
    }
    
    public final void warning(@org.jetbrains.annotations.NotNull
    java.lang.String text, @org.jetbrains.annotations.NotNull
    com.saltfish.assistant.ui.components.MessagePosition position) {
    }
    
    public final void info(@org.jetbrains.annotations.NotNull
    java.lang.String text, @org.jetbrains.annotations.NotNull
    com.saltfish.assistant.ui.components.MessagePosition position) {
    }
    
    public final void dismiss(long id) {
    }
}