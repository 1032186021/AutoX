# DeviceActivationScreen 页面重构

## Overview

将 `DeviceActivationScreen` 重构为 Material Design 3 标准风格，与项目中 HomeScreen、LoginScreen 保持一致的视觉语言，同时提升交互体验。

## Layout Structure

```
Scaffold
├── TopAppBar (CenterAlignedTopAppBar)
│   ├── Register mode: no back, right refresh button → reset dialog
│   └── Renew mode: back arrow, no right action
├── Scrollable Column
│   ├── Hero Icon (animated, 72dp)
│   ├── Title text
│   ├── Clipboard prompt chip (conditional, AnimatedVisibility)
│   ├── Activation Card
│   │   ├── Card description text
│   │   ├── OutlinedTextField (leading Key icon, trailing clear, clipboard detection on focus)
│   │   ├── Error text row (AnimatedVisibility)
│   │   ├── InfoRow × 3 (App version, Script version, UUID — all copyable)
│   │   └── Device detail section (renew mode only)
│   │       ├── HorizontalDivider
│   │       ├── Section header
│   │       └── Detail Surface
│   └── Submit Button (full-width pill, animated states)
└── SnackbarHost (managed by Scaffold)
```

## Components

### 1. TopAppBar

- Uses `CenterAlignedTopAppBar` with `TopAppBarDefaults.centerAlignedTopAppBarColors`
- `containerColor = MaterialTheme.colorScheme.surface`
- Title: `mode.title` in `titleLarge` typography
- Register mode: no navigation icon, actions slot has `Refresh` icon → `AlertDialog` confirm reset
- Renew mode: `ArrowBack` navigation icon, no actions
- Back navigation blocked via `BackHandler` when `!canBack`

### 2. Hero Icon

A 72dp circular `Surface` with icon inside, serving as visual anchor at top of content area.

States:
- **Default**: `Lock` icon, `onSurfaceVariant` tint, 12% alpha background
- **Loading**: `Lock` icon rotates with `infiniteTransition` (1s per revolution, `LinearEasing`)
- **Success**: `CheckCircle` icon, `primary` tint, scale-up spring animation
- **Error**: `Lock` icon, `error` tint, horizontal shake animation (3 oscillations, 50ms each, 300ms total)

### 3. Clipboard Detection

Triggered when `OutlinedTextField` gains focus.

- Check clipboard content via `ClipboardManager`
- If non-empty text exists, show an `AnimatedVisibility` chip above the text field
- Background: `primaryContainer` at 30% alpha, rounded `MaterialTheme.shapes.small`
- Leading `ContentPaste` icon, text "检测到剪贴板中的卡密", trailing `TextButton("一键填入")`
- On "一键填入" click: set `secret = clipboardText`, hide chip
- Animation: `fadeIn + slideInVertically` / `fadeOut + slideOutVertically`

### 4. Input Field

`OutlinedTextField`:
- `leadingIcon`: `Icons.Default.Key`
- `trailingIcon`: `Icons.Default.Clear` (visible when `secret.isNotEmpty()`), clears input on click
- `shape`: `MaterialTheme.shapes.medium` (16dp)
- `singleLine = true`, `imeAction = Done`
- On `Done`: call `doSubmit()`
- On value change: clear `errorText`
- Disabled during loading
- `Modifier.onFocusChanged`: trigger clipboard check on gain focus

### 5. Error Display

`Row` inside `AnimatedVisibility(visible = errorText.isNotEmpty())`:
- `Error` icon (16dp, `error` color)
- Error text in `bodySmall`, `error` color
- Enter: `fadeIn(300ms)`, Exit: `fadeOut(200ms)`

### 6. InfoRow (refactored)

Replaces current emoji-based `InfoRow` with Material Icons:

```
[Icon 18dp]  Label (80dp, bodyMedium)  Value (weight 1f, bodySmall, end-aligned)  [Copy 14dp]
```

- Icons: `PhoneAndroid` (App version), `Code` (Script version), `Fingerprint` (UUID)
- Copy button: `ContentCopy` icon (14dp), on click → copy to clipboard → icon switches to `Check` (green) for 800ms, then reverts
- No Snackbar dependency — feedback is local to the icon change

### 7. Submit Button

Full-width pill button (`MaterialTheme.shapes.large` = 24dp, height 52dp):

States:
- **Disabled**: `secret.isBlank()`, container color `onSurface.copy(alpha = 0.12f)`, no click
- **Enabled**: `primary` container, `VpnKey` leading icon + label text
- **Loading**: disabled state + `CircularProgressIndicator` (22dp, `onPrimary` color, 2dp stroke) + "提交中..." text
- **Success**: container turns `primary`, icon becomes `Check`, text becomes "激活成功" / "续费成功", wait 600ms then `onDone()`

Transitions via `AnimatedContent` with `fadeIn/fadeOut` (200ms).

### 8. Device Detail (Renew Mode)

Shown only when `isRenew && device != null`.

- `HorizontalDivider` separator
- Section header row: `PhoneAndroid` icon (16dp) + "设备详情" in `labelMedium`
- Inner `Surface` (shape `small` = 12dp, background `surfaceVariant` at 50% alpha) containing:
  - `DeviceDetailRow`: label (bodyMedium) + value (bodySmall, end-aligned)
  - Remaining days displayed as a colored badge:
    - `> 7 days`: StatusGreen badge
    - `3-7 days`: StatusOrange badge
    - `≤ 0 days`: StatusRed badge with "已过期" text

### 9. Reset Dialog

`AlertDialog`:
- Title: "重置设备"
- Text: "将清除本地设备数据，需要重新激活。确定继续？"
- Confirm: "确定" (error-colored)
- Dismiss: "取消"
- On confirm: clear device ID in preferences, navigate to login

## Data Flow

```
DeviceActivationScreen
├── Input: ActivationMode (Register | Renew)
├── State: secret, errorText, isLoading, submitted, showResetDialog, clipboardText, showClipboardChip
├── Actions:
│   ├── doSubmit() → DeviceManager.register/renew → success/failure → animate → onDone()
│   └── resetDevice() → clear prefs → navigate to login
└── Output: onDone() callback (navigates to post-auth destination)
```

## Error Handling

- Network error: "网络连接失败，请检查网络后重试"
- Invalid secret: "激活失败，请检查卡密是否正确"
- Renew failure: "续费失败，请检查卡密是否正确"
- Keyboard dismiss on submit

## Animation Summary

| Trigger | Element | Animation |
|---------|---------|-----------|
| Input focus + clipboard has text | Clipboard chip | slideInVertically + fadeIn (300ms) |
| Error appears | Error row | fadeIn (300ms) |
| Error disappears | Error row | fadeOut (200ms) |
| Submit loading | Button content | fadeIn/fadeOut crossfade (200ms) |
| Submit loading | Hero icon | infinite rotation (1s period) |
| Submit success | Hero icon | scale-up spring + icon swap to CheckCircle |
| Submit error | Hero icon | horizontal shake (3×50ms, 300ms total) |
| Submit success | Button | container color → green, icon → Check (600ms delay then navigate) |
| Copy pressed | Copy icon | swap to Check (green, 800ms then revert) |
| Chip dismissed | Clipboard chip | slideOutVertically + fadeOut (200ms) |

## File Changes

- **Rewrite**: `saltfish/src/main/java/com/saltfish/assistant/ui/activation/DeviceActivationScreen.kt`
- **No changes** to: NavGraph.kt (interface unchanged), DeviceManager.kt, Theme files
