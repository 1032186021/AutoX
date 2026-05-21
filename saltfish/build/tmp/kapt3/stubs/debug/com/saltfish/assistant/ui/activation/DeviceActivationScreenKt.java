package com.saltfish.assistant.ui.activation;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 2, d1 = {"\u00004\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u001a4\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0003\u001a\u001e\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a\u0018\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0003H\u0003\u001a2\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00032\u0010\b\u0002\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005H\u0003\u001a*\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\b2\b\b\u0002\u0010\u0018\u001a\u00020\u0019H\u0003\u00a8\u0006\u001a"}, d2 = {"ClipboardChip", "", "text", "", "onFill", "Lkotlin/Function0;", "onDismiss", "visible", "", "DeviceActivationScreen", "mode", "Lcom/saltfish/assistant/ui/activation/ActivationMode;", "onDone", "DeviceDetailRow", "label", "value", "InfoRow", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "onCopy", "StatusIcon", "isLoading", "isSuccess", "isError", "modifier", "Landroidx/compose/ui/Modifier;", "saltfish_debug"})
public final class DeviceActivationScreenKt {
    
    @androidx.compose.runtime.Composable
    @kotlin.OptIn(markerClass = {androidx.compose.animation.ExperimentalAnimationApi.class, androidx.compose.material3.ExperimentalMaterial3Api.class})
    public static final void DeviceActivationScreen(@org.jetbrains.annotations.NotNull
    com.saltfish.assistant.ui.activation.ActivationMode mode, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onDone) {
    }
    
    @androidx.compose.runtime.Composable
    private static final void StatusIcon(boolean isLoading, boolean isSuccess, boolean isError, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable
    private static final void ClipboardChip(java.lang.String text, kotlin.jvm.functions.Function0<kotlin.Unit> onFill, kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, boolean visible) {
    }
    
    @androidx.compose.runtime.Composable
    private static final void DeviceDetailRow(java.lang.String label, java.lang.String value) {
    }
    
    @androidx.compose.runtime.Composable
    private static final void InfoRow(androidx.compose.ui.graphics.vector.ImageVector icon, java.lang.String label, java.lang.String value, kotlin.jvm.functions.Function0<java.lang.String> onCopy) {
    }
}