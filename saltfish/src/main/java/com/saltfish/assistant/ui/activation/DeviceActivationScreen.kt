package com.saltfish.assistant.ui.activation

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

@OptIn(ExperimentalMaterial3Api::class)
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

        scope.launch {
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
        }
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

            // Hero icon placeholder
            Surface(
                modifier = Modifier.size(72.dp),
                shape = CircleShape,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.12f)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        Icons.Default.Lock,
                        contentDescription = null,
                        modifier = Modifier.size(36.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

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
                    Text("Card placeholder — to be replaced in Tasks 3-5")
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Submit button placeholder
            Button(
                onClick = { doSubmit() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(52.dp),
                shape = MaterialTheme.shapes.large,
                enabled = !isLoading && secret.isNotBlank(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
                )
            ) {
                Text(mode.btnLabel, style = MaterialTheme.typography.labelLarge)
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

// InfoRow — keep existing implementation unchanged for now
@Composable
private fun InfoRow(
    icon: String,
    label: String,
    value: String,
    onCopy: (() -> String)? = null,
    snackbarHostState: SnackbarHostState? = null
) {
    val scope = rememberCoroutineScope()
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp)
            .then(
                if (onCopy != null) {
                    Modifier.clickable(
                        interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() },
                        indication = null
                    ) {
                        val text = onCopy()
                        val clipboard = SaltfishApp.instance
                            .getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        clipboard.setPrimaryClip(ClipData.newPlainText("text", text))
                        scope.launch {
                            snackbarHostState?.showSnackbar("已复制到剪贴板")
                        }
                    }
                } else Modifier
            )
    ) {
        Text(text = icon, fontSize = 18.sp)
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = label,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 14.sp,
            modifier = Modifier.width(90.dp)
        )
        Text(
            text = value,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 12.sp,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        if (onCopy != null) {
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "📋", fontSize = 16.sp, color = MaterialTheme.colorScheme.outline)
        }
    }
}
