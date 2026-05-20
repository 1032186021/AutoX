package com.saltfish.assistant.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.saltfish.assistant.ui.home.HomeScreen
import com.saltfish.assistant.ui.task.TaskScreen
import com.saltfish.assistant.ui.settings.SettingsScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Task : Screen("task")
    object Automation : Screen("automation")
    object Settings : Screen("settings")
}

@Composable
fun SaltfishNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.Task.route) {
            TaskScreen()
        }
        composable(Screen.Automation.route) {
            // AutomationScreen placeholder - will be implemented later
        }
        composable(Screen.Settings.route) {
            SettingsScreen()
        }
    }
}
