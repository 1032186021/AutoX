package com.saltfish.assistant.service;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 #2\u00020\u0001:\u0001#B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u000eJ\u0006\u0010\u0012\u001a\u00020\u000eJ\b\u0010\u0013\u001a\u00020\u0014H\u0002J\u0016\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\fJ\u001e\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\fJ&\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\fJ\u0018\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\f2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0014J\u0010\u0010 \u001a\u00020!2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u000e\u0010\"\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00068BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\b\u00a8\u0006$"}, d2 = {"Lcom/saltfish/assistant/service/NotificationHelper;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "manager", "Landroid/app/NotificationManager;", "getManager", "()Landroid/app/NotificationManager;", "buildServiceNotification", "Landroid/app/Notification;", "text", "", "cancelTaskNotification", "", "taskId", "", "cancelUpgradeNotification", "createChannels", "mainActivityIntent", "Landroid/app/PendingIntent;", "showError", "title", "message", "showTaskCompleted", "taskType", "platform", "showTaskFailed", "error", "showUpgradeAvailable", "version", "downloadIntent", "taskNotificationId", "", "updateServiceNotification", "Companion", "saltfish_debug"})
public final class NotificationHelper {
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    public static final com.saltfish.assistant.service.NotificationHelper.Companion Companion = null;
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String CHANNEL_SERVICE = "saltfish_service";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String CHANNEL_TASK_RESULT = "saltfish_task_result";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String CHANNEL_ERROR = "saltfish_error";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String CHANNEL_UPGRADE = "saltfish_upgrade";
    public static final int NOTIFICATION_SERVICE = 1001;
    public static final int NOTIFICATION_ERROR = 3001;
    public static final int NOTIFICATION_UPGRADE = 4001;
    private static final int TASK_NOTIFICATION_OFFSET = 2000;
    private static final int MAX_TASK_NOTIFICATIONS = 100;
    
    public NotificationHelper(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    private final android.app.NotificationManager getManager() {
        return null;
    }
    
    public final void createChannels() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final android.app.Notification buildServiceNotification(@org.jetbrains.annotations.NotNull
    java.lang.String text) {
        return null;
    }
    
    public final void updateServiceNotification(@org.jetbrains.annotations.NotNull
    java.lang.String text) {
    }
    
    public final void showTaskCompleted(long taskId, @org.jetbrains.annotations.NotNull
    java.lang.String taskType, @org.jetbrains.annotations.NotNull
    java.lang.String platform) {
    }
    
    public final void showTaskFailed(long taskId, @org.jetbrains.annotations.NotNull
    java.lang.String taskType, @org.jetbrains.annotations.NotNull
    java.lang.String platform, @org.jetbrains.annotations.NotNull
    java.lang.String error) {
    }
    
    public final void cancelTaskNotification(long taskId) {
    }
    
    public final void showError(@org.jetbrains.annotations.NotNull
    java.lang.String title, @org.jetbrains.annotations.NotNull
    java.lang.String message) {
    }
    
    public final void showUpgradeAvailable(@org.jetbrains.annotations.NotNull
    java.lang.String version, @org.jetbrains.annotations.Nullable
    android.app.PendingIntent downloadIntent) {
    }
    
    public final void cancelUpgradeNotification() {
    }
    
    private final android.app.PendingIntent mainActivityIntent() {
        return null;
    }
    
    private final int taskNotificationId(long taskId) {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/saltfish/assistant/service/NotificationHelper$Companion;", "", "()V", "CHANNEL_ERROR", "", "CHANNEL_SERVICE", "CHANNEL_TASK_RESULT", "CHANNEL_UPGRADE", "MAX_TASK_NOTIFICATIONS", "", "NOTIFICATION_ERROR", "NOTIFICATION_SERVICE", "NOTIFICATION_UPGRADE", "TASK_NOTIFICATION_OFFSET", "saltfish_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}