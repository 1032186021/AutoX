package com.saltfish.assistant.ui.permissions

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.PowerManager
import android.provider.Settings
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import kotlinx.coroutines.delay

data class PermissionItem(
    val key: String,
    val title: String,
    val description: String,
    val icon: ImageVector,
    val isRequired: Boolean = true,
    val settingAction: (Context) -> Unit,
    val checkGranted: (Context) -> Boolean
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PermissionsGuideScreen(onComplete: () -> Unit) {
    val context = LocalContext.current

    // 获取未授权的权限列表（必需权限）
    val allPermissions = remember { permissions }
    val missingPermissions = remember { allPermissions.filter { !it.checkGranted(context) } }

    // 全部已授权，直接返回
    LaunchedEffect(Unit) {
        if (missingPermissions.isEmpty()) {
            onComplete()
        }
    }

    // 全部已授权，不渲染任何 UI
    if (missingPermissions.isEmpty()) return

    var currentStep by remember { mutableStateOf(0) }
    var grantedStates by remember { mutableStateOf(missingPermissions.map { it.checkGranted(context) }) }

    val refreshTrigger = remember { mutableStateOf(0) }
    LaunchedEffect(refreshTrigger.value) {
        delay(500)
        grantedStates = missingPermissions.map { it.checkGranted(context) }
    }

    // 从设置页返回时自动刷新权限状态
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                refreshTrigger.value++
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    val step = missingPermissions.getOrNull(currentStep) ?: return

    Scaffold(
        topBar = {
            if (currentStep < missingPermissions.size) {
                CenterAlignedTopAppBar(
                    title = {
                        Text("权限引导 (${currentStep + 1}/${missingPermissions.size})")
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                missingPermissions.indices.forEach { index ->
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 6.dp)
                            .size(if (index == currentStep) 10.dp else 8.dp)
                            .clip(CircleShape)
                            .background(
                                when {
                                    index < currentStep -> StatusGreen
                                    index == currentStep -> MaterialTheme.colorScheme.primary
                                    else -> MaterialTheme.colorScheme.outlineVariant
                                }
                            )
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Surface(
                modifier = Modifier.size(80.dp),
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primaryContainer
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = step.icon,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                step.title,
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                step.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            val isGranted = grantedStates.getOrElse(currentStep) { false }
            StatusBadge(isGranted = isGranted, isRequired = step.isRequired)

            Spacer(modifier = Modifier.height(32.dp))

            if (!isGranted) {
                Button(
                    onClick = {
                        step.settingAction(context)
                        refreshTrigger.value++
                    },
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    shape = RoundedCornerShape(25.dp)
                ) {
                    Icon(Icons.Default.Settings, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("前往设置")
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    "授权后请返回此页面",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                Surface(
                    modifier = Modifier.size(64.dp),
                    shape = CircleShape,
                    color = StatusGreen.copy(alpha = 0.1f)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            Icons.Default.Check,
                            contentDescription = null,
                            modifier = Modifier.size(36.dp),
                            tint = StatusGreen
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (currentStep > 0) {
                    OutlinedButton(
                        onClick = { currentStep-- },
                        modifier = Modifier.weight(1f).height(48.dp),
                        shape = RoundedCornerShape(24.dp)
                    ) {
                        Icon(Icons.Default.KeyboardArrowLeft, contentDescription = null, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("上一步")
                    }
                }

                if (currentStep < missingPermissions.size - 1) {
                    val canProceed = isGranted || !step.isRequired
                    Button(
                        onClick = { currentStep++ },
                        enabled = canProceed,
                        modifier = Modifier.weight(1f).height(48.dp),
                        shape = RoundedCornerShape(24.dp)
                    ) {
                        Text(if (isGranted || !step.isRequired) "下一步" else "请先授权")
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(Icons.Default.KeyboardArrowRight, contentDescription = null, modifier = Modifier.size(20.dp))
                    }
                } else {
                    val allDone = missingPermissions.indices.all { i ->
                        !missingPermissions[i].isRequired || grantedStates[i]
                    }
                    Button(
                        onClick = { onComplete() },
                        enabled = allDone,
                        modifier = Modifier.weight(1f).height(48.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (allDone) StatusGreen
                                else MaterialTheme.colorScheme.outline
                        )
                    ) {
                        Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("开始使用")
                    }
                }
            }
        }
    }
}

@Composable
private fun StatusBadge(isGranted: Boolean, isRequired: Boolean) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = when {
            isGranted -> StatusGreen.copy(alpha = 0.1f)
            isRequired -> StatusRed.copy(alpha = 0.1f)
            else -> StatusOrange.copy(alpha = 0.1f)
        }
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = when {
                    isGranted -> Icons.Default.CheckCircle
                    isRequired -> Icons.Default.Close
                    else -> Icons.Default.Info
                },
                contentDescription = null,
                modifier = Modifier.size(18.dp),
                tint = when {
                    isGranted -> StatusGreen
                    isRequired -> StatusRed
                    else -> StatusOrange
                }
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                when {
                    isGranted -> "已授权"
                    isRequired -> "未授权 (必需)"
                    else -> "未授权 (可选)"
                },
                style = MaterialTheme.typography.labelLarge,
                color = when {
                    isGranted -> StatusGreen
                    isRequired -> StatusRed
                    else -> StatusOrange
                }
            )
        }
    }
}

private val StatusGreen = Color(0xFF4CAF50)
private val StatusRed = Color(0xFFF44336)
private val StatusOrange = Color(0xFFFFA000)

private val permissions = listOf(
    PermissionItem("accessibility", "无障碍服务", "咸鱼助手需要无障碍服务来模拟屏幕操作，包括点击、滑动、输入文字等，这是自动化任务的核心功能。", Icons.Default.Build, true,
        { ctx -> ctx.startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS).apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }) },
        { ctx -> val svc = "${ctx.packageName}/com.stardust.autojs.core.accessibility.AccessibilityService"; (Settings.Secure.getString(ctx.contentResolver, Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES) ?: "").contains(svc) }
    ),
    PermissionItem("overlay", "悬浮窗权限", "悬浮窗用于显示脚本运行日志、任务进度等浮动窗口，方便您实时查看自动化状态。", Icons.Default.ExitToApp, true,
        { ctx -> ctx.startActivity(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION).apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); data = android.net.Uri.parse("package:${ctx.packageName}") }) },
        { ctx -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) Settings.canDrawOverlays(ctx) else true }
    ),
    PermissionItem("notifications", "通知权限", "通知权限用于显示前台服务通知（常驻通知栏）和任务执行结果通知，确保服务稳定运行。", Icons.Default.Notifications, true,
        { ctx -> ctx.startActivity(Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); putExtra(Settings.EXTRA_APP_PACKAGE, ctx.packageName) }) },
        { ctx -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { val nm = ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager; nm.areNotificationsEnabled() } else true }
    ),
    PermissionItem("battery", "电池优化白名单", "关闭电池优化可以防止系统在后台自动终止脚本服务，确保长时间运行的自动化任务不被中断。", Icons.Default.Warning, true,
        { ctx -> ctx.startActivity(Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS).apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); data = android.net.Uri.parse("package:${ctx.packageName}") }) },
        { ctx -> val pm = ctx.getSystemService(Context.POWER_SERVICE) as PowerManager; pm.isIgnoringBatteryOptimizations(ctx.packageName) }
    ),
    PermissionItem("screen_capture", "屏幕截图", "屏幕截图权限用于在自动化任务中进行图像识别和界面分析，帮助脚本定位界面元素（使用时可动态申请）。", Icons.Default.Add, false, { _ -> }, { true })
)
