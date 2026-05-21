# Home Screen Redesign Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Refactor navigation architecture to a shared `MainScaffold` with persistent `NavigationBar`, and upgrade visuals with glassmorphism style while retaining existing purple color scheme.

**Architecture:** Nested NavHost — outer `rootNavController` handles flow routing (login → guide → permissions → main), plus overlay screens (Settings, DeviceActivation). Inner `mainNavController` inside `MainScaffold` handles tab switching among Home/Automation/Task/UserCenter with a persistent bottom bar.

**Tech Stack:** Kotlin, Jetpack Compose, Material 3, Navigation Compose

---

## File Structure

| File | Role |
|------|------|
| `ui/home/MainScaffold.kt` | **New** — Shared outer `Scaffold` with `NavigationBar` + inner `NavHost` for 4 tabs. Owns tab-switching logic and top bar routing. |
| `ui/navigation/NavGraph.kt` | **Modify** — Outer NavHost: login/guide/permissions/device-activation/settings stay standalone. `Screen.Home` route now hosts `MainScaffold` instead of `HomeScreen`. |
| `ui/home/HomeScreen.kt` | **Refactor** — Strip `Scaffold`/`SaltfishTopBar`/`SaltfishBottomBar`. Export `HomeTopBar` + `HomeContent` composables. Upgrade card visuals to glass style. |
| `ui/automation/AutomationScreen.kt` | **Modify** — Strip outer `Scaffold`, export `AutomationTopBar` + `AutomationContent`. |
| `ui/task/TaskScreen.kt` | **Modify** — Strip outer `Scaffold`, export `TaskTopBar` + `TaskContent`. |
| `ui/user/UserCenterScreen.kt` | **Modify** — Strip outer `Scaffold`, export `UserCenterTopBar` + `UserCenterContent`. |

---

### Task 1: Create `MainScaffold.kt` — shared navigation shell

**Files:**
- Create: `saltfish/src/main/java/com/saltfish/assistant/ui/home/MainScaffold.kt`

- [ ] **Step 1: Write `MainScaffold.kt`**

```kotlin
package com.saltfish.assistant.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.saltfish.assistant.ui.automation.AutomationContent
import com.saltfish.assistant.ui.automation.AutomationTopBar
import com.saltfish.assistant.ui.navigation.Screen
import com.saltfish.assistant.ui.task.TaskContent
import com.saltfish.assistant.ui.task.TaskTopBar
import com.saltfish.assistant.ui.user.UserCenterContent
import com.saltfish.assistant.ui.user.UserCenterTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(rootNavController: NavController) {
    val mainNavController = rememberNavController()
    val navBackStackEntry by mainNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Screen.Home.route

    Scaffold(
        topBar = {
            when (currentRoute) {
                Screen.Home.route -> HomeTopBar(
                    onSettings = { rootNavController.navigate(Screen.Settings.route) }
                )
                Screen.Automation.route -> AutomationTopBar()
                Screen.Task.route -> TaskTopBar()
                Screen.UserCenter.route -> UserCenterTopBar()
            }
        },
        bottomBar = {
            SaltfishBottomBar(
                currentRoute = currentRoute,
                onNavigate = { route ->
                    mainNavController.navigate(route) {
                        popUpTo(mainNavController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        NavHost(
            navController = mainNavController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeContent(rootNavController = rootNavController)
            }
            composable(Screen.Automation.route) {
                AutomationContent()
            }
            composable(Screen.Task.route) {
                TaskContent()
            }
            composable(Screen.UserCenter.route) {
                UserCenterContent(
                    onLogout = {
                        rootNavController.navigate(Screen.Login.route) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SaltfishBottomBar(currentRoute: String, onNavigate: (String) -> Unit) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.85f),
        tonalElevation = 0.dp
    ) {
        NavigationBarItem(
            selected = currentRoute == Screen.Home.route,
            onClick = { onNavigate(Screen.Home.route) },
            icon = {
                Icon(
                    if (currentRoute == Screen.Home.route) Icons.Default.Home else Icons.Default.Home,
                    contentDescription = null
                )
            },
            label = { Text("首页") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                selectedTextColor = MaterialTheme.colorScheme.primary,
                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                indicatorColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
            )
        )
        NavigationBarItem(
            selected = currentRoute == Screen.Automation.route,
            onClick = { onNavigate(Screen.Automation.route) },
            icon = { Icon(Icons.Default.PlayArrow, contentDescription = null) },
            label = { Text("自动化") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                selectedTextColor = MaterialTheme.colorScheme.primary,
                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                indicatorColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
            )
        )
        NavigationBarItem(
            selected = currentRoute == Screen.Task.route,
            onClick = { onNavigate(Screen.Task.route) },
            icon = { Icon(Icons.Default.List, contentDescription = null) },
            label = { Text("任务队列") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                selectedTextColor = MaterialTheme.colorScheme.primary,
                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                indicatorColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
            )
        )
        NavigationBarItem(
            selected = currentRoute == Screen.UserCenter.route,
            onClick = { onNavigate(Screen.UserCenter.route) },
            icon = { Icon(Icons.Default.AccountCircle, contentDescription = null) },
            label = { Text("用户中心") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                selectedTextColor = MaterialTheme.colorScheme.primary,
                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                indicatorColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
            )
        )
    }
}
```

- [ ] **Step 2: Verify build compiles for the new file**

Run: `./gradlew :saltfish:compileCommonDebugKotlin 2>&1 | tail -20`
Expected: BUILD SUCCESSFUL (with unresolved references from other files which will be fixed in later tasks)

- [ ] **Step 3: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/ui/home/MainScaffold.kt
git commit -m "feat: add MainScaffold with nested NavHost for shared navigation chrome"
```

---

### Task 2: Refactor `HomeScreen.kt` — extract `HomeTopBar` + `HomeContent` without Scaffold

**Files:**
- Modify: `saltfish/src/main/java/com/saltfish/assistant/ui/home/HomeScreen.kt` (rewrite)

- [ ] **Step 1: Rewrite `HomeScreen.kt`**

Replace the entire file content with:

```kotlin
package com.saltfish.assistant.ui.home

import android.content.Intent
import android.provider.Settings
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.saltfish.assistant.SaltfishApp
import com.saltfish.assistant.data.remote.SocketIOManager.ConnectionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(onSettings: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    modifier = Modifier.size(32.dp),
                    shape = MaterialTheme.shapes.small,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    tonalElevation = 2.dp,
                    shadowElevation = 4.dp
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
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
                    )
                }
            }
        },
        actions = {
            IconButton(onClick = onSettings) {
                Icon(
                    Icons.Default.Settings,
                    contentDescription = "设置",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.8f)
        )
    )
}

@Composable
fun HomeContent(
    rootNavController: NavController,
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    var showDeviceInfo by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        (context.applicationContext as SaltfishApp).lifecycleManager.onMainEntered()
    }

    // Background glow
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.06f),
                            Color.Transparent
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Spacer(modifier = Modifier.height(4.dp))

            // Hero status card — glass style
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f)
                ),
                border = BorderStroke(
                    0.5.dp,
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.08f)
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
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
                                Brush.linearGradient(
                                    listOf(
                                        MaterialTheme.colorScheme.primary,
                                        MaterialTheme.colorScheme.tertiary
                                    )
                                )
                            else null
                        )
                        Box(
                            modifier = Modifier
                                .width(1.dp)
                                .height(40.dp)
                                .background(MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                        )
                        HeroStatItem(
                            label = "任务状态",
                            value = when (uiState.taskState) {
                                is com.saltfish.assistant.engine.TaskExecutionState.Idle -> "空闲"
                                is com.saltfish.assistant.engine.TaskExecutionState.Running -> "运行中"
                            },
                            valueColor = null
                        )
                        Box(
                            modifier = Modifier
                                .width(1.dp)
                                .height(40.dp)
                                .background(MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                        )
                        HeroStatItem(
                            label = "适配器",
                            value = "${uiState.adapterVersions.size} 个",
                            valueColor = Brush.linearGradient(
                                listOf(
                                    MaterialTheme.colorScheme.primary,
                                    MaterialTheme.colorScheme.tertiary
                                )
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    LinearProgressIndicator(
                        progress = if (uiState.wsState == ConnectionState.CONNECTED) 1f else 0.3f,
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.primary,
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
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.6f)
                ),
                border = BorderStroke(
                    0.5.dp,
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.06f)
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
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
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
                    )
                    PermissionRow(
                        icon = Icons.Default.ExitToApp,
                        label = "悬浮窗权限",
                        isGranted = uiState.isFloatyPermissionGranted,
                        onClick = {
                            context.startActivity(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION))
                        }
                    )
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
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
                    if (showDeviceInfo) Icons.Default.KeyboardArrowUp
                    else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("设备信息", style = MaterialTheme.typography.labelMedium)
            }
            AnimatedVisibility(visible = showDeviceInfo) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.6f)
                    ),
                    border = BorderStroke(
                        0.5.dp,
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.06f)
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
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

            // CTA button — gradient style
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
                shape = MaterialTheme.shapes.small,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.horizontalGradient(
                                listOf(
                                    MaterialTheme.colorScheme.primary,
                                    MaterialTheme.colorScheme.tertiary
                                )
                            ),
                            shape = MaterialTheme.shapes.small
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
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
                }
            }

            // Adapter versions
            if (uiState.adapterVersions.isNotEmpty()) {
                Text(
                    "适配器版本",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(start = 4.dp)
                )
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.6f)
                    ),
                    border = BorderStroke(
                        0.5.dp,
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.06f)
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
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
    valueColor: Brush? = null
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(4.dp))
        if (valueColor != null) {
            Text(
                value,
                style = MaterialTheme.typography.titleMedium.copy(
                    brush = valueColor
                )
            )
        } else {
            Text(
                value,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
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
            Surface(
                shape = MaterialTheme.shapes.extraSmall,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
            ) {
                Text(
                    "已开启",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                )
            }
        } else {
            Surface(
                shape = MaterialTheme.shapes.extraSmall,
                color = MaterialTheme.colorScheme.error.copy(alpha = 0.08f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 8.dp, end = 4.dp, top = 2.dp, bottom = 2.dp)
                ) {
                    Text(
                        "去开启",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.error
                    )
                    Icon(
                        Icons.Default.KeyboardArrowRight,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp),
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
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
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.widthIn(max = 180.dp),
            overflow = TextOverflow.Ellipsis
        )
    }
}
```

- [ ] **Step 2: Verify build compiles for the refactored file**

Run: `./gradlew :saltfish:compileCommonDebugKotlin 2>&1 | tail -20`
Expected: BUILD SUCCESSFUL (may have warnings about unused imports in other files)

- [ ] **Step 3: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/ui/home/HomeScreen.kt
git commit -m "refactor: extract HomeTopBar + HomeContent, upgrade cards to glass style"
```

---

### Task 3: Refactor `AutomationScreen.kt` — export `AutomationTopBar` + `AutomationContent`

**Files:**
- Modify: `saltfish/src/main/java/com/saltfish/assistant/ui/automation/AutomationScreen.kt`

- [ ] **Step 1: Rewrite `AutomationScreen.kt`**

Replace the file to remove the outer `Scaffold` and export two composables:

```kotlin
package com.saltfish.assistant.ui.automation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.saltfish.assistant.engine.SchedulerState
import com.saltfish.assistant.engine.TaskExecutionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AutomationTopBar() {
    CenterAlignedTopAppBar(
        title = { Text("自动化控制") },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.8f)
        )
    )
}

@Composable
fun AutomationContent(viewModel: AutomationViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val logs by viewModel.logLines.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Service status
        ElevatedCard(shape = MaterialTheme.shapes.medium) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("脚本服务", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            modifier = Modifier.size(8.dp),
                            shape = MaterialTheme.shapes.extraSmall,
                            color = if (uiState.isServiceRunning)
                                MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.error
                        ) {}
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            if (uiState.isServiceRunning) "运行中" else "已停止",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
                if (uiState.isServiceRunning) {
                    OutlinedButton(
                        onClick = { viewModel.stopService() },
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = MaterialTheme.colorScheme.error
                        )
                    ) {
                        Icon(Icons.Default.Close, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("停止")
                    }
                } else {
                    Button(onClick = { viewModel.startService() }) {
                        Icon(Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("启动")
                    }
                }
            }
        }

        // Scheduler + Task state
        ElevatedCard(shape = MaterialTheme.shapes.medium) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("调度器", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(32.dp)) {
                    StatusItem("队列", "${uiState.schedulerState.queueSize}")
                    StatusItem("状态", if (uiState.schedulerState.isRunning) "执行中" else "空闲")
                }
                when (val ts = uiState.taskState) {
                    is TaskExecutionState.Idle -> {
                        Text("空闲", style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                    is TaskExecutionState.Running -> {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("${ts.platform} / ${ts.taskType}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary)
                        Text("进度: ${ts.progress} 步",
                            style = MaterialTheme.typography.bodySmall)
                        Spacer(modifier = Modifier.height(4.dp))
                        LinearProgressIndicator(
                            progress = (ts.progress % 100) / 100f,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }

        HorizontalDivider()

        // Platform selector
        Text("选择平台", style = MaterialTheme.typography.titleMedium)
        if (uiState.availablePlatforms.isEmpty()) {
            Text("暂无可用的适配器脚本",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant)
        } else {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                uiState.availablePlatforms.keys.take(6).forEach { platform ->
                    FilterChip(
                        selected = platform == uiState.selectedPlatform,
                        onClick = { viewModel.selectPlatform(platform) },
                        label = { Text(platformName(platform), style = MaterialTheme.typography.labelMedium) }
                    )
                }
            }
        }

        // Task type selector
        Text("任务类型", style = MaterialTheme.typography.titleMedium)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            AutomationViewModel.TASK_TYPES.take(5).forEach { (type, label) ->
                FilterChip(
                    selected = type == uiState.selectedTaskType,
                    onClick = { viewModel.selectTaskType(type) },
                    label = { Text(label, style = MaterialTheme.typography.labelMedium) }
                )
            }
        }

        // Task params
        Text("任务参数 (JSON)", style = MaterialTheme.typography.titleMedium)
        OutlinedTextField(
            value = uiState.taskParams,
            onValueChange = { viewModel.updateParams(it) },
            modifier = Modifier.fillMaxWidth().heightIn(min = 80.dp),
            isError = uiState.paramsError != null,
            maxLines = 5
        )
        uiState.paramsError?.let {
            Text(it, color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall)
        }

        // Submit
        Button(
            onClick = { viewModel.submitTask() },
            modifier = Modifier.fillMaxWidth().height(48.dp),
            enabled = uiState.submitEnabled,
            shape = MaterialTheme.shapes.small
        ) {
            Icon(Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("提交任务")
        }

        if (!uiState.submitEnabled && uiState.selectedPlatform.isNotEmpty()) {
            Text(
                when {
                    !uiState.isServiceRunning -> "请先启动脚本服务"
                    uiState.selectedTaskType.isEmpty() -> "请选择任务类型"
                    uiState.paramsError != null -> "请修正参数错误"
                    else -> ""
                },
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error
            )
        }

        HorizontalDivider()

        // Log section
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("运行日志", style = MaterialTheme.typography.titleMedium)
            IconButton(onClick = { viewModel.clearLogs() }) {
                Icon(Icons.Default.Delete, contentDescription = "清空日志", modifier = Modifier.size(18.dp))
            }
        }
        ElevatedCard(
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.heightIn(min = 120.dp, max = 300.dp)
        ) {
            if (logs.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxWidth().padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("暂无日志", style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            } else {
                val listState = rememberLazyListState()
                LaunchedEffect(logs.size) {
                    if (logs.isNotEmpty()) listState.animateScrollToItem(logs.size - 1)
                }
                LazyColumn(
                    state = listState,
                    modifier = Modifier.padding(8.dp)
                ) {
                    items(logs) { log ->
                        Text(
                            text = log,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontFamily = FontFamily.Monospace
                            ),
                            modifier = Modifier.padding(vertical = 1.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun StatusItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary)
        Text(label, style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

private fun platformName(packageName: String): String = when (packageName) {
    "com.taobao.idlefish" -> "闲鱼"
    "com.xunmeng.pinduoduo" -> "拼多多"
    "com.wuba.zhuanzhuan" -> "转转"
    "com.sina.weibo" -> "微博"
    "com.xingin.xhs" -> "小红书"
    "com.duoduo.tuanzhang" -> "多多团长"
    else -> packageName
}
```

- [ ] **Step 2: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/ui/automation/AutomationScreen.kt
git commit -m "refactor: extract AutomationTopBar + AutomationContent from Scaffold wrapper"
```

---

### Task 4: Refactor `TaskScreen.kt` — export `TaskTopBar` + `TaskContent`

**Files:**
- Modify: `saltfish/src/main/java/com/saltfish/assistant/ui/task/TaskScreen.kt`

- [ ] **Step 1: Rewrite `TaskScreen.kt`**

```kotlin
package com.saltfish.assistant.ui.task

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.saltfish.assistant.domain.model.TaskEntity
import com.saltfish.assistant.domain.model.TaskStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskTopBar() {
    CenterAlignedTopAppBar(
        title = { Text("任务队列") },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.8f)
        )
    )
}

@Composable
fun TaskContent(viewModel: TaskViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("待执行", "已完成", "失败")

    val filteredTasks = remember(uiState.tasks, selectedTab) {
        when (selectedTab) {
            0 -> uiState.tasks.filter { it.status == TaskStatus.PENDING || it.status == TaskStatus.RUNNING }
            1 -> uiState.tasks.filter { it.status == TaskStatus.COMPLETED }
            2 -> uiState.tasks.filter { it.status == TaskStatus.FAILED || it.status == TaskStatus.CANCELLED }
            else -> emptyList()
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(selectedTabIndex = selectedTab) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = { Text(title) }
                )
            }
        }
        if (filteredTasks.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        Icons.Default.List,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        "暂无任务",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filteredTasks, key = { it.id }) { task ->
                    TaskCard(
                        task = task,
                        onCancel = { viewModel.cancelTask(task.id) }
                    )
                }
            }
        }
    }
}

@Composable
private fun TaskCard(task: TaskEntity, onCancel: () -> Unit) {
    ElevatedCard(shape = MaterialTheme.shapes.medium) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(task.taskType, style = MaterialTheme.typography.titleSmall)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "平台: ${task.platform} | 优先级: ${task.priority}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    statusText(task.status),
                    style = MaterialTheme.typography.labelMedium,
                    color = statusColor(task.status)
                )
                task.errorMessage?.let {
                    Text(
                        it,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            if (task.status == TaskStatus.PENDING || task.status == TaskStatus.RUNNING) {
                IconButton(onClick = onCancel) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "取消",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

private fun statusText(status: TaskStatus): String = when (status) {
    TaskStatus.PENDING -> "等待中"
    TaskStatus.RUNNING -> "执行中"
    TaskStatus.COMPLETED -> "已完成"
    TaskStatus.FAILED -> "失败"
    TaskStatus.CANCELLED -> "已取消"
}

private fun statusColor(status: TaskStatus) = when (status) {
    TaskStatus.PENDING -> Color(0xFFFFA000)
    TaskStatus.RUNNING -> Color(0xFF2196F3)
    TaskStatus.COMPLETED -> Color(0xFF4CAF50)
    TaskStatus.FAILED -> Color(0xFFF44336)
    TaskStatus.CANCELLED -> Color(0xFF9E9E9E)
}
```

- [ ] **Step 2: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/ui/task/TaskScreen.kt
git commit -m "refactor: extract TaskTopBar + TaskContent from Scaffold wrapper"
```

---

### Task 5: Refactor `UserCenterScreen.kt` — export `UserCenterTopBar` + `UserCenterContent`

**Files:**
- Modify: `saltfish/src/main/java/com/saltfish/assistant/ui/user/UserCenterScreen.kt`

- [ ] **Step 1: Rewrite `UserCenterScreen.kt`**

```kotlin
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
                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
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
                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
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
```

- [ ] **Step 2: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/ui/user/UserCenterScreen.kt
git commit -m "refactor: extract UserCenterTopBar + UserCenterContent from Scaffold wrapper"
```

---

### Task 6: Refactor `NavGraph.kt` — wire `MainScaffold` as the main screen host

**Files:**
- Modify: `saltfish/src/main/java/com/saltfish/assistant/ui/navigation/NavGraph.kt`

- [ ] **Step 1: Rewrite `NavGraph.kt`**

```kotlin
package com.saltfish.assistant.ui.navigation

import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.saltfish.assistant.SaltfishApp
import com.saltfish.assistant.engine.DeviceState
import com.saltfish.assistant.ui.activation.ActivationMode
import com.saltfish.assistant.ui.activation.DeviceActivationScreen
import com.saltfish.assistant.ui.guide.GuideScreen
import com.saltfish.assistant.ui.home.MainScaffold
import com.saltfish.assistant.ui.login.LoginScreen
import com.saltfish.assistant.ui.permissions.PermissionsGuideScreen
import com.saltfish.assistant.ui.settings.SettingsScreen
import kotlinx.coroutines.launch

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Task : Screen("task")
    object Automation : Screen("automation")
    object Settings : Screen("settings")
    object Login : Screen("login")
    object UserCenter : Screen("user_center")
    object PermissionsGuide : Screen("permissions_guide")
    object Guide : Screen("guide")
    object Log : Screen("log")
    object DeviceActivation : Screen("device_activation")
}

@Composable
fun SaltfishNavGraph(
    splashLoggedIn: Boolean? = null,
    onSplashOverrideConsumed: () -> Unit = {}
) {
    val rootNavController = rememberNavController()
    val context = androidx.compose.ui.platform.LocalContext.current
    val app = context.applicationContext as SaltfishApp
    val lifecycleManager = app.lifecycleManager
    val scope = rememberCoroutineScope()

    LaunchedEffect(splashLoggedIn) {
        if (splashLoggedIn != null) onSplashOverrideConsumed()
    }

    var startRoute by remember { mutableStateOf<String?>(null) }
    LaunchedEffect(Unit) {
        startRoute = lifecycleManager.resolveStartRoute(splashLoggedIn)
    }

    if (startRoute == null) return

    NavHost(navController = rootNavController, startDestination = startRoute!!) {
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    scope.launch {
                        val dest = lifecycleManager.onLoginSuccess()
                        rootNavController.navigate(dest) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    }
                }
            )
        }
        composable(Screen.Guide.route) {
            GuideScreen(
                onDone = {
                    rootNavController.navigate(lifecycleManager.onGuideDone()) {
                        popUpTo(Screen.Guide.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.PermissionsGuide.route) {
            PermissionsGuideScreen(
                onComplete = {
                    rootNavController.navigate(lifecycleManager.onPermissionsDone()) {
                        popUpTo(Screen.PermissionsGuide.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.DeviceActivation.route) {
            val mode = when (app.deviceManager.state) {
                DeviceState.Idle -> ActivationMode.Register
                else -> ActivationMode.Renew
            }
            DeviceActivationScreen(
                mode = mode,
                onDone = {
                    scope.launch {
                        val dest = lifecycleManager.onDeviceActivated()
                        rootNavController.navigate(dest) {
                            popUpTo(Screen.DeviceActivation.route) { inclusive = true }
                        }
                    }
                }
            )
        }
        composable(Screen.Settings.route) {
            SettingsScreen()
        }
        // Main screen hosts the inner NavHost for tabs via MainScaffold
        composable(Screen.Home.route) {
            MainScaffold(rootNavController = rootNavController)
        }
    }
}
```

- [ ] **Step 2: Verify build compiles all together**

Run: `./gradlew :saltfish:compileCommonDebugKotlin 2>&1 | tail -30`
Expected: BUILD SUCCESSFUL

- [ ] **Step 3: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/ui/navigation/NavGraph.kt
git commit -m "refactor: wire MainScaffold as main screen host in NavGraph"
```

---

### Task 7: Full build verification

- [ ] **Step 1: Run full debug build**

```bash
./gradlew :saltfish:assembleCommonDebug 2>&1 | tail -20
```
Expected: BUILD SUCCESSFUL

- [ ] **Step 2: Run unit tests**

```bash
./gradlew :saltfish:test 2>&1 | tail -20
```
Expected: BUILD SUCCESSFUL (tests pass or skip if none exist)

- [ ] **Step 3: Final commit (if any fixups were needed)**

```bash
git add -A
git commit -m "chore: final build verification fixes"
```
(Only if changes were needed. Otherwise skip.)
