package com.saltfish.assistant.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.navOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.saltfish.assistant.ui.automation.AutomationContent
import com.saltfish.assistant.ui.automation.AutomationTopBar
import com.saltfish.assistant.ui.navigation.Screen
import com.saltfish.assistant.ui.task.TaskContent
import com.saltfish.assistant.ui.task.TaskTopBar
import com.saltfish.assistant.ui.user.UserCenterContent
import com.saltfish.assistant.ui.user.UserCenterTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(rootNavController: NavController) {
    val mainNavController = rememberNavController()
    val navBackStackEntry by mainNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Screen.Home.route

    Scaffold(
        topBar = {
            when (currentRoute) {
                Screen.Home.route -> HomeTopBar(
                    onSettings = { rootNavController.navigate(Screen.Settings.route) }
                )
                Screen.Automation.route -> AutomationTopBar()
                Screen.Task.route -> TaskTopBar()
                Screen.UserCenter.route -> UserCenterTopBar()
            }
        },
        bottomBar = {
            SaltfishBottomBar(
                currentRoute = currentRoute,
                onNavigate = { route ->
                    mainNavController.navigate(route) {
                        popUpTo(Screen.Home.route) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        NavHost(
            navController = mainNavController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeContent(rootNavController = rootNavController)
            }
            composable(Screen.Automation.route) {
                AutomationContent()
            }
            composable(Screen.Task.route) {
                TaskContent()
            }
            composable(Screen.UserCenter.route) {
                UserCenterContent(
                    onLogout = {
                        rootNavController.navigate(Screen.Login.route) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun SaltfishBottomBar(currentRoute: String, onNavigate: (String) -> Unit) {
    val navItemColors = NavigationBarItemDefaults.colors(
        selectedIconColor = MaterialTheme.colorScheme.primary,
        selectedTextColor = MaterialTheme.colorScheme.primary,
        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
        indicatorColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
    )

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.85f)
    ) {
        NavigationBarItem(
            selected = currentRoute == Screen.Home.route,
            onClick = { onNavigate(Screen.Home.route) },
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text("首页") },
            colors = navItemColors
        )
        NavigationBarItem(
            selected = currentRoute == Screen.Automation.route,
            onClick = { onNavigate(Screen.Automation.route) },
            icon = { Icon(Icons.Default.PlayArrow, contentDescription = null) },
            label = { Text("自动化") },
            colors = navItemColors
        )
        NavigationBarItem(
            selected = currentRoute == Screen.Task.route,
            onClick = { onNavigate(Screen.Task.route) },
            icon = { Icon(Icons.Default.List, contentDescription = null) },
            label = { Text("任务队列") },
            colors = navItemColors
        )
        NavigationBarItem(
            selected = currentRoute == Screen.UserCenter.route,
            onClick = { onNavigate(Screen.UserCenter.route) },
            icon = { Icon(Icons.Default.AccountCircle, contentDescription = null) },
            label = { Text("用户中心") },
            colors = navItemColors
        )
    }
}
