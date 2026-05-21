package com.saltfish.assistant.ui.activation

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.IntOffset
import com.saltfish.assistant.SaltfishApp
import com.saltfish.assistant.engine.DeviceState
import com.saltfish.assistant.ui.theme.StatusGreen
import com.saltfish.assistant.ui.theme.StatusOrange
import com.saltfish.assistant.ui.theme.StatusRed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// ── Activation mode ──
enum class ActivationMode(val title: String, val promptHint: String, val btnLabel: String) {
    Register("激活设备", "请输入咸鱼助手的注册卡密", "激活"),
    Renew("续费使用", "请输入咸鱼助手的续费卡密", "续费")
}

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DeviceActivationScreen(
    mode: ActivationMode,
    onDone: () -> Unit
) {
    val app = LocalContext.current.applicationContext as SaltfishApp
    val deviceManager = app.deviceManager
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val focusManager = LocalFocusManager.current

    val isRenew = mode == ActivationMode.Renew
    val device = deviceManager.device
    val remainingDays = deviceManager.remainingDays
    val canBack = !(deviceManager.state == DeviceState.Idle || deviceManager.state == DeviceState.Expired)

    // ── Local state ──
    var secret by remember { mutableStateOf("") }
    var errorText by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var submitted by remember { mutableStateOf(false) }
    var showResetDialog by remember { mutableStateOf(false) }
    var showClipboardChip by remember { mutableStateOf(false) }
    var clipboardText by remember { mutableStateOf("") }
    var isSuccess by remember { mutableStateOf(false) }
    var isShakeError by remember { mutableStateOf(false) }

    val expiryColor = when {
        remainingDays <= 0 -> StatusRed
        remainingDays <= 3 -> StatusOrange
        else -> MaterialTheme.colorScheme.onSurfaceVariant
    }

    // Clipboard detection
    fun checkClipboard() {
        if (showClipboardChip) return
        try {
            val cm = app.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = cm.primaryClip?.getItemAt(0)?.text?.toString()
            if (!clip.isNullOrBlank() && clip != secret) {
                clipboardText = clip
                showClipboardChip = true
            }
        } catch (_: Exception) {}
    }

    // Trigger clipboard check on resume (and initial composition)
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(lifecycle) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                checkClipboard()
            }
        }
        lifecycle.addObserver(observer)
        onDispose { lifecycle.removeObserver(observer) }
    }

    // ── Submit handler ──
    fun doSubmit() {
        if (submitted || isLoading) return
        val input = secret.trim()
        if (input.isEmpty()) {
            errorText = "请输入有效卡密"
            return
        }
        submitted = true
        isLoading = true
        errorText = ""
        isShakeError = false

        scope.launch {
            try {
                val success: Boolean = withContext(Dispatchers.IO) {
                    if (isRenew) {
                        deviceManager.renew(input)
                    } else {
                        deviceManager.register(input) != null
                    }
                }
                if (success) {
                    isSuccess = true
                    app.deviceManager.onActivationResolved?.invoke()
                    delay(600)
                    onDone()
                } else {
                    isLoading = false
                    isSuccess = false
                    submitted = false
                    isShakeError = true
                    errorText = if (isRenew) "续费失败，请检查卡密是否正确" else "激活失败，请检查卡密是否正确"
                }
            } catch (e: Exception) {
                isLoading = false
                isSuccess = false
                submitted = false
                errorText = "网络连接失败，请检查网络后重试"
            }
        }
    }

    // Block back when non-dismissible
    if (!canBack) {
        BackHandler { /* consume back */ }
    }

    // ── Reset dialog ──
    if (showResetDialog) {
        AlertDialog(
            onDismissRequest = { showResetDialog = false },
            title = { Text("重置设备") },
            text = { Text("将清除本地设备数据，需要重新登录并激活。确定继续？") },
            confirmButton = {
                TextButton(onClick = {
                    showResetDialog = false
                    app.preferencesManager.logout()
                    app.preferencesManager.deviceId = null
                    app.preferencesManager.deviceKey = null
                    app.preferencesManager.guideShown = false
                    onDone()
                }) {
                    Text("确定", color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = { showResetDialog = false }) {
                    Text("取消")
                }
            }
        )
    }

    // ── UI ──
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(mode.title, style = MaterialTheme.typography.titleLarge) },
                navigationIcon = {
                    if (canBack) {
                        IconButton(onClick = { if (!submitted) onDone() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "返回")
                        }
                    }
                },
                actions = {
                    if (!canBack) {
                        IconButton(onClick = { showResetDialog = true }) {
                            Icon(Icons.Default.Refresh, contentDescription = "重置")
                        }
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            StatusIcon(
                isLoading = isLoading,
                isSuccess = isSuccess,
                isError = isShakeError
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = if (isRenew) "续费以继续使用" else "欢迎使用咸鱼助手",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Main activation card (placeholder for later tasks)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    // Clipboard chip (shown when clipboard has text)
                    ClipboardChip(
                        text = clipboardText,
                        onFill = {
                            secret = clipboardText
                            errorText = ""
                        },
                        onDismiss = { showClipboardChip = false },
                        visible = showClipboardChip
                    )

                    // Prompt text
                    Text(
                        text = mode.promptHint,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Input field
                    OutlinedTextField(
                        value = secret,
                        onValueChange = {
                            secret = it
                            errorText = ""
                            isShakeError = false
                        },
                        placeholder = { Text("请输入卡密", style = MaterialTheme.typography.bodyLarge) },
                        leadingIcon = {
                            Icon(Icons.Default.Key, contentDescription = null,
                                 tint = MaterialTheme.colorScheme.onSurfaceVariant)
                        },
                        trailingIcon = {
                            if (secret.isNotEmpty()) {
                                IconButton(onClick = { secret = "" }) {
                                    Icon(Icons.Default.Clear, contentDescription = "清除")
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .onFocusChanged { focusState ->
                                if (focusState.isFocused) checkClipboard()
                            },
                        singleLine = true,
                        enabled = !isLoading,
                        shape = MaterialTheme.shapes.medium,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.clearFocus()
                                doSubmit()
                            }
                        )
                    )

                    // Error text with animation
                    AnimatedVisibility(
                        visible = errorText.isNotEmpty(),
                        enter = fadeIn(tween(300)),
                        exit = fadeOut(tween(200))
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(top = 10.dp)
                        ) {
                            Icon(
                                Icons.Default.Error,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                                tint = MaterialTheme.colorScheme.error
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                errorText,
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    // Device info rows
                    val appVersion = "v${app.packageManager.getPackageInfo(app.packageName, 0).versionName}"
                    InfoRow(
                        icon = Icons.Default.PhoneAndroid,
                        label = "App 版本",
                        value = appVersion,
                        onCopy = { appVersion }
                    )
                    InfoRow(
                        icon = Icons.Default.Code,
                        label = "脚本版本",
                        value = "v1.0.0",
                        onCopy = { "v1.0.0" }
                    )
                    val uuid = app.preferencesManager.uuid
                    InfoRow(
                        icon = Icons.Default.Fingerprint,
                        label = "唯一标识",
                        value = uuid,
                        onCopy = { uuid }
                    )

                    // Device detail section (renew mode only)
                    if (isRenew && device != null) {
                        Divider(
                            modifier = Modifier.padding(vertical = 12.dp),
                            color = MaterialTheme.colorScheme.outlineVariant
                        )

                        // Section header
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(bottom = 8.dp)
                        ) {
                            Icon(
                                Icons.Default.PhoneAndroid,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                "设备详情",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        // Detail surface
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            shape = MaterialTheme.shapes.small,
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                DeviceDetailRow("设备名称", device.name ?: "-")
                                DeviceDetailRow("到期时间", device.expiresTime ?: "-")

                                Spacer(modifier = Modifier.height(4.dp))

                                // Remaining days badge
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        "剩余天数",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )

                                    val badgeColor = when {
                                        remainingDays <= 0 -> StatusRed
                                        remainingDays <= 7 -> StatusOrange
                                        else -> StatusGreen
                                    }

                                    Surface(
                                        shape = RoundedCornerShape(6.dp),
                                        color = badgeColor.copy(alpha = 0.15f)
                                    ) {
                                        Text(
                                            text = if (remainingDays <= 0) "已过期" else "${remainingDays} 天",
                                            color = badgeColor,
                                            style = MaterialTheme.typography.labelMedium,
                                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { doSubmit() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(52.dp),
                shape = MaterialTheme.shapes.large,
                enabled = !isLoading && secret.isNotBlank(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSuccess)
                        StatusGreen
                    else
                        MaterialTheme.colorScheme.primary,
                    disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
                )
            ) {
                AnimatedContent(
                    targetState = isLoading to isSuccess,
                    transitionSpec = {
                        ContentTransform(
                            targetContentEnter = fadeIn(tween(200)),
                            initialContentExit = fadeOut(tween(200))
                        )
                    }
                ) { (loading, success) ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        when {
                            loading -> {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(22.dp),
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    strokeWidth = 2.dp
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text("提交中...", style = MaterialTheme.typography.labelLarge)
                            }
                            success -> {
                                Icon(
                                    Icons.Default.Check,
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    if (isRenew) "续费成功" else "激活成功",
                                    style = MaterialTheme.typography.labelLarge
                                )
                            }
                            else -> {
                                Icon(
                                    Icons.Default.VpnKey,
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(mode.btnLabel, style = MaterialTheme.typography.labelLarge)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

// ── Animated StatusIcon ──
@Composable
private fun StatusIcon(
    isLoading: Boolean,
    isSuccess: Boolean,
    isError: Boolean,
    modifier: Modifier = Modifier
) {
    // Rotation animation for loading
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    // Shake animation for error
    val shakeOffset = remember { Animatable(0f) }
    LaunchedEffect(isError) {
        if (isError) {
            for (i in 0..2) {
                shakeOffset.animateTo(10f, tween(50))
                shakeOffset.animateTo(-10f, tween(50))
            }
            shakeOffset.animateTo(0f, tween(50))
        } else {
            shakeOffset.snapTo(0f)
        }
    }

    val (icon, tint) = when {
        isSuccess -> Icons.Default.CheckCircle to MaterialTheme.colorScheme.primary
        isError -> Icons.Default.Lock to MaterialTheme.colorScheme.error
        else -> Icons.Default.Lock to MaterialTheme.colorScheme.onSurfaceVariant
    }

    Surface(
        modifier = modifier
            .size(72.dp)
            .offset(x = shakeOffset.value.dp)
            .then(Modifier.rotate(if (isLoading) rotation else 0f)),
        shape = CircleShape,
        color = tint.copy(alpha = 0.12f)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                icon,
                contentDescription = null,
                modifier = Modifier.size(36.dp),
                tint = tint
            )
        }
    }
}

// ── ClipboardChip ──
@Composable
private fun ClipboardChip(
    text: String,
    onFill: () -> Unit,
    onDismiss: () -> Unit,
    visible: Boolean
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(tween(300)),
        exit = slideOutVertically(targetOffsetY = { -it }) + fadeOut(tween(200))
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            shape = MaterialTheme.shapes.small,
            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.ContentPaste,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "检测到剪贴板中的卡密",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                TextButton(onClick = {
                    onFill()
                    onDismiss()
                }) {
                    Text("一键填入", style = MaterialTheme.typography.labelMedium)
                }
            }
        }
    }
}

@Composable
private fun DeviceDetailRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            value,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun InfoRow(
    icon: ImageVector,
    label: String,
    value: String,
    onCopy: (() -> String)? = null
) {
    var showCopied by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(18.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.width(80.dp)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        if (onCopy != null) {
            Spacer(modifier = Modifier.width(6.dp))
            IconButton(
                onClick = {
                    val text = onCopy()
                    val clipboard = SaltfishApp.instance
                        .getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    clipboard.setPrimaryClip(ClipData.newPlainText("text", text))
                    showCopied = true
                    scope.launch {
                        delay(800)
                        showCopied = false
                    }
                },
                modifier = Modifier.size(28.dp)
            ) {
                Icon(
                    imageVector = if (showCopied) Icons.Default.Check else Icons.Default.ContentCopy,
                    contentDescription = if (showCopied) "已复制" else "复制",
                    modifier = Modifier.size(14.dp),
                    tint = if (showCopied) StatusGreen else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
