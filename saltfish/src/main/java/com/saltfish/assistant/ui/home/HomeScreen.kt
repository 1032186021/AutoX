package com.saltfish.assistant.ui.home

import android.content.Intent
import android.provider.Settings
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.saltfish.assistant.data.remote.SocketIOManager.ConnectionState
import com.saltfish.assistant.engine.TaskExecutionState
import com.saltfish.assistant.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    var showDeviceInfo by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            SaltfishTopBar(onLogClick = { navController.navigate(Screen.Settings.route) })
        },
        bottomBar = {
            SaltfishBottomBar(
                currentRoute = Screen.Home.route,
                onNavigate = { route -> navController.navigate(route) {
                    popUpTo(Screen.Home.route) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }}
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Spacer(modifier = Modifier.height(4.dp))

            // Hero status card
            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        HeroStatItem(
                            label = "服务器",
                            value = when (uiState.wsState) {
                                ConnectionState.CONNECTED -> "已连接"
                                else -> "未连接"
                            },
                            valueColor = if (uiState.wsState == ConnectionState.CONNECTED)
                                MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.error
                        )
                        Box(modifier = Modifier.width(1.dp).height(40.dp).background(MaterialTheme.colorScheme.outlineVariant))
                        HeroStatItem(
                            label = "任务状态",
                            value = when (uiState.taskState) {
                                is TaskExecutionState.Idle -> "空闲"
                                is TaskExecutionState.Running -> "运行中"
                            },
                            valueColor = MaterialTheme.colorScheme.onSurface
                        )
                        Box(modifier = Modifier.width(1.dp).height(40.dp).background(MaterialTheme.colorScheme.outlineVariant))
                        HeroStatItem(
                            label = "适配器",
                            value = "${uiState.adapterVersions.size} 个",
                            valueColor = MaterialTheme.colorScheme.primary
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    LinearProgressIndicator(
                        progress = if (uiState.wsState == ConnectionState.CONNECTED) 1f else 0.3f,
                        modifier = Modifier.fillMaxWidth(),
                        color = if (uiState.wsState == ConnectionState.CONNECTED)
                            MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.error,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    )
                }
            }

            // Permission section
            Text(
                "系统权限",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(start = 4.dp)
            )
            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium
            ) {
                Column {
                    PermissionRow(
                        icon = Icons.Default.Build,
                        label = "无障碍服务",
                        isGranted = uiState.isAccessibilityEnabled,
                        onClick = {
                            context.startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
                        }
                    )
                    Divider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                    )
                    PermissionRow(
                        icon = Icons.Default.ExitToApp,
                        label = "悬浮窗权限",
                        isGranted = uiState.isFloatyPermissionGranted,
                        onClick = {
                            context.startActivity(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION))
                        }
                    )
                    Divider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                    )
                    PermissionRow(
                        icon = Icons.Default.Warning,
                        label = "电池优化白名单",
                        isGranted = uiState.isIgnoringBattery,
                        onClick = {
                            context.startActivity(Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS))
                        }
                    )
                }
            }

            // Device info expandable
            TextButton(
                onClick = { showDeviceInfo = !showDeviceInfo },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    if (showDeviceInfo) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("设备信息", style = MaterialTheme.typography.labelMedium)
            }
            AnimatedVisibility(visible = showDeviceInfo) {
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        val di = uiState.deviceInfo
                        DeviceInfoRow("设备名称", di.name)
                        DeviceInfoRow("品牌", di.brand)
                        DeviceInfoRow("型号", di.model)
                        DeviceInfoRow("Android", "${di.androidVersion} (SDK ${di.sdkLevel})")
                        DeviceInfoRow("CPU", di.cpuAbi)
                        DeviceInfoRow("Root", if (di.isRooted) "已 Root" else "未 Root")
                        DeviceInfoRow("UUID", di.uuid.take(12) + "...")
                        DeviceInfoRow("内存", "${di.availableMemory} / ${di.totalMemory}")
                        DeviceInfoRow("存储", "${di.availableStorage} / ${di.totalStorage}")
                        DeviceInfoRow("电量", "${di.batteryLevel}% (${di.batteryStatus})")
                        DeviceInfoRow("分辨率", "${di.screenWidth} x ${di.screenHeight}")
                        DeviceInfoRow("网络", "${di.networkType} / ${di.ipAddress}")
                    }
                }
            }

            // CTA button
            Button(
                onClick = {
                    if (uiState.wsState == ConnectionState.CONNECTED) {
                        viewModel.disconnectWebSocket()
                    } else {
                        viewModel.connectWebSocket()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = MaterialTheme.shapes.small
            ) {
                Icon(
                    if (uiState.wsState == ConnectionState.CONNECTED)
                        Icons.Default.Close else Icons.Default.PlayArrow,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    if (uiState.wsState == ConnectionState.CONNECTED) "断开连接"
                    else "连接服务器"
                )
            }

            // Adapter versions
            if (uiState.adapterVersions.isNotEmpty()) {
                Text(
                    "适配器版本",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(start = 4.dp)
                )
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        uiState.adapterVersions.forEach { (platform, version) ->
                            Text(
                                "$platform: v$version",
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(vertical = 2.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun HeroStatItem(
    label: String,
    value: String,
    valueColor: androidx.compose.ui.graphics.Color
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            value,
            style = MaterialTheme.typography.titleMedium,
            color = valueColor
        )
    }
}

@Composable
private fun PermissionRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    isGranted: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            icon,
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = if (isGranted) MaterialTheme.colorScheme.primary
                   else MaterialTheme.colorScheme.error
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        if (isGranted) {
            Icon(
                Icons.Default.Check,
                contentDescription = null,
                modifier = Modifier.size(18.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        } else {
            Text(
                "去开启",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.error
            )
            Icon(
                Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier.size(18.dp),
                tint = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Composable
private fun DeviceInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            value,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaltfishTopBar(onLogClick: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    modifier = Modifier.size(32.dp),
                    shape = MaterialTheme.shapes.small,
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text("🐟", style = MaterialTheme.typography.labelMedium)
                    }
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        "咸鱼助手",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        "智能自动化平台",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        },
        actions = {
            IconButton(onClick = onLogClick) {
                Icon(
                    Icons.Default.List,
                    contentDescription = "日志",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaltfishBottomBar(currentRoute: String, onNavigate: (String) -> Unit) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 0.dp
    ) {
        NavigationBarItem(
            selected = currentRoute == Screen.Home.route,
            onClick = { onNavigate(Screen.Home.route) },
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text("首页") }
        )
        NavigationBarItem(
            selected = currentRoute == Screen.Automation.route,
            onClick = { onNavigate(Screen.Automation.route) },
            icon = { Icon(Icons.Default.PlayArrow, contentDescription = null) },
            label = { Text("自动化") }
        )
        NavigationBarItem(
            selected = currentRoute == Screen.Task.route,
            onClick = { onNavigate(Screen.Task.route) },
            icon = { Icon(Icons.Default.List, contentDescription = null) },
            label = { Text("任务队列") }
        )
        NavigationBarItem(
            selected = currentRoute == Screen.UserCenter.route,
            onClick = { onNavigate(Screen.UserCenter.route) },
            icon = { Icon(Icons.Default.AccountCircle, contentDescription = null) },
            label = { Text("用户中心") }
        )
    }
}
