package com.saltfish.assistant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.saltfish.assistant.ui.components.MessageHost
import com.saltfish.assistant.ui.navigation.SaltfishNavGraph
import com.saltfish.assistant.ui.theme.SaltfishTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // SplashActivity passes loggedIn state; default to preferences check if not provided
        val splashLoggedIn = intent.getBooleanExtra(SplashActivity.EXTRA_LOGGED_IN, false)
        val hasExtra = intent.hasExtra(SplashActivity.EXTRA_LOGGED_IN)

        setContent {
            SaltfishTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box {
                        var splashOverride by remember { mutableStateOf(hasExtra) }
                        SaltfishNavGraph(
                            splashLoggedIn = if (hasExtra) splashLoggedIn else null,
                            onSplashOverrideConsumed = { splashOverride = false }
                        )
                        MessageHost()
                    }
                }
            }
        }
    }
}
