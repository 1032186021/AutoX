package com.saltfish.assistant.ui.user

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.saltfish.assistant.SaltfishApp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserCenterTopBar() {
    CenterAlignedTopAppBar(
        title = { Text("用户中心") },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.8f)
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserCenterContent(
    onLogout: () -> Unit
) {
    val context = LocalContext.current
    val app = context.applicationContext as SaltfishApp
    val prefs = app.preferencesManager
    var showLogoutDialog by remember { mutableStateOf(false) }

    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            title = { Text("退出登录") },
            text = { Text("确定要退出登录吗？") },
            confirmButton = {
                TextButton(onClick = {
                    showLogoutDialog = false
                    onLogout()
                }) {
                    Text("确定", color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = { showLogoutDialog = false }) {
                    Text("取消")
                }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        // Avatar
        Surface(
            modifier = Modifier.size(72.dp),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp),
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Username
        Text(
            prefs.nickName ?: "未设置昵称",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(4.dp))

        // Status chip
        AssistChip(
            onClick = {},
            label = {
                Text(
                    if (prefs.isLoggedIn()) "已登录" else "未登录",
                    style = MaterialTheme.typography.labelMedium
                )
            },
            leadingIcon = {
                Icon(
                    if (prefs.isLoggedIn()) Icons.Default.CheckCircle else Icons.Default.Close,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Account section
        SectionHeader("账号管理")
        ElevatedCard(shape = MaterialTheme.shapes.medium, modifier = Modifier.fillMaxWidth()) {
            Column {
                CenterItem(
                    icon = Icons.Default.AccountCircle,
                    title = "个人信息",
                    subtitle = prefs.nickName ?: "查看/修改个人信息"
                )
                Divider(modifier = Modifier.padding(horizontal = 16.dp))
                CenterItem(
                    icon = Icons.Default.Lock,
                    title = "权限列表",
                    subtitle = "查看当前权限"
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Data section
        SectionHeader("数据管理")
        ElevatedCard(shape = MaterialTheme.shapes.medium, modifier = Modifier.fillMaxWidth()) {
            Column {
                CenterItem(
                    icon = Icons.Default.Refresh,
                    title = "同步数据",
                    subtitle = "从服务器同步最新配置"
                )
                Divider(modifier = Modifier.padding(horizontal = 16.dp))
                CenterItem(
                    icon = Icons.Default.Delete,
                    title = "清除缓存",
                    subtitle = "清除本地缓存数据",
                    onClick = {
                        context.cacheDir.deleteRecursively()
                        context.cacheDir.mkdirs()
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // About section
        SectionHeader("关于")
        ElevatedCard(shape = MaterialTheme.shapes.medium, modifier = Modifier.fillMaxWidth()) {
            CenterItem(
                icon = Icons.Default.Info,
                title = "咸鱼助手",
                subtitle = "v1.0.0 · 基于 AutoX 引擎"
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Logout button
        OutlinedButton(
            onClick = { showLogoutDialog = true },
            modifier = Modifier.fillMaxWidth().height(48.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colorScheme.error
            ),
            shape = MaterialTheme.shapes.small
        ) {
            Icon(Icons.Default.ExitToApp, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("退出登录")
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun SectionHeader(title: String) {
    Text(
        title,
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, bottom = 8.dp)
    )
}

@Composable
private fun CenterItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            icon,
            contentDescription = null,
            modifier = Modifier.size(22.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, style = MaterialTheme.typography.bodyLarge)
            Text(
                subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Icon(
            Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
        )
    }
}
