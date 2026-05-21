# DeviceActivationScreen 页面重构 — 实现计划

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** 将 DeviceActivationScreen 重写为 Material 3 标准风格，加入剪贴板检测、状态动画、统一排版。

**Architecture:** 单文件重构，Scaffold + TopAppBar 包裹，内部 Composable 拆分为 StatusIcon / ClipboardChip / InfoRow / DeviceDetailSection / ResetDialog。

**Tech Stack:** Kotlin 1.6.21, Jetpack Compose 1.2.0-rc01, Material 3, Navigation Compose

---

### Task 1: 搭建 Scaffold + TopAppBar 骨架

**Files:**
- Rewrite: `saltfish/src/main/java/com/saltfish/assistant/ui/activation/DeviceActivationScreen.kt`

- [ ] **Step 1: 用 Scaffold + CenterAlignedTopAppBar 替换旧布局**

删除旧代码（L114-L372 的 Box/Column 包裹），写入新骨架。保留 `ActivationMode` enum、`DeviceActivationScreen` 函数签名、`InfoRow` 不变，先不实现新组件，只搭好 Scaffold + TopAppBar + 按钮占位。

**完整替换内容：**

```kotlin
package com.saltfish.assistant.ui.activation

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
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
                errorText = if (isRenew) "续费失败，请检查卡密是否正确" else "激活失败，请检查卡密是否正确"
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
                    app.preferencesManager.clearAll()
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

            // Hero icon placeholder (to be implemented in Task 2)
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

            // Main activation card (placeholder for Task 3-5)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    // Card content — to be filled in subsequent tasks
                    Text("Card placeholder")
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

// InfoRow placeholder — keep existing implementation for now
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
```

- [ ] **Step 2: 编译验证**

Run: `./gradlew :app:compileCommonDebugKotlin 2>&1 | tail -20`
Expected: BUILD SUCCESSFUL

- [ ] **Step 3: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/ui/activation/DeviceActivationScreen.kt
git commit -m "refactor: scaffold skeleton for DeviceActivationScreen with M3 TopAppBar"
```

---

### Task 2: Hero Icon 动画

**Files:**
- Modify: `saltfish/src/main/java/com/saltfish/assistant/ui/activation/DeviceActivationScreen.kt`

- [ ] **Step 1: 编写 StatusIcon 组件**

在 `DeviceActivationScreen` 函数之后添加（新增 imports: `androidx.compose.animation.core.*`, `androidx.compose.ui.draw.rotate`）：

```kotlin
import androidx.compose.animation.core.*
import androidx.compose.ui.draw.rotate

@Composable
private fun StatusIcon(
    isLoading: Boolean,
    isSuccess: Boolean,
    modifier: Modifier = Modifier
) {
    // Rotation animation
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    // Shake animation
    val shakeOffset = remember { Animatable(0f) }

    LaunchedEffect(isSuccess) {
        // Shake on error is handled separately; success triggers scale
    }

    val icon = when {
        isSuccess -> Icons.Default.CheckCircle
        else -> Icons.Default.Lock
    }
    val tint = when {
        isSuccess -> MaterialTheme.colorScheme.primary
        else -> MaterialTheme.colorScheme.onSurfaceVariant
    }

    Surface(
        modifier = modifier
            .size(72.dp)
            .then(if (isLoading) Modifier.rotate(rotation) else Modifier),
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
```

- [ ] **Step 2: 替换 placeholder Hero icon**

将 Task 1 中的 Hero icon placeholder 代码块（`Surface` + `Icon(Lock)`）替换为：

```kotlin
StatusIcon(isLoading = isLoading, isSuccess = isSuccess)
```

更新 imports：添加 `import androidx.compose.animation.core.*` 和 `import androidx.compose.ui.draw.rotate`。

- [ ] **Step 3: 添加失败抖动逻辑**

在 `StatusIcon` 函数签名增加 `isError: Boolean` 参数，添加抖动代码：

```kotlin
@Composable
private fun StatusIcon(
    isLoading: Boolean,
    isSuccess: Boolean,
    isError: Boolean,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    val shakeOffset = remember { Animatable(0f) }
    LaunchedEffect(isError) {
        if (isError) {
            for (i in 0..2) {
                shakeOffset.animateTo(10f, tween(50))
                shakeOffset.animateTo(-10f, tween(50))
            }
            shakeOffset.animateTo(0f, tween(50))
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
            .then(if (isLoading) Modifier.rotate(rotation) else Modifier),
        shape = CircleShape,
        color = tint.copy(alpha = 0.12f)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(icon, contentDescription = null, modifier = Modifier.size(36.dp), tint = tint)
        }
    }
}
```

更新调用处：添加 `var isShakeError by remember { mutableStateOf(false) }` 状态，在 `doSubmit()` 失败分支设置 `isShakeError = true`，并在重新输入时重置。

调用处改为：`StatusIcon(isLoading = isLoading, isSuccess = isSuccess, isError = isShakeError)`

并在 `onValueChange` 中重置 `isShakeError = false`。

- [ ] **Step 4: 编译验证**

Run: `./gradlew :app:compileCommonDebugKotlin 2>&1 | tail -20`
Expected: BUILD SUCCESSFUL

- [ ] **Step 5: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/ui/activation/DeviceActivationScreen.kt
git commit -m "feat: add animated StatusIcon with rotation, shake, and success states"
```

---

### Task 3: 剪贴板检测

**Files:**
- Modify: `saltfish/src/main/java/com/saltfish/assistant/ui/activation/DeviceActivationScreen.kt`

- [ ] **Step 1: 实现 ClipboardChip 组件**

在 `StatusIcon` 函数后面添加：

```kotlin
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically

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
```

- [ ] **Step 2: 在输入框 focus 时触发检测**

在卡片内容区域添加剪贴板检测逻辑。在 `doSubmit()` 函数之后、Scaffold 之前添加：

```kotlin
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
```

在 TextField 的 `onFocusChanged` 中调用。添加 import：`import androidx.compose.ui.focus.onFocusChanged`。

- [ ] **Step 3: 编译验证**

Run: `./gradlew :app:compileCommonDebugKotlin 2>&1 | tail -20`
Expected: BUILD SUCCESSFUL

- [ ] **Step 4: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/ui/activation/DeviceActivationScreen.kt
git commit -m "feat: add clipboard detection chip with auto-fill for activation key"
```

---

### Task 4: 输入框 + 错误提示重构

**Files:**
- Modify: `saltfish/src/main/java/com/saltfish/assistant/ui/activation/DeviceActivationScreen.kt`

- [ ] **Step 1: 实现输入段和错误显示**

替换卡片中的 `Text("Card placeholder")` 为：

```kotlin
// Clipboard chip
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
        Icon(Icons.Default.Key, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
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

// Error text
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
```

需要添加 import: `import androidx.compose.animation.fadeIn`, `import androidx.compose.animation.fadeOut`, `import androidx.compose.ui.focus.onFocusChanged`。

- [ ] **Step 2: 编译验证**

Run: `./gradlew :app:compileCommonDebugKotlin 2>&1 | tail -20`
Expected: BUILD SUCCESSFUL

- [ ] **Step 3: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/ui/activation/DeviceActivationScreen.kt
git commit -m "feat: refactor input field with Material icons, clear button, and animated error display"
```

---

### Task 5: InfoRow 重构（Material Icons + 复制反馈）

**Files:**
- Modify: `saltfish/src/main/java/com/saltfish/assistant/ui/activation/DeviceActivationScreen.kt`

- [ ] **Step 1: 重写 InfoRow 组件**

替换旧的 `InfoRow` 实现：

```kotlin
import androidx.compose.ui.graphics.vector.ImageVector

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
```

- [ ] **Step 2: 更新卡片内 InfoRow 调用**

替换旧的 `InfoRow` 调用（删除 `snackbarHostState` 参数）：

```kotlin
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
```

- [ ] **Step 3: 编译验证**

Run: `./gradlew :app:compileCommonDebugKotlin 2>&1 | tail -20`
Expected: BUILD SUCCESSFUL

- [ ] **Step 4: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/ui/activation/DeviceActivationScreen.kt
git commit -m "refactor: replace emoji InfoRow with Material Icons and in-row copy feedback"
```

---

### Task 6: 提交按钮动画（禁用/加载/成功状态）

**Files:**
- Modify: `saltfish/src/main/java/com/saltfish/assistant/ui/activation/DeviceActivationScreen.kt`

- [ ] **Step 1: 替换按钮实现**

将 placeholder 按钮替换为带动画的版本：

```kotlin
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith

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
            fadeIn(tween(200)) togetherWith fadeOut(tween(200))
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
```

需要添加 imports: `import androidx.compose.animation.AnimatedContent`, `import androidx.compose.animation.togetherWith`。

- [ ] **Step 2: 编译验证**

Run: `./gradlew :app:compileCommonDebugKotlin 2>&1 | tail -20`
Expected: BUILD SUCCESSFUL

- [ ] **Step 3: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/ui/activation/DeviceActivationScreen.kt
git commit -m "feat: animated submit button with loading spinner and success state"
```

---

### Task 7: 续费模式设备详情卡片

**Files:**
- Modify: `saltfish/src/main/java/com/saltfish/assistant/ui/activation/DeviceActivationScreen.kt`

- [ ] **Step 1: 实现 DeviceDetailRow 和详情区块**

在 `InfoRow` 函数后面添加：

```kotlin
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
```

在 InfoRow 调用之后、Card 关闭之前添加续费详情区块（放在 InfoRow 之后）：

```kotlin
// Device detail section (renew mode only)
if (isRenew && device != null) {
    HorizontalDivider(
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
```

- [ ] **Step 2: 编译验证**

Run: `./gradlew :app:compileCommonDebugKotlin 2>&1 | tail -20`
Expected: BUILD SUCCESSFUL

- [ ] **Step 3: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/ui/activation/DeviceActivationScreen.kt
git commit -m "feat: add device detail section with colored expiry badge for renew mode"
```

---

### Task 8: 清理和最终验证

**Files:**
- Modify: `saltfish/src/main/java/com/saltfish/assistant/ui/activation/DeviceActivationScreen.kt`

- [ ] **Step 1: 移除未使用的 imports**

清理不再需要的 import：
- 删除 `androidx.compose.foundation.clickable`（InfoRow 不再使用整行点击）
- 删除 `androidx.compose.foundation.interaction.MutableInteractionSource`（不再需要）
- 删除 `androidx.compose.material3.Divider`（改用 HorizontalDivider）
- 删除旧 `InfoRow` 函数签名中遗留的 `SnackbarHostState` 参数相关引用
- 删除未使用的 `import androidx.compose.ui.unit.sp`（如果不再有硬编码字号）

- [ ] **Step 2: 编译并检查警告**

Run: `./gradlew :app:compileCommonDebugKotlin 2>&1 | tail -30`
Expected: BUILD SUCCESSFUL with no warnings about unused imports

- [ ] **Step 3: 完整文件自查**

确认文件包含以下所有组件（按顺序）：
1. `ActivationMode` enum
2. `DeviceActivationScreen` composable（主函数）
3. `StatusIcon` composable
4. `ClipboardChip` composable
5. `InfoRow` composable
6. `DeviceDetailRow` composable

确认所有 Composable 函数都标记为 `private`（除 `DeviceActivationScreen`）。

- [ ] **Step 4: 最终 Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/ui/activation/DeviceActivationScreen.kt
git commit -m "chore: clean up unused imports and verify file structure"
```

---

## Implementation Order

```
Task 1 (skeleton) → Task 2 (hero icon) → Task 3 (clipboard)
       → Task 4 (input + error) → Task 5 (InfoRow)
       → Task 6 (button) → Task 7 (device detail) → Task 8 (cleanup)
```

Tasks 1-3 are foundational; Tasks 4-7 build on them and can potentially be parallelized. Task 8 is final cleanup.
