# Saltfish Native Android App Design

## Overview

将 `client-autojs`（咸鱼助手）从 Auto.js TypeScript 脚本重构为基于 AutoX 的原生 Android 应用。

**核心设计原则**：Adapter 保持脚本形式运行在 Rhino 引擎上，Application（远控框架）用原生 Kotlin 实现。

## 模块结构

```
AutoX/
├── :saltfish                    ← 新建，咸鱼助手 APK (com.saltfish.assistant)
│   └── 依赖 :autojs, :automator, :common
├── :autojs                      ← 复用，Rhino 引擎 + 自动化 API
├── :automator                   ← 复用，无障碍服务 + 手势注入
├── :common                      ← 复用，基础工具
├── :app                         ← 现有 AutoX 主 App（不动）
└── :inrt                        ← 现有模板 APK（不动）
```

- `:saltfish` 是独立 Android application 模块，不依赖 `:app`
- 打包：`./gradlew :saltfish:assembleRelease`
- 复用 AutoX 的 `autojs`（脚本引擎）、`automator`（无障碍）、`common`（工具）模块

## 技术栈

- **语言**：Kotlin
- **UI**：Jetpack Compose + Material 3
- **架构**：MVVM + Repository
- **网络**：Retrofit (HTTP) + Socket.IO (`io.socket:socket.io-client`)
- **持久化**：Room + SharedPreferences
- **通信**：原生层通过 Rhino `ScriptEngine` 直接调用 Adapter 脚本

## saltfish 模块内部结构

```
saltfish/src/main/java/com/saltfish/
├── SaltfishApp.kt               # Application 入口，初始化 DI、CrashHandler
├── MainActivity.kt              # 唯一 Activity，Compose 宿主
├── service/
│   └── ScriptService.kt         # 前台服务，持有 ScriptEngine，跑 Adapter 脚本
├── engine/
│   └── ScriptBridge.kt          # 桥接层，原生 ↔ Rhino 双向调用
├── data/
│   ├── local/                   # Room DB + SharedPrefs
│   ├── remote/                  # Retrofit API + Socket.IO
│   └── repository/              # Repository 层
├── domain/
│   ├── model/                   # 领域模型
│   └── usecase/                 # 用例
├── ui/
│   ├── theme/                   # Material 3 主题
│   ├── home/                    # 首页（设备状态、连接状态、权限状态）
│   ├── task/                    # 任务面板（任务队列、执行日志）
│   ├── automation/              # 自动化控制面板
│   ├── settings/                # 设置
│   └── components/              # 共享 UI 组件
└── util/                        # 工具类
```

### 关键设计点

- `ScriptService`：常驻前台服务，持有 Rhino `ScriptEngine`，保证无障碍服务和脚本引擎生命周期独立于 Activity
- `ScriptBridge`：原生调脚本的唯一入口，封装 Rhino 调用细节，脚本通过注册的全局函数/变量与原生通信
- `ScriptManager`：管理 Adapter 脚本的加载、版本切换、云端更新

## ScriptBridge 桥接层

### 原生 → 脚本

原生层通过 `ScriptEngine` scope 注入 `scriptBridge` 对象，设置全局变量供脚本读取：

```kotlin
interface ScriptBridge {
    fun setGlobal(name: String, value: Any)
    fun getGlobal(name: String): Any
    fun executeTask(platform: String, taskType: String, params: JSONObject): JSONObject
}
```

### 脚本 → 原生

脚本侧调用 `scriptBridge` 对象的方法汇报状态：

```js
scriptBridge.onTaskProgress("正在搜索商品...");
scriptBridge.onTaskComplete({ success: true, data: { productId: 123 } });
scriptBridge.onTaskError("操作失败");
```

### 通信流程

```
原生层 executeTask() → scope.put("scriptBridge", bridgeImpl, scope)
    → engine.evaluateString(scope, adapterScript, "adapter.js")
    → 脚本执行业务逻辑
    → 脚本调用 scriptBridge.onXxx()
    → Bridge 回调 ViewModel / Repository
```

## 数据流

```
                    服务器 (midway-aios)
                    │ HTTP API    │ Socket.IO
                    ▼             ▼
              Repository 层 (data/repository/)
              DeviceRepo  AccountRepo  TaskRepo  ConfigRepo
                    │
                    ▼
              ViewModel 层 (ui/*/)
              HomeVM  TaskVM  AutomationVM  SettingsVM
                    │ StateFlow<UiState>
                    ▼
              Compose UI
                    │ 用户操作 / 服务器指令
                    ▼
              ScriptService
              接收任务 → ScriptBridge.executeTask() → Rhino 执行
              脚本回调 → ScriptBridge.onXxx() → 更新 ViewModel
```

- ViewModel 通过 `StateFlow` 驱动 UI 刷新
- 服务器下发任务：Socket.IO → Repository → TaskQueue → ScriptService → ScriptBridge → Adapter 脚本
- Adapter 脚本执行结果：ScriptBridge 回调 → ViewModel → UI / 上报服务器

## 脚本管理 (ScriptManager)

- 启动时从 `assets/adapters/` 解压默认 Adapter 到 `filesDir/adapters/`
- 检测云端版本，下载更新覆盖
- 按 platform + version 匹配加载对应 Adapter 脚本
- 每个 Adapter 脚本独立 Rhino scope，互不污染

## 任务调度 (TaskScheduler)

- 任务队列：`PriorityQueue<TaskEntity>` + `CoroutineScope` 管理
- 任务超时看门狗，超时自动失败
- 支持取消、重试（可配置次数）
- 串行执行，同一时间只跑一个自动化任务
- 当前任务状态、进度通过 `StateFlow` 暴露给 UI

## 热更新

- 启动时请求检查更新 API
- APK 整包更新：下载 → 调系统安装器
- Adapter ZIP 增量更新：下载 → 解压覆盖 scripts 目录 → 重启 ScriptService
- 更新期间锁任务队列

## 权限与无障碍服务

- `AccessibilityService` 从 `:automator` 模块注册（`AndroidManifest.xml` 声明）
- 首次启动引导页按顺序检查：无障碍服务 → 悬浮窗 → 通知 → 后台运行 → 截屏
- 权限状态通过 `StateFlow` 实时反映到首页

## Adapter 脚本（保留现有 TS 源码编译为 JS）

- 闲鱼 (com.taobao.idlefish): 7.12.10 / 7.26.30
- 拼多多 (com.xunmeng.pinduoduo): 6.67.0
- 转转 (com.wuba.zhuanzhuan): 9.2.21
- 微博 (com.sina.weibo): 14.5.2 / 16.4.0
- 小红书 (com.xingin.xhs): 8.14.0
- 多多团长 (com.duoduo.tuanzhang): 2.13.0
