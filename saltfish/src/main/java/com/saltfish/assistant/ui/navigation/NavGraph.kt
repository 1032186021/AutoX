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
