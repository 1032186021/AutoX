# Logs — 开发调试日志服务

## Overview

为 saltfish 模块提供一个零运行时开销的开发调试日志工具，通过 `BuildConfig.DEBUG` 控制在 debug 构建时输出到 logcat，release 构建时由编译器完全剔除。

## API

```kotlin
// 基本调用
Logs.verbose(module) { "消息" }
Logs.debug(module) { "消息" }
Logs.info(module) { "消息" }
Logs.warn(module) { "消息" }
Logs.error(module) { "消息" }

// 带异常
Logs.error(module) { error -> "请求失败: ${error.message}" }
```

- `module`: 模块名（字符串），如 `"ApiClient"`、`"TaskScheduler"`
- 消息体使用 lambda 包裹，release 构建时编译器不执行，避免字符串拼接开销
- 所有方法为 `inline`，`BuildConfig.DEBUG` 为编译时常量，release 构建时整个调用被 R8/ProGuard 消除

## TAG 规则

- 固定 TAG: `Saltfish`
- 终端查看: `adb logcat -s Saltfish:*`
- 模块过滤: `adb logcat -s Saltfish:* | grep ApiClient`
- 输出格式: `[ApiClient] 请求参数: {"page":1}`

## Level 映射

| 方法 | Logcat Level |
|------|-------------|
| `verbose` | `Log.VERBOSE` |
| `debug` | `Log.DEBUG` |
| `info` | `Log.INFO` |
| `warn` | `Log.WARN` |
| `error` | `Log.ERROR` |

## 实现要点

- 单文件: `saltfish/src/main/java/com/saltfish/assistant/util/Logs.kt`
- Kotlin `object`，所有方法 `inline`
- 消息格式: `[$module] $message`
- 异常日志: error 级别带异常参数，输出异常 stacktrace
- release 剔除依赖 `BuildConfig.DEBUG` + R8 inline 优化

## 文件变更

- **新建**: `saltfish/src/main/java/com/saltfish/assistant/util/Logs.kt`
