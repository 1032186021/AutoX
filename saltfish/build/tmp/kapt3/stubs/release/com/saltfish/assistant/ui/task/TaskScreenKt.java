package com.saltfish.assistant.ui.task;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 2, d1 = {"\u00000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u001a\u001e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0003\u001a\u0012\u0010\u0006\u001a\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007\u001a\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\r\u001a\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\fH\u0002\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0010"}, d2 = {"TaskCard", "", "task", "Lcom/saltfish/assistant/domain/model/TaskEntity;", "onCancel", "Lkotlin/Function0;", "TaskScreen", "viewModel", "Lcom/saltfish/assistant/ui/task/TaskViewModel;", "statusColor", "Landroidx/compose/ui/graphics/Color;", "status", "Lcom/saltfish/assistant/domain/model/TaskStatus;", "(Lcom/saltfish/assistant/domain/model/TaskStatus;)J", "statusText", "", "saltfish_release"})
public final class TaskScreenKt {
    
    @androidx.compose.runtime.Composable
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    public static final void TaskScreen(@org.jetbrains.annotations.NotNull
    com.saltfish.assistant.ui.task.TaskViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable
    private static final void TaskCard(com.saltfish.assistant.domain.model.TaskEntity task, kotlin.jvm.functions.Function0<kotlin.Unit> onCancel) {
    }
    
    private static final java.lang.String statusText(com.saltfish.assistant.domain.model.TaskStatus status) {
        return null;
    }
    
    private static final long statusColor(com.saltfish.assistant.domain.model.TaskStatus status) {
        return 0L;
    }
}