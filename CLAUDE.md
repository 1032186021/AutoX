# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Development

- **Android Studio**: 2023.3.1 Patch 2 or Bumblebee 2021.1.1 (see `project-versions.json`)
- **JDK**: 17
- **Kotlin**: 1.6.21, **Compose**: 1.2.0-rc01 (defined in `buildSrc/src/main/kotlin/Version.kt`)

**Manual builds via Gradle wrapper:**
```bash
# Build debug APK
./gradlew :app:assembleCommonDebug

# Build the template APK (inrt, embedded as asset into main app)
./gradlew :inrt:assembleTemplateRelease

# Build both template + main app
./gradlew :app:assembleCommonRelease

# Run unit tests
./gradlew :autojs:test
./gradlew :app:test
./gradlew :common:test
```

**No CI/CD pipeline** is configured; builds are local.

## Module Architecture

The project is an **Android automation framework** (AutoX) — a fork of Auto.js for scripting Android device interactions with JavaScript.

### Module Dependency Graph (top-down)

```
app (org.autojs.autoxjs)          ← Main APK: UI, settings, project management
├── autojs (com.stardust.autojs)   ← Core: JS runtime bindings, Rhino engine, automation APIs
│   ├── automator (com.stardust.automator)  ← UI Automator wrapper: view lookup, gestures
│   │   └── common (com.stardust)           ← Shared utilities, I/O, concurrency
│   ├── common
│   ├── LocalRepo:libtermexec     ← Terminal emulator
│   ├── LocalRepo:emulatorview    ← Terminal view
│   ├── LocalRepo:term            ← Terminal integration
│   ├── LocalRepo:p7zip           ← Archive extraction (7zip)
│   ├── LocalRepo:OpenCV          ← OpenCV bindings for image matching
│   └── paddleocr                  ← OCR (PaddleOCR + Tesseract + MLKit)
├── apkbuilder                     ← APK packaging library for export
└── inrt (com.stardust.auojs.inrt) ← Standalone runtime APK (template) for exported scripts
    ├── autojs
    └── automator
```

### Key modules

- **`app`** (`org.autojs.autoxjs`): The main application APK. Package `ui/` holds all screens (settings, editor, file explorer, floating windows, logs). Package `external/` handles Tasker plugin, widgets, file providers, and tile services. Package `autojs/api/` defines the scripting API surface exposed to users.

- **`autojs`** (`com.stardust.autojs`): The core engine. Contains the Rhino JavaScript engine (`org.mozilla.javascript`), script runtime with sandboxing, and the `ScriptInterface` annotation used to mark methods callable from JS. Scripts are executed via Rhino with bindings to Android platform APIs.

- **`automator`** (`com.stardust.automator`): Accessibility-based UI automation. Wraps Android's `AccessibilityService` and `UiAutomation` APIs to provide view hierarchy traversal, click, swipe, and gesture injection without root.

- **`common`** (`com.stardust`): Foundational utilities — file I/O, concurrency primitives, event bus, networking, and JSON handling.

- **`inrt`** (`com.stardust.auojs.inrt`): A lightweight standalone APK that serves as the **template** for exporting AutoX scripts. Uses WebSocket-based IPC (`DevPluginService`, `JsonWebSocket`) to communicate between the main app and sandboxed sub-process.

- **`apkbuilder`**: Programmatic APK construction. Contains `pxb.android.axml`/`arsc` for binary Android resource editing and `ApkPackager`/`ManifestEditor` for building the final installable APK from a script project + the inrt template.

- **`buildSrc`**: Defines `Versions` data class read from `project-versions.json`, plus `kotlin_version` and `compose_version` constants.

## Key Conventions

- **Two flavors**: `common` (release channel, version from JSON) and `v6` (dev channel with `.v6` suffix).
- **ProGuard**: Keeps all `com.stardust.autojs.**`, `com.stardust.automator.**`, `org.mozilla.javascript.**`, and methods annotated with `@ScriptInterface` or `@Subscribe`.
- **Architecture**: ABIs `armeabi-v7a` and `arm64-v8a` only; APK splits per ABI with universal APK.
- **Annotation processing**: Uses both AndroidAnnotations and ButterKnife (annotationProcessor + kapt).
- **Module group** for local libraries is `:LocalRepo:` (local Maven-substitute modules not published externally).
- **The `inrt` module builds a template APK** that is copied into `app/src/main/assets/template.apk` during the main app's build. Run `:inrt:assembleTemplateRelease` before building the app the first time.
