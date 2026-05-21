package com.saltfish.assistant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.saltfish.assistant.ui.activation.ActivationMode
import com.saltfish.assistant.ui.activation.DeviceActivationScreen
import com.saltfish.assistant.ui.components.MessageHost
import com.saltfish.assistant.ui.navigation.SaltfishNavGraph
import com.saltfish.assistant.ui.theme.SaltfishTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashLoggedIn = intent.getBooleanExtra(SplashActivity.EXTRA_LOGGED_IN, false)
        val hasExtra = intent.hasExtra(SplashActivity.EXTRA_LOGGED_IN)

        setContent {
            SaltfishTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var splashOverride by remember { mutableStateOf(hasExtra) }
                    val app = (this@MainActivity.applicationContext as SaltfishApp)

                    // Show activation overlay when device expires via WS command
                    var showForceActivation by remember { mutableStateOf(false) }
                    LaunchedEffect(Unit) {
                        app.deviceManager.onActivationRequired = {
                            showForceActivation = true
                        }
                    }

                    Box(modifier = Modifier.fillMaxSize()) {
                        SaltfishNavGraph(
                            splashLoggedIn = if (hasExtra) splashLoggedIn else null,
                            onSplashOverrideConsumed = { splashOverride = false }
                        )
                        MessageHost()

                        // Fullscreen activation overlay (non-dismissible)
                        if (showForceActivation) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.White)
                            ) {
                                DeviceActivationScreen(
                                    mode = ActivationMode.Renew,
                                    onDone = {
                                        showForceActivation = false
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
