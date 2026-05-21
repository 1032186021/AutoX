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
