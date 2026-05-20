# Saltfish M3 UI Redesign Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Migrate saltfish module from Material 2 to Material Design 3 with complete visual redesign.

**Architecture:** Replace `androidx.compose.material` with `material3` 1.0.1, rebuild theme on M3 ColorScheme/Typography/Shapes, then rewrite all 7 screens + 2 components with new M3 component APIs. Immersive edge-to-edge layout with 4-tab NavigationBar and custom TopBar with logo/title/log button.

**Tech Stack:** Kotlin 1.6.21, Compose Compiler 1.2.0, Compose UI 1.2.0-rc01, Material3 1.0.1

**Verification:** After each task, run `./gradlew :saltfish:compileCommonDebugKotlin` to verify compilation. After all tasks complete, build full APK with `./gradlew :saltfish:assembleCommonDebug`.

---

### Task 1: Update Dependencies

**Files:**
- Modify: `gradle/libs.versions.toml`
- Modify: `saltfish/build.gradle.kts`

- [ ] **Step 1: Add material3 to version catalog**

```toml
# In gradle/libs.versions.toml, add under [versions]:
material3-version = "1.0.1"

# In gradle/libs.versions.toml, add under [libraries]:
compose-material3 = { module = "androidx.compose.material3:material3", version.ref = "material3-version" }
```

- [ ] **Step 2: Replace dependencies in saltfish build.gradle.kts**

In `saltfish/build.gradle.kts`, replace:
```kotlin
implementation(libs.compose.material)
```
with:
```kotlin
implementation(libs.compose.material3)
```

Remove these two lines:
```kotlin
implementation(libs.accompanist.insets)
implementation(libs.accompanist.systemuicontroller)
```

- [ ] **Step 3: Verify version catalog parses**

Run: `./gradlew :saltfish:dependencies --configuration commonDebugRuntimeClasspath 2>&1 | grep material3`
Expected: Output includes `androidx.compose.material3:material3:1.0.1`

- [ ] **Step 4: Commit**

```bash
git add gradle/libs.versions.toml saltfish/build.gradle.kts
git commit -m "build: add material3 1.0.1 dependency, remove accompanist-insets"
```

---

### Task 2: Create M3 Color Palette

**Files:**
- Modify: `saltfish/src/main/java/com/saltfish/assistant/ui/theme/Color.kt`

- [ ] **Step 1: Write the M3 tonal color palette**

Replace the entire content of `Color.kt`:

```kotlin
package com.saltfish.assistant.ui.theme

import androidx.compose.ui.graphics.Color

// Primary - #6750A4
val Primary0 = Color(0xFF000000)
val Primary10 = Color(0xFF21005D)
val Primary20 = Color(0xFF381E72)
val Primary30 = Color(0xFF4F378B)
val Primary40 = Color(0xFF6750A4)
val Primary50 = Color(0xFF7F67BE)
val Primary60 = Color(0xFF9A82D0)
val Primary70 = Color(0xFFB69DFF)
val Primary80 = Color(0xFFD0BCFF)
val Primary90 = Color(0xFFEADDFF)
val Primary95 = Color(0xFFF6EDFF)
val Primary99 = Color(0xFFFFFBFE)
val Primary100 = Color(0xFFFFFFFF)

// Secondary
val Secondary0 = Color(0xFF000000)
val Secondary10 = Color(0xFF1D192B)
val Secondary20 = Color(0xFF332D41)
val Secondary30 = Color(0xFF4A4458)
val Secondary40 = Color(0xFF625B71)
val Secondary50 = Color(0xFF7A7289)
val Secondary60 = Color(0xFF958DA4)
val Secondary70 = Color(0xFFB0A7BF)
val Secondary80 = Color(0xFFCCC2DC)
val Secondary90 = Color(0xFFE8DEF8)
val Secondary95 = Color(0xFFF6EDFF)
val Secondary99 = Color(0xFFFFFBFE)
val Secondary100 = Color(0xFFFFFFFF)

// Tertiary
val Tertiary0 = Color(0xFF000000)
val Tertiary10 = Color(0xFF31101D)
val Tertiary20 = Color(0xFF492532)
val Tertiary30 = Color(0xFF633B48)
val Tertiary40 = Color(0xFF7D5260)
val Tertiary50 = Color(0xFF986977)
val Tertiary60 = Color(0xFFB58392)
val Tertiary70 = Color(0xFFD29DAC)
val Tertiary80 = Color(0xFFEFB8C8)
val Tertiary90 = Color(0xFFFFD8E4)
val Tertiary95 = Color(0xFFFFECF0)
val Tertiary99 = Color(0xFFFFFBFA)
val Tertiary100 = Color(0xFFFFFFFF)

// Neutral
val Neutral0 = Color(0xFF000000)
val Neutral10 = Color(0xFF1C1B1F)
val Neutral20 = Color(0xFF313033)
val Neutral30 = Color(0xFF48464A)
val Neutral40 = Color(0xFF605D62)
val Neutral50 = Color(0xFF79767A)
val Neutral60 = Color(0xFF939094)
val Neutral70 = Color(0xFFAEAAAE)
val Neutral80 = Color(0xFFCAC4D0)
val Neutral90 = Color(0xFFE6E1E5)
val Neutral95 = Color(0xFFF4EFF4)
val Neutral99 = Color(0xFFFFFBFE)
val Neutral100 = Color(0xFFFFFFFF)

// Neutral Variant
val NeutralVariant30 = Color(0xFF49454F)
val NeutralVariant50 = Color(0xFF79747E)
val NeutralVariant60 = Color(0xFF938F99)
val NeutralVariant80 = Color(0xFFCAC4D0)
val NeutralVariant90 = Color(0xFFE7E0EC)

// Error
val Error0 = Color(0xFF000000)
val Error10 = Color(0xFF410E0B)
val Error20 = Color(0xFF601410)
val Error30 = Color(0xFF8C1D18)
val Error40 = Color(0xFFB3261E)
val Error50 = Color(0xFFDC362E)
val Error60 = Color(0xFFE46962)
val Error70 = Color(0xFFEC928E)
val Error80 = Color(0xFFF2B8B5)
val Error90 = Color(0xFFF9DEDC)
val Error95 = Color(0xFFFCEEEE)
val Error99 = Color(0xFFFFFBFA)
val Error100 = Color(0xFFFFFFFF)

// Status colors
val StatusGreen = Color(0xFF4CAF50)
val StatusRed = Color(0xFFF44336)
val StatusOrange = Color(0xFFFF9800)
val StatusBlue = Color(0xFF2196F3)
val StatusGray = Color(0xFF9E9E9E)
```

- [ ] **Step 2: Verify compilation**

Run: `./gradlew :saltfish:compileCommonDebugKotlin`
Expected: BUILD SUCCESSFUL

- [ ] **Step 3: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/ui/theme/Color.kt
git commit -m "refactor: replace M2 colors with M3 tonal palette based on #6750A4"
```

---

### Task 3: Create M3 Typography

**Files:**
- Create: `saltfish/src/main/java/com/saltfish/assistant/ui/theme/Type.kt`

- [ ] **Step 1: Write the Type.kt file**

```kotlin
package com.saltfish.assistant.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val SaltfishTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp
    ),
    displayMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp
    ),
    displaySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)
```

- [ ] **Step 2: Verify compilation**

Run: `./gradlew :saltfish:compileCommonDebugKotlin`
Expected: BUILD SUCCESSFUL

- [ ] **Step 3: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/ui/theme/Type.kt
git commit -m "feat: add M3 typography system"
```

---

### Task 4: Create M3 Theme

**Files:**
- Modify: `saltfish/src/main/java/com/saltfish/assistant/ui/theme/Theme.kt`

- [ ] **Step 1: Rewrite Theme.kt with M3**

Replace the entire content of `Theme.kt`:

```kotlin
package com.saltfish.assistant.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = Primary40,
    onPrimary = Primary100,
    primaryContainer = Primary90,
    onPrimaryContainer = Primary10,
    secondary = Secondary40,
    onSecondary = Secondary100,
    secondaryContainer = Secondary90,
    onSecondaryContainer = Secondary10,
    tertiary = Tertiary40,
    onTertiary = Tertiary100,
    tertiaryContainer = Tertiary90,
    onTertiaryContainer = Tertiary10,
    error = Error40,
    onError = Error100,
    errorContainer = Error90,
    onErrorContainer = Error10,
    background = Neutral99,
    onBackground = Neutral10,
    surface = Neutral95,
    onSurface = Neutral10,
    surfaceVariant = NeutralVariant90,
    onSurfaceVariant = NeutralVariant30,
    outline = NeutralVariant50,
    outlineVariant = NeutralVariant80,
    inverseSurface = Neutral20,
    inverseOnSurface = Neutral95,
    inversePrimary = Primary80
)

private val DarkColorScheme = darkColorScheme(
    primary = Primary80,
    onPrimary = Primary20,
    primaryContainer = Primary30,
    onPrimaryContainer = Primary90,
    secondary = Secondary80,
    onSecondary = Secondary20,
    secondaryContainer = Secondary30,
    onSecondaryContainer = Secondary90,
    tertiary = Tertiary80,
    onTertiary = Tertiary20,
    tertiaryContainer = Tertiary30,
    onTertiaryContainer = Tertiary90,
    error = Error80,
    onError = Error20,
    errorContainer = Error30,
    onErrorContainer = Error90,
    background = Neutral10,
    onBackground = Neutral90,
    surface = Neutral10,
    onSurface = Neutral90,
    surfaceVariant = NeutralVariant30,
    onSurfaceVariant = NeutralVariant80,
    outline = NeutralVariant60,
    outlineVariant = NeutralVariant30,
    inverseSurface = Neutral90,
    inverseOnSurface = Neutral20,
    inversePrimary = Primary40
)

private val SaltfishShapes = Shapes(
    extraSmall = RoundedCornerShape(8.dp),
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(24.dp),
    extraLarge = RoundedCornerShape(28.dp)
)

@Composable
fun SaltfishTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val view = LocalView.current
            if (!view.isInEditMode) {
                SideEffect {
                    val window = (view.context as Activity).window
                    window.statusBarColor = colorScheme.primary.toArgb()
                }
            }
            if (darkTheme) DarkColorScheme else LightColorScheme
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.setDecorFitsSystemWindows(window, false)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                window.isNavigationBarContrastEnforced = false
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                window.setDecorFitsSystemWindows(false)
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = SaltfishTypography,
        shapes = SaltfishShapes,
        content = content
    )
}
```

- [ ] **Step 2: Verify compilation**

Run: `./gradlew :saltfish:compileCommonDebugKotlin`
Expected: FAIL — other files still import M2. This confirms the M3 theme compiles independently. Proceed.

- [ ] **Step 3: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/ui/theme/Theme.kt
git commit -m "refactor: rewrite theme with M3 ColorScheme, Typography, Shapes, edge-to-edge"
```

---

### Task 5: Update StatusCard Component

**Files:**
- Modify: `saltfish/src/main/java/com/saltfish/assistant/ui/components/StatusCard.kt`

- [ ] **Step 1: Rewrite StatusCard with M3**

Replace entire file:

```kotlin
package com.saltfish.assistant.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun StatusCard(
    title: String,
    value: String,
    statusColor: Color,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                title,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    modifier = Modifier.size(8.dp),
                    shape = MaterialTheme.shapes.extraSmall,
                    color = statusColor
                ) {}
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    value,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}
```

- [ ] **Step 2: Verify compilation**

Run: `./gradlew :saltfish:compileCommonDebugKotlin`
Expected: FAIL — StatusCard compiles, but other files still use M2. This is expected.

- [ ] **Step 3: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/ui/components/StatusCard.kt
git commit -m "refactor: migrate StatusCard to M3 ElevatedCard and typography"
```

---

### Task 6: Update PermissionItem Component

**Files:**
- Modify: `saltfish/src/main/java/com/saltfish/assistant/ui/components/PermissionItem.kt`

- [ ] **Step 1: Rewrite PermissionItem with M3**

Replace entire file:

```kotlin
package com.saltfish.assistant.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PermissionItem(
    label: String,
    isGranted: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        color = MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(8.dp),
                shape = MaterialTheme.shapes.extraSmall,
                color = if (isGranted) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.error
                }
            ) {}
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = label,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = if (isGranted) "已授权" else "未授权",
                style = MaterialTheme.typography.labelMedium,
                color = if (isGranted) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.error
                }
            )
        }
    }
}
```

- [ ] **Step 2: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/ui/components/PermissionItem.kt
git commit -m "refactor: migrate PermissionItem to M3 Surface and colorScheme"
```

---

### Task 7: Redesign HomeScreen

**Files:**
- Modify: `saltfish/src/main/java/com/saltfish/assistant/ui/home/HomeScreen.kt`

- [ ] **Step 1: Rewrite HomeScreen with M3 mixed layout (style C)**

Replace entire file:

```kotlin
package com.saltfish.assistant.ui.home

import android.content.Intent
import android.provider.Settings
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
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
            SaltfishTopBar(onLogClick = { navController.navigate(Screen.Log.route) })
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
                        VerticalDivider(
                            modifier = Modifier.height(40.dp),
                            color = MaterialTheme.colorScheme.outlineVariant
                        )
                        HeroStatItem(
                            label = "任务状态",
                            value = when (uiState.taskState) {
                                is TaskExecutionState.Idle -> "空闲"
                                is TaskExecutionState.Running -> "运行中"
                            },
                            valueColor = MaterialTheme.colorScheme.onSurface
                        )
                        VerticalDivider(
                            modifier = Modifier.height(40.dp),
                            color = MaterialTheme.colorScheme.outlineVariant
                        )
                        HeroStatItem(
                            label = "适配器",
                            value = "${uiState.adapterVersions.size} 个",
                            valueColor = MaterialTheme.colorScheme.primary
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    // Progress indicator bar
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
                        icon = Icons.Default.Accessibility,
                        label = "无障碍服务",
                        isGranted = uiState.isAccessibilityEnabled,
                        onClick = {
                            context.startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
                        }
                    )
                    HorizontalDivider(
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
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                    )
                    PermissionRow(
                        icon = Icons.Default.BatteryAlert,
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
                    if (showDeviceInfo) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
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
```

- [ ] **Step 2: Verify compilation**

Run: `./gradlew :saltfish:compileCommonDebugKotlin`
Expected: FAIL — HomeScreen compiles but other files still reference M2. Proceed.

- [ ] **Step 3: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/ui/home/HomeScreen.kt
git commit -m "refactor: redesign HomeScreen with M3 mixed layout, SaltfishTopBar, SaltfishBottomBar"
```

---

### Task 8: Redesign AutomationScreen

**Files:**
- Modify: `saltfish/src/main/java/com/saltfish/assistant/ui/automation/AutomationScreen.kt`

- [ ] **Step 1: Rewrite AutomationScreen with M3**

Replace entire file:

```kotlin
package com.saltfish.assistant.ui.automation

import androidx.compose.animation.core.*
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
fun AutomationScreen(viewModel: AutomationViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val logs by viewModel.logLines.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("自动化控制") },
                actions = {
                    IconButton(onClick = { viewModel.clearLogs() }) {
                        Icon(Icons.Default.Delete, contentDescription = "清空日志")
                    }
                }
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
            Text("运行日志", style = MaterialTheme.typography.titleMedium)
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
git commit -m "refactor: redesign AutomationScreen with M3 cards, FilterChips, and typography"
```

---

### Task 9: Redesign TaskScreen

**Files:**
- Modify: `saltfish/src/main/java/com/saltfish/assistant/ui/task/TaskScreen.kt`

- [ ] **Step 1: Rewrite TaskScreen with M3**

Replace entire file:

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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.saltfish.assistant.domain.model.TaskEntity
import com.saltfish.assistant.domain.model.TaskStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(viewModel: TaskViewModel = viewModel()) {
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

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("任务队列") })
        }
    ) { innerPadding ->
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
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
    TaskStatus.PENDING -> StatusOrange
    TaskStatus.RUNNING -> StatusBlue
    TaskStatus.COMPLETED -> StatusGreen
    TaskStatus.FAILED -> StatusRed
    TaskStatus.CANCELLED -> StatusGray
}

private val StatusOrange = androidx.compose.ui.graphics.Color(0xFFFFA000)
private val StatusBlue = androidx.compose.ui.graphics.Color(0xFF2196F3)
private val StatusGreen = androidx.compose.ui.graphics.Color(0xFF4CAF50)
private val StatusRed = androidx.compose.ui.graphics.Color(0xFFF44336)
private val StatusGray = androidx.compose.ui.graphics.Color(0xFF9E9E9E)
```

- [ ] **Step 2: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/ui/task/TaskScreen.kt
git commit -m "refactor: redesign TaskScreen with M3 TabRow, ElevatedCards, and empty state"
```

---

### Task 10: Redesign UserCenterScreen

**Files:**
- Modify: `saltfish/src/main/java/com/saltfish/assistant/ui/user/UserCenterScreen.kt`

- [ ] **Step 1: Rewrite UserCenterScreen with M3**

Replace entire file:

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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.saltfish.assistant.SaltfishApp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserCenterScreen(
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

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("用户中心") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
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
git commit -m "refactor: redesign UserCenterScreen with M3 cards, avatar, and section headers"
```

---

### Task 11: Update LoginScreen

**Files:**
- Modify: `saltfish/src/main/java/com/saltfish/assistant/ui/login/LoginScreen.kt`

- [ ] **Step 1: Migrate LoginScreen to M3**

Replace entire file:

```kotlin
package com.saltfish.assistant.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    viewModel: LoginViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val focusManager = LocalFocusManager.current
    var passwordVisible by remember { mutableStateOf(false) }

    LaunchedEffect(uiState.loginSuccess) {
        if (uiState.loginSuccess) onLoginSuccess()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            // Logo / App name
            Text(
                "咸鱼助手",
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "登录您的账号",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Username
            OutlinedTextField(
                value = uiState.username,
                onValueChange = viewModel::updateUsername,
                label = { Text("用户名") },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password
            OutlinedTextField(
                value = uiState.password,
                onValueChange = viewModel::updatePassword,
                label = { Text("密码") },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                visualTransformation = if (passwordVisible) VisualTransformation.None
                    else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
            )

            // Captcha (conditional)
            if (uiState.showCaptcha) {
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = uiState.captcha,
                    onValueChange = viewModel::updateCaptcha,
                    label = { Text("验证码") },
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done)
                )
                Spacer(modifier = Modifier.height(8.dp))
                uiState.captchaBase64?.let {
                    Text(
                        "验证码图片(点此刷新)",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Remember account
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = uiState.rememberAccount,
                    onCheckedChange = viewModel::setRememberAccount
                )
                Text(
                    "记住登录信息",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Error message
            uiState.errorMessage?.let { error ->
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    error,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Login button
            Button(
                onClick = { viewModel.login() },
                enabled = !uiState.isLoading,
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(25.dp)
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                        strokeWidth = 2.dp
                    )
                } else {
                    Text("登  录", style = MaterialTheme.typography.titleMedium)
                }
            }

            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}
```

- [ ] **Step 2: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/ui/login/LoginScreen.kt
git commit -m "refactor: migrate LoginScreen to M3 components and theme"
```

---

### Task 12: Update SettingsScreen

**Files:**
- Modify: `saltfish/src/main/java/com/saltfish/assistant/ui/settings/SettingsScreen.kt`

- [ ] **Step 1: Migrate SettingsScreen to M3**

Replace entire file:

```kotlin
package com.saltfish.assistant.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.saltfish.assistant.SaltfishApp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    val context = LocalContext.current
    val app = context.applicationContext as SaltfishApp
    var autoUpgrade by remember { mutableStateOf(app.preferencesManager.autoUpgrade) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("设置") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Update settings
            Text("更新设置", style = MaterialTheme.typography.titleMedium)
            ElevatedCard(shape = MaterialTheme.shapes.medium) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("自动更新", style = MaterialTheme.typography.bodyLarge)
                    Switch(
                        checked = autoUpgrade,
                        onCheckedChange = {
                            autoUpgrade = it
                            app.preferencesManager.autoUpgrade = it
                        }
                    )
                }
            }

            HorizontalDivider()

            // Cache
            Text("缓存管理", style = MaterialTheme.typography.titleMedium)
            OutlinedButton(
                onClick = {
                    context.cacheDir.deleteRecursively()
                    context.cacheDir.mkdirs()
                },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.small
            ) {
                Text("清除缓存")
            }

            HorizontalDivider()

            // About
            Text("关于", style = MaterialTheme.typography.titleMedium)
            ElevatedCard(shape = MaterialTheme.shapes.medium) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("咸鱼助手 v1.0.0", style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "基于 AutoX 引擎",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}
```

- [ ] **Step 2: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/ui/settings/SettingsScreen.kt
git commit -m "refactor: migrate SettingsScreen to M3 components"
```

---

### Task 13: Update PermissionsGuideScreen

**Files:**
- Modify: `saltfish/src/main/java/com/saltfish/assistant/ui/permissions/PermissionsGuideScreen.kt`

- [ ] **Step 1: Migrate PermissionsGuideScreen to M3**

Replace entire file:

```kotlin
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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
    var currentStep by remember { mutableStateOf(0) }
    var grantedStates by remember { mutableStateOf(List(permissions.size) { false }) }

    LaunchedEffect(currentStep) {
        grantedStates = permissions.map { it.checkGranted(context) }
    }

    val refreshTrigger = remember { mutableStateOf(0) }
    LaunchedEffect(refreshTrigger.value) {
        delay(500)
        grantedStates = permissions.map { it.checkGranted(context) }
    }

    val step = permissions.getOrNull(currentStep) ?: return

    Scaffold(
        topBar = {
            if (currentStep < permissions.size) {
                CenterAlignedTopAppBar(
                    title = {
                        Text("权限引导 (${currentStep + 1}/${permissions.size})")
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
            // Progress indicator
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                permissions.indices.forEach { index ->
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

            // Icon
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

            // Title
            Text(
                step.title,
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Description
            Text(
                step.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Status
            val isGranted = grantedStates.getOrElse(currentStep) { false }
            StatusBadge(isGranted = isGranted, isRequired = step.isRequired)

            Spacer(modifier = Modifier.height(32.dp))

            // Action button
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

            // Bottom buttons
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

                if (currentStep < permissions.size - 1) {
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
                    val allDone = permissions.indices.all { i ->
                        !permissions[i].isRequired || grantedStates[i]
                    }
                    Button(
                        onClick = {
                            val app = context.applicationContext as com.saltfish.assistant.SaltfishApp
                            app.preferencesManager.isFirstLaunch = false
                            onComplete()
                        },
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

private val StatusGreen = androidx.compose.ui.graphics.Color(0xFF4CAF50)
private val StatusRed = androidx.compose.ui.graphics.Color(0xFFF44336)
private val StatusOrange = androidx.compose.ui.graphics.Color(0xFFFFA000)

private val permissions = listOf(
    PermissionItem(
        key = "accessibility",
        title = "无障碍服务",
        description = "咸鱼助手需要无障碍服务来模拟屏幕操作，包括点击、滑动、输入文字等，这是自动化任务的核心功能。",
        icon = Icons.Default.Build,
        isRequired = true,
        settingAction = { ctx ->
            ctx.startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            })
        },
        checkGranted = { ctx ->
            val service = "${ctx.packageName}/com.stardust.autojs.core.accessibility.AccessibilityService"
            val enabled = Settings.Secure.getString(
                ctx.contentResolver,
                Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
            ) ?: ""
            enabled.contains(service)
        }
    ),
    PermissionItem(
        key = "overlay",
        title = "悬浮窗权限",
        description = "悬浮窗用于显示脚本运行日志、任务进度等浮动窗口，方便您实时查看自动化状态。",
        icon = Icons.Default.ExitToApp,
        isRequired = true,
        settingAction = { ctx ->
            ctx.startActivity(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                data = android.net.Uri.parse("package:${ctx.packageName}")
            })
        },
        checkGranted = { ctx ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Settings.canDrawOverlays(ctx)
            } else true
        }
    ),
    PermissionItem(
        key = "notifications",
        title = "通知权限",
        description = "通知权限用于显示前台服务通知（常驻通知栏）和任务执行结果通知，确保服务稳定运行。",
        icon = Icons.Default.Notifications,
        isRequired = true,
        settingAction = { ctx ->
            ctx.startActivity(Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                putExtra(Settings.EXTRA_APP_PACKAGE, ctx.packageName)
            })
        },
        checkGranted = { ctx ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val nm = ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                nm.areNotificationsEnabled()
            } else true
        }
    ),
    PermissionItem(
        key = "battery",
        title = "电池优化白名单",
        description = "关闭电池优化可以防止系统在后台自动终止脚本服务，确保长时间运行的自动化任务不被中断。",
        icon = Icons.Default.Warning,
        isRequired = true,
        settingAction = { ctx ->
            ctx.startActivity(Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                data = android.net.Uri.parse("package:${ctx.packageName}")
            })
        },
        checkGranted = { ctx ->
            val pm = ctx.getSystemService(Context.POWER_SERVICE) as PowerManager
            pm.isIgnoringBatteryOptimizations(ctx.packageName)
        }
    ),
    PermissionItem(
        key = "screen_capture",
        title = "屏幕截图",
        description = "屏幕截图权限用于在自动化任务中进行图像识别和界面分析，帮助脚本定位界面元素（使用时可动态申请）。",
        icon = Icons.Default.Add,
        isRequired = false,
        settingAction = { _ -> },
        checkGranted = { true }
    )
)
```

- [ ] **Step 2: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/ui/permissions/PermissionsGuideScreen.kt
git commit -m "refactor: migrate PermissionsGuideScreen to M3 theme and components"
```

---

### Task 14: Update SplashScreen

**Files:**
- Modify: `saltfish/src/main/java/com/saltfish/assistant/ui/splash/SplashScreen.kt`

- [ ] **Step 1: Migrate SplashScreen to M3 theme colors**

SplashScreen uses minimal imports (`material.Icon`, `material.Text`) so the main change is replacing `Yellow500`/`Yellow700` references with `MaterialTheme.colorScheme` values.

Replace the import blocks and color references:

In the import section, replace:
```kotlin
import com.saltfish.assistant.ui.theme.Yellow500
import com.saltfish.assistant.ui.theme.Yellow700
```

And in the code:

Replace `Yellow500.copy(alpha = 0.3f)` with `MaterialTheme.colorScheme.primaryContainer` on line 104 (background gradient).
Replace `Yellow500.copy(alpha = 0.15f)` with `MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)` on line 106.
Replace `Yellow500.copy(alpha = 0.2f)` with `MaterialTheme.colorScheme.primaryContainer` on line 157.
Replace `Yellow700` with `MaterialTheme.colorScheme.onPrimaryContainer` on line 166 (Icon tint).

Also add `import androidx.compose.material3.MaterialTheme` to the import block.

Full replacement of the Box with gradient fallback (lines 98-111):

```kotlin
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    MaterialTheme.colorScheme.primaryContainer,
                                    MaterialTheme.colorScheme.background,
                                    MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
                                )
                            )
                        )
                )
```

Logo icon tint change (line 166):
```kotlin
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
```

- [ ] **Step 2: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/ui/splash/SplashScreen.kt
git commit -m "refactor: replace hardcoded Yellow colors with M3 theme in SplashScreen"
```

---

### Task 15: Update NavGraph and Navigation

**Files:**
- Modify: `saltfish/src/main/java/com/saltfish/assistant/ui/navigation/NavGraph.kt`

- [ ] **Step 1: Update NavGraph for new navigation structure**

The key change: HomeScreen now contains its own BottomBar, so the NavGraph's structure shifts — HomeScreen stays as the main scaffold with tabs, and the other screens are navigated via the BottomBar within HomeScreen. The Login/Permissions/Settings/UserCenter/Log screens remain as independent routes.

Add the Log screen route, update Home composable:

```kotlin
package com.saltfish.assistant.ui.navigation

import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.saltfish.assistant.SaltfishApp
import com.saltfish.assistant.ui.automation.AutomationScreen
import com.saltfish.assistant.ui.home.HomeScreen
import com.saltfish.assistant.ui.login.LoginScreen
import com.saltfish.assistant.ui.permissions.PermissionsGuideScreen
import com.saltfish.assistant.ui.settings.SettingsScreen
import com.saltfish.assistant.ui.task.TaskScreen
import com.saltfish.assistant.ui.user.UserCenterScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Task : Screen("task")
    object Automation : Screen("automation")
    object Settings : Screen("settings")
    object Login : Screen("login")
    object UserCenter : Screen("user_center")
    object PermissionsGuide : Screen("permissions_guide")
    object Log : Screen("log")
}

@Composable
fun SaltfishNavGraph(
    splashLoggedIn: Boolean? = null,
    onSplashOverrideConsumed: () -> Unit = {}
) {
    val navController = rememberNavController()
    val context = androidx.compose.ui.platform.LocalContext.current
    val app = context.applicationContext as SaltfishApp

    LaunchedEffect(splashLoggedIn) {
        if (splashLoggedIn != null) onSplashOverrideConsumed()
    }

    val startRoute = when {
        splashLoggedIn == true -> {
            if (app.preferencesManager.isFirstLaunch) Screen.PermissionsGuide.route
            else Screen.Home.route
        }
        splashLoggedIn == false -> Screen.Login.route
        !app.preferencesManager.isLoggedIn() -> Screen.Login.route
        app.preferencesManager.isFirstLaunch -> Screen.PermissionsGuide.route
        else -> Screen.Home.route
    }

    NavHost(navController = navController, startDestination = startRoute) {
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    val dest = if (app.preferencesManager.isFirstLaunch)
                        Screen.PermissionsGuide.route
                    else
                        Screen.Home.route
                    navController.navigate(dest) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.Task.route) {
            TaskScreen()
        }
        composable(Screen.Automation.route) {
            AutomationScreen()
        }
        composable(Screen.PermissionsGuide.route) {
            PermissionsGuideScreen(
                onComplete = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.PermissionsGuide.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Settings.route) {
            SettingsScreen()
        }
        composable(Screen.UserCenter.route) {
            UserCenterScreen(
                onLogout = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
    }
}
```

- [ ] **Step 2: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/ui/navigation/NavGraph.kt
git commit -m "refactor: update NavGraph with Log route and M3-compatible navigation"
```

---

### Task 16: Final Compilation Verification

- [ ] **Step 1: Build full APK**

Run: `./gradlew :saltfish:assembleCommonDebug`
Expected: BUILD SUCCESSFUL

- [ ] **Step 2: Fix any compilation errors**

Check compiler output for:
- Unresolved M2 imports → replace with M3 equivalents
- `MaterialTheme.colors` → `MaterialTheme.colorScheme` references
- `ExperimentalMaterialApi` → `ExperimentalMaterial3Api` annotations
- Missing imports for new M3 components

- [ ] **Step 3: Commit fixes if needed**

```bash
git add -A
git commit -m "fix: resolve compilation errors from M3 migration"
```

---

### Task 17: Verify MainActivity Theme Wrapper

**Files:**
- Modify: `saltfish/src/main/java/com/saltfish/assistant/MainActivity.kt`

- [ ] **Step 1: Ensure MainActivity wraps content in SaltfishTheme**

Check that `MainActivity.kt` wraps its `setContent` with `SaltfishTheme { }`. If not, add it:

```kotlin
setContent {
    SaltfishTheme {
        // existing content
    }
}
```

- [ ] **Step 2: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/MainActivity.kt
git commit -m "fix: ensure MainActivity uses SaltfishTheme wrapper"
```
