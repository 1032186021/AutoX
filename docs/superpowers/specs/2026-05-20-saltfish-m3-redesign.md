# Saltfish M3 UI Redesign

## Overview

将咸鱼助手 Saltfish 模块从 Material 2 迁移到 Material Design 3，并进行完整的视觉重设计。

**Primary Color**: `#6750A4`（M3 默认紫）
**Migration Strategy**: 一次性全量迁移（方案 A）
**Home Layout Style**: 混合布局（方案 C）

---

## Dependencies

### 变更

| Item | Before | After |
|------|--------|-------|
| Material library | `androidx.compose.material:material:1.2.0-rc01` | `androidx.compose.material3:material3:1.0.1` |
| Accompanist Insets | `accompanist-insets` + `accompanist-insets-ui` | 移除（M3 Scaffold 内置 insets 处理） |

Kotlin 1.6.21 和 Compose Compiler 1.2.0 保持不变。material3 1.0.1 兼容 Compose 1.2.x。

### 修改文件

- `gradle/libs.versions.toml` — 添加 `compose-material3` library entry
- `saltfish/build.gradle.kts` — 替换依赖

---

## Theme Architecture

### File Structure

```
saltfish/src/main/java/com/saltfish/assistant/ui/theme/
├── Color.kt      ← 重写：M3 色调调色板
├── Theme.kt      ← 重写：ColorScheme + Typography + Shapes + SaltfishTheme
└── Type.kt       ← 新增：M3 排版系统
```

### Color Scheme

基于 Primary `#6750A4` 的完整 M3 色调调色板：

**Light Theme**:
- Primary: `#6750A4`, onPrimary: `#FFFFFF`
- PrimaryContainer: `#EADDFF`, onPrimaryContainer: `#1D192B`
- Secondary: `#625B71`, SecondaryContainer: `#E8DEF8`
- Tertiary: `#7D5260`, TertiaryContainer: `#FFD8E4`
- Background: `#FFFBFE`, Surface: `#F7F2FA`
- SurfaceVariant: `#E7E0EC`, onSurfaceVariant: `#49454F`
- Error: `#B3261E`
- Outline: `#79747E`

**Dark Theme**:
- Primary: `#D0BCFF`, onPrimary: `#381E72`
- PrimaryContainer: `#4F378B`, onPrimaryContainer: `#EADDFF`
- Secondary: `#CCC2DC`, SecondaryContainer: `#4A4458`
- Tertiary: `#EFB8C8`, TertiaryContainer: `#633B48`
- Background: `#1C1B1F`, Surface: `#2B2830`
- SurfaceVariant: `#49454F`, onSurfaceVariant: `#CAC4D0`
- Error: `#F2B8B5`
- Outline: `#938F99`

### Typography (Type.kt)

```kotlin
@Composable
fun SaltfishTypography() = Typography(
    displayMedium,   // 首页 Hero 标题
    headlineSmall,   // 页面主标题
    titleMedium,     // 卡片标题 / Section 标题
    titleSmall,      // 列表项标题
    bodyLarge,       // 描述文本
    bodyMedium,      // 正文
    labelMedium,     // Chips / 按钮
    labelSmall,      // Badge / 标注
)
```

### Shapes

```kotlin
Shapes(
    extraSmall = 8.dp,   // Chips, Badge
    small = 12.dp,       // Surface, Button
    medium = 16.dp,      // Card, Dialog
    large = 24.dp,       // FAB, BottomSheet
    extraLarge = 28.dp,  // Large containers
)
```

---

## Global Framework

### Structure

```
┌──────────────────────────────┐
│  状态栏（透明，边缘到边缘）    │
├──────────────────────────────┤
│  🐟 Logo  咸鱼助手    📋日志  │  ← 自定义 TopBar
│            智能自动化平台      │      (CenterAlignedTopAppBar 变体)
├──────────────────────────────┤
│                              │
│     当前页面内容              │
│     (Scaffold content area)  │
│                              │
├──────────────────────────────┤
│  🏠首页 ⚡自动化 📋任务 👤我的│  ← M3 NavigationBar (4 tabs)
└──────────────────────────────┘
```

### TopBar 规格
- 左侧: App Logo (28dp 圆角) + 标题行（主标题 16sp Medium + 副标题 11sp）
- 右侧: 日志按钮 (IconButton, icon: Description/ListAlt)
- 背景: 透明 / surface 色，无 elevation

### NavigationBar 规格
- 4 个 tab: 首页、自动化、任务队列、用户中心
- 使用 M3 `NavigationBar` + `NavigationBarItem`
- 选中态: Primary 色 icon + label
- 非选中态: onSurfaceVariant
- 背景: Surface 色，带顶部 tint 分割线

---

## Pages

### 1. 首页 (HomeScreen)

**布局**（混合布局 C）:

1. **Hero 状态卡片** — `ElevatedCard` 或 `FilledTonalCard`
   - 三列布局: 服务器连接 / 任务状态 / 适配器数量
   - 底部进度条指示器
   - 使用 PrimaryContainer 背景

2. **权限快捷管理** — 分组 `ListItem`
   - SectionHeader: "系统权限" (labelMedium, uppercase tracking)
   - 每项: icon + 名称 + 右侧状态 (Switch 或 "去开启 →" 箭头)
   - `SurfaceVariant` 背景的圆角容器

3. **底部 CTA** — `Button` (filled)
   - 全宽圆角按钮 "连接服务器"
   - 链接态: "断开连接" (outlined 风格)

4. **设备信息** (折叠) — 可选展开区域，避免信息过载
   - 可折叠 Section，默认收起
   - 使用 AnimatedVisibility

### 2. 自动化 (AutomationScreen)

1. **状态显示区**
   - 运行中: 动画 Pulse 指示器 + 当前平台名
   - 空闲: 占位插画 / 图标 + "未运行" 文字

2. **平台选择**
   - `FilterChip` 横向排列: 闲鱼、拼多多、转转、微博、多多团长、小红书
   - 支持多选

3. **操作按钮**
   - 开始: `Button` filled 全宽
   - 停止: `OutlinedButton` 全宽（运行中显示）

4. **最近日志** (mini log list)
   - 最近 5 条执行记录
   - 时间 + 任务名 + 结果 Chip

### 3. 任务队列 (TaskScreen)

1. **分段筛选** — `TabRow` (secondary/scrollable)
   - 待执行 / 已完成 / 失败

2. **任务列表**
   - 每项: 平台 icon + 任务类型 + 时间 + 状态 Chip
   - `SwipeToDismiss` 滑动删除
   - `Card` 作为列表项容器

3. **空状态**
   - 居中插画 + "暂无任务" 文字
   - 使用 SurfaceVariant 背景

### 4. 用户中心 (UserCenterScreen)

1. **顶部个人信息**
   - 大头像 (72dp) + 用户名 + 角色标签 Chip
   - 居中布局

2. **分组列表**
   - Section: 账号信息 (用户名、设备ID)
   - Section: 应用设置 (自动更新 Switch、清除缓存)
   - Section: 关于 (版本号)

3. **退出登录**
   - `TextButton` 红色，居中
   - 带确认 Dialog

### 5. 日志 (LogScreen)

- 通过 TopBar 右上角按钮打开
- `ModalBottomSheet` 或全屏 Screen
- 实时日志流: 时间戳 + 级别 icon + 消息
- TopBar: 搜索/过滤 Chip + 清空按钮
- 自动滚动: `LazyColumn` + `animateScrollToItem`

---

## Component Mapping (M2 → M3)

| M2 | M3 | Notes |
|----|----|-------|
| `material.MaterialTheme` | `material3.MaterialTheme` | Import change |
| `lightColors()` / `darkColors()` | `lightColorScheme()` / `darkColorScheme()` | M3 has ~30 color slots |
| `material.Scaffold` | `material3.Scaffold` | No drawer; padding → innerPadding |
| `material.TopAppBar` | `material3.CenterAlignedTopAppBar` | Custom for logo+subtitle |
| `BottomNavigation` | `NavigationBar` | M3 dedicated component |
| `BottomNavigationItem` | `NavigationBarItem` | API differs: selectedIcon, indicator |
| `Card` | `ElevatedCard` or `Card` | Default elevated; FilledTonalCard for containers |
| `material.Button` | `material3.Button` | Filled by default |
| `OutlinedButton` | `OutlinedButton` | Similar API |
| `Switch` | `Switch` | Similar API |
| `Divider` | `HorizontalDivider` | Named change |
| `Surface` | `Surface` | Similar API |
| `IconButton` | `IconButton` | Similar API |
| `Text` | `Text` | Access typography via `MaterialTheme.typography` |
| `MaterialTheme.colors` | `MaterialTheme.colorScheme` | Property name change |
| `MaterialTheme.shapes` | `MaterialTheme.shapes` | Property name same, scale different |

---

## File Change List

```
saltfish/build.gradle.kts                          ← 替换 M2 → M3 依赖
gradle/libs.versions.toml                           ← 添加 compose-material3

saltfish/src/main/java/com/saltfish/assistant/ui/
├── theme/
│   ├── Color.kt                                    ← 重写（M3 调色板）
│   ├── Theme.kt                                    ← 重写（ColorScheme + Typography + Shapes）
│   └── Type.kt                                     ← 新增（排版系统）
├── components/
│   ├── StatusCard.kt                               ← M2 → M3 组件替换
│   └── PermissionItem.kt                           ← M2 → M3 组件替换
├── home/
│   └── HomeScreen.kt                               ← 混合布局重设计
├── automation/
│   └── AutomationScreen.kt                         ← 重设计
├── task/
│   └── TaskScreen.kt                               ← 重设计
├── user/
│   └── UserCenterScreen.kt                         ← 重设计
├── login/
│   └── LoginScreen.kt                              ← M3 主题适配
├── settings/
│   └── SettingsScreen.kt                           ← M3 主题适配
├── permissions/
│   └── PermissionsGuideScreen.kt                   ← M3 主题适配
├── splash/
│   └── SplashScreen.kt                             ← M3 主题适配
└── navigation/
    └── NavGraph.kt                                 ← 更新路由 + 日志页
```

---

## Edge Cases & States

- **Loading**: 首页设备信息加载中使用 shimmer/placeholder
- **Empty**: 任务队列空状态、适配器列表空状态
- **Error**: 服务器连接失败，Card 变红 Chip + "连接失败" 提示
- **Dark mode**: 全部页面支持 Dark/Light 双主题
- **Edge-to-edge**: 适配 Android 10+ 手势导航，底部 NavigationBar 留出 systemBar 安全区
