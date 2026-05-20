package com.saltfish.assistant.ui.home

import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.saltfish.assistant.data.remote.SocketIOManager.ConnectionState
import com.saltfish.assistant.engine.TaskExecutionState
import com.saltfish.assistant.ui.components.PermissionItem
import com.saltfish.assistant.ui.components.StatusCard
import com.saltfish.assistant.ui.navigation.Screen

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("咸鱼助手") },
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.Settings.route) }) {
                        Icon(Icons.Default.Settings, contentDescription = "设置")
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    selected = true,
                    onClick = {},
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    label = { Text("首页") }
                )
                BottomNavigationItem(
                    selected = false,
                    onClick = { navController.navigate(Screen.Task.route) },
                    icon = { Icon(Icons.Default.List, contentDescription = null) },
                    label = { Text("任务") }
                )
                BottomNavigationItem(
                    selected = false,
                    onClick = {},
                    icon = { Icon(Icons.Default.PlayArrow, contentDescription = null) },
                    label = { Text("自动化") }
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Connection status
            StatusCard(
                title = "服务器连接",
                value = when (uiState.wsState) {
                    ConnectionState.CONNECTED -> "已连接"
                    ConnectionState.CONNECTING -> "连接中..."
                    ConnectionState.RECONNECTING -> "重连中..."
                    ConnectionState.DISCONNECTED -> "未连接"
                },
                statusColor = when (uiState.wsState) {
                    ConnectionState.CONNECTED -> Color(0xFF4CAF50)
                    else -> Color(0xFFFF5722)
                }
            )

            // Task status
            StatusCard(
                title = "当前任务",
                value = when (val ts = uiState.taskState) {
                    is TaskExecutionState.Idle -> "空闲"
                    is TaskExecutionState.Running -> "运行中: ${ts.taskType}"
                },
                statusColor = when (uiState.taskState) {
                    is TaskExecutionState.Idle -> Color(0xFF9E9E9E)
                    is TaskExecutionState.Running -> Color(0xFF2196F3)
                }
            )

            // Device info
            Card(elevation = 2.dp) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("设备信息", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("设备: ${uiState.deviceName}", fontSize = 14.sp)
                    Text("内存: ${uiState.availableMemory}", fontSize = 14.sp)
                    Text("电量: ${uiState.batteryLevel}%", fontSize = 14.sp)
                    Text("用户: ${uiState.username}", fontSize = 14.sp)
                }
            }

            // Permissions
            Card(elevation = 2.dp) {
                Column {
                    Text(
                        "系统权限",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(16.dp, 12.dp)
                    )
                    PermissionItem(
                        label = "无障碍服务",
                        isGranted = uiState.isAccessibilityEnabled,
                        onClick = {
                            context.startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
                        }
                    )
                    PermissionItem(
                        label = "悬浮窗权限",
                        isGranted = uiState.isFloatyPermissionGranted,
                        onClick = {
                            context.startActivity(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION))
                        }
                    )
                    PermissionItem(
                        label = "电池优化白名单",
                        isGranted = uiState.isIgnoringBattery,
                        onClick = {
                            context.startActivity(Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS))
                        }
                    )
                }
            }

            // Adapter versions
            if (uiState.adapterVersions.isNotEmpty()) {
                Card(elevation = 2.dp) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("适配器版本", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        uiState.adapterVersions.forEach { (platform, version) ->
                            Text("$platform: v$version", fontSize = 13.sp)
                        }
                    }
                }
            }

            // Connect/Disconnect button
            Button(
                onClick = {
                    if (uiState.wsState == ConnectionState.CONNECTED) {
                        viewModel.disconnectWebSocket()
                    } else {
                        viewModel.connectWebSocket()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    if (uiState.wsState == ConnectionState.CONNECTED) "断开连接" else "连接服务器"
                )
            }
        }
    }
}
