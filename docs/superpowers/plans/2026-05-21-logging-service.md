# Logs 开发调试日志服务 — 实现计划

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** 为 saltfish 模块创建零运行时开销的开发日志工具，debug 构建输出到 logcat，release 构建由编译器剔除。

**Architecture:** 单个 Kotlin `object`，所有方法 `inline`，通过 `BuildConfig.DEBUG` 编译时常量控制分支，消息体用 lambda 包裹避免不必要的字符串拼接。

**Tech Stack:** Kotlin, `android.util.Log`, 无外部依赖

---

### Task 1: 创建 Logs.kt

**Files:**
- Create: `saltfish/src/main/java/com/saltfish/assistant/util/Logs.kt`

- [ ] **Step 1: 创建 util 目录并编写 Logs.kt**

```bash
mkdir -p saltfish/src/main/java/com/saltfish/assistant/util
```

```kotlin
package com.saltfish.assistant.util

import android.util.Log
import com.saltfish.assistant.BuildConfig

object Logs {
    private const val TAG = "Saltfish"

    inline fun verbose(module: String, message: () -> String) {
        if (BuildConfig.DEBUG) {
            Log.v(TAG, "[$module] ${message()}")
        }
    }

    inline fun debug(module: String, message: () -> String) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "[$module] ${message()}")
        }
    }

    inline fun info(module: String, message: () -> String) {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, "[$module] ${message()}")
        }
    }

    inline fun warn(module: String, message: () -> String) {
        if (BuildConfig.DEBUG) {
            Log.w(TAG, "[$module] ${message()}")
        }
    }

    inline fun error(module: String, message: () -> String) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, "[$module] ${message()}")
        }
    }

    inline fun error(module: String, throwable: Throwable, message: () -> String) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, "[$module] ${message()}", throwable)
        }
    }
}
```

- [ ] **Step 2: 验证编译**

```bash
./gradlew :saltfish:compileCommonDebugKotlin
```

Expected: BUILD SUCCESSFUL

- [ ] **Step 3: 验证 release 编译不受影响**

```bash
./gradlew :saltfish:compileCommonReleaseKotlin
```

Expected: BUILD SUCCESSFUL

- [ ] **Step 4: 在 ApiClient 中加一条调用验证实际可用**

在 `saltfish/src/main/java/com/saltfish/assistant/data/remote/ApiClient.kt` 的某个网络请求方法中添加：

```kotlin
import com.saltfish.assistant.util.Logs

// 在请求方法开头添加
Logs.debug("ApiClient") { "请求: GET /device/info" }
```

- [ ] **Step 5: 编译并验证 logcat 输出**

```bash
./gradlew :saltfish:assembleCommonDebug
# 安装 APK 后运行:
adb logcat -s Saltfish:*
```

Expected: 看到 `D/Saltfish: [ApiClient] 请求: GET /device/info`

- [ ] **Step 6: Commit**

```bash
git add saltfish/src/main/java/com/saltfish/assistant/util/Logs.kt saltfish/src/main/java/com/saltfish/assistant/data/remote/ApiClient.kt
git commit -m "feat: add Logs debug logger with BuildConfig.DEBUG gate"
```
