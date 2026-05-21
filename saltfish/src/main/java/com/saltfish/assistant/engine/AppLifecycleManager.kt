package com.saltfish.assistant.engine

import com.saltfish.assistant.SaltfishApp
import com.saltfish.assistant.ui.navigation.Screen

/**
 * Centralized app lifecycle state machine.
 * Mirrors the TS AppLifecycle design:
 *   SPLASH → LOGIN → startGuidedFlow → GUIDE → PERMISSION_GUIDE → MAIN
 *
 * The MAIN gate checks device activation, then guide/permission/hone.
 * Engine startup (DeviceManager, etc.) converges in [onMainEntered].
 */
class AppLifecycleManager(private val app: SaltfishApp) {

    // -- entry point (called from NavGraph) --

    /**
     * Resolves the initial route. May suspend to sync device info
     * when the user is logged in but local device data is missing.
     */
    suspend fun resolveStartRoute(splashLoggedIn: Boolean?): String {
        if (splashLoggedIn == false || (splashLoggedIn == null && !app.preferencesManager.isLoggedIn())) {
            return Screen.Login.route
        }
        return startGuidedFlow()
    }

    /**
     * Called after login succeeds. Syncs device info if missing,
     * then runs the device-activation gate.
     */
    suspend fun onLoginSuccess(): String = startGuidedFlow()

    /**
     * Called after device activation completes. Re-checks device state
     * (activation may have failed), then proceeds to guide/permissions/home.
     */
    suspend fun onDeviceActivated(): String {
        val devState = app.deviceManager.checkDevice()
        if (devState == DeviceState.Idle || devState == DeviceState.Expired) {
            return Screen.DeviceActivation.route
        }
        return postDeviceGateRoute()
    }

    // -- guide / permissions (no gate, linear flow) --

    fun onGuideDone(): String = Screen.PermissionsGuide.route
    fun onPermissionsDone(): String = Screen.Home.route

    // -- main entry --

    /** Called when Home screen first renders. Centralizes engine startup. */
    fun onMainEntered() {
        app.deviceManager.start()
    }

    // -- private --

    /**
     * TS startGuidedFlow: if logged in but no deviceId/device,
     * sync from server before continuing.
     */
    private suspend fun startGuidedFlow(): String {
        if (app.preferencesManager.isLoggedIn() && (app.preferencesManager.deviceId == null || app.deviceManager.device == null)) {
            app.deviceManager.sync()
        }
        return postDeviceGateRoute()
    }

    /** TS continueGuidedFlow + goMain gate: device → guide → permissions → home. */
    private fun postDeviceGateRoute(): String {
        val devState = app.deviceManager.checkDevice()
        if (devState == DeviceState.Idle || devState == DeviceState.Expired) {
            return Screen.DeviceActivation.route
        }
        if (!app.preferencesManager.guideShown) return Screen.Guide.route
        // 每次启动都检查权限，缺失则引导，全部已授权则自动跳过
        return Screen.PermissionsGuide.route
    }
}
