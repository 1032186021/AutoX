package com.saltfish.assistant.ui.navigation

import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.saltfish.assistant.SaltfishApp
import com.saltfish.assistant.ui.home.HomeScreen
import com.saltfish.assistant.ui.login.LoginScreen
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
}

@Composable
fun SaltfishNavGraph() {
    val navController = rememberNavController()
    val context = androidx.compose.ui.platform.LocalContext.current
    val app = context.applicationContext as SaltfishApp
    val startRoute = if (app.preferencesManager.isLoggedIn()) Screen.Home.route else Screen.Login.route

    NavHost(navController = navController, startDestination = startRoute) {
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
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
            // AutomationScreen placeholder
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
