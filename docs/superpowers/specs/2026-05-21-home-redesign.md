# Home Screen Redesign: MainScaffold + Glassmorphism

## Overview

Refactor the home screen architecture to unify navigation chrome (TopAppBar + NavigationBar) into a shared `MainScaffold`, and upgrade the visual design from default Material 3 to a glassmorphism-infused style that retains the existing purple color scheme.

## Motivation

- **Architecture**: Each screen currently owns its own `Scaffold`, causing the bottom `NavigationBar` to disappear when switching tabs. Moving to a shared outer `Scaffold` fixes this.
- **Aesthetics**: The current UI looks like a default Material 3 template — no distinctive visual identity. Adding glassmorphism (backdrop blur, semi-transparent cards, gradient accents) creates depth and character.

## Architecture Change

### Before
```
NavHost
├── HomeScreen → Scaffold (TopBar + BottomBar + Content)
├── AutomationScreen → Scaffold (TopBar only)
├── TaskScreen → Scaffold (TopBar only)
└── UserCenterScreen → Scaffold (TopBar only)
```

### After
```
NavHost
├── LoginScreen (full-screen, no chrome)
├── GuideScreen (full-screen, no chrome)
├── PermissionsGuideScreen (full-screen, no chrome)
├── SplashScreen (full-screen, no chrome)
└── MainScaffold (shared chrome)
    ├── HomeScreen (content only)
    ├── AutomationScreen (content only + own TopBar)
    ├── TaskScreen (content only + own TopBar)
    └── UserCenterScreen (content only + own TopBar)
```

### Key decisions

- **New file**: `MainScaffold.kt` — contains the shared `Scaffold` with `NavigationBar` and a slot for per-screen `TopAppBar`.
- **TopAppBar per screen**: Each screen composable returns its own `TopAppBar` composable so screens with different actions (log, clear, settings) stay independent.
- **Non-main routes**: Login, Guide, Splash, PermissionsGuide, DeviceActivation are rendered outside `MainScaffold` — no bottom bar for these flows.
- **NavGraph restructure**: Split into `AuthGraph` (login/guide/permissions) and `MainGraph` (home/automation/task/user). `MainScaffold` wraps `MainGraph`.

## Visual Design

### Principles
- Keep existing purple primary (`#6750A4`) and color tokens from `Theme.kt`
- Keep existing shape system (`8dp`–`28dp` rounded corners)
- Add glassmorphism layers for depth
- Use subtle gradient accents (text, progress bars)
- Add background atmosphere (radial glow)

### Changes to Theme
- **No changes** to `Color.kt` or `Type.kt` — existing tokens are sufficient.
- Add a `BackgroundGlow` composable (decorative, not in theme).
- Add `CardDefaults.glassCardColors()` extension or local modifier pattern.

### Card style
- Background: `Color.White.copy(alpha = 0.7f)` or `surface.copy(alpha = 0.6f)`
- Border: `primary.copy(alpha = 0.08f)`
- Shadow: `elevation = 2.dp` with softer ambient
- Shape: `MaterialTheme.shapes.large` (24dp)

### TopAppBar
- Transparent/glass background with bottom border
- Logo icon gets a subtle gradient background and shadow
- Subtitle uses `primary` variant color instead of gray

### Bottom NavigationBar
- Glass background
- Selected item: use primary color with a small indicator dot or pill
- Subtle top border

### Button CTA
- Gradient background (`primary` to lighter purple `#9A82DB`)
- Stronger shadow with primary tint
- Rounded shape (12dp)

### Status badges (permission rows)
- Replace plain "去开启" text with small filled chips
- Granted: green-tinted surface + check
- Denied: red-tinted surface + arrow

## Files to Change

| File | Action | Notes |
|------|--------|-------|
| `ui/navigation/NavGraph.kt` | **Refactor** | Split routes: `MainScaffold` wraps main tabs, standalone screens outside |
| `ui/home/HomeScreen.kt` | **Refactor** | Remove `Scaffold`, `SaltfishTopBar`, `SaltfishBottomBar`; render content only |
| `ui/automation/AutomationScreen.kt` | **Modify** | Remove outer `Scaffold`, keep `TopAppBar` content |
| `ui/task/TaskScreen.kt` | **Modify** | Remove outer `Scaffold`, keep `TopAppBar` content |
| `ui/user/UserCenterScreen.kt` | **Modify** | Remove outer `Scaffold`, keep `TopAppBar` content |
| `ui/home/MainScaffold.kt` | **New** | Shared `Scaffold` with `NavigationBar`, slot for per-screen top bar |
| `ui/home/HomeViewModel.kt` | **No change** | — |
| `ui/theme/Theme.kt` | **No change** | Existing tokens sufficient |

## Non-Goals

- Not changing the login/guide/splash flows
- Not adding animation transitions between tabs (can be done later)
- Not touching `Color.kt` or `Type.kt`
- Not changing the data layer or ViewModels

## Risks

- **Backdrop blur on Android**: `Modifier.blur()` is available from API 31+. On older devices, fall back to a more opaque background. Since AutoX targets API 24+ but this is a cosmetic effect, graceful degradation is acceptable.
- **Recomposition**: Moving `Scaffold` up one level is a net reduction in Scaffold instances (4→1), so recomposition should improve, not regress.
