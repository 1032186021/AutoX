package com.saltfish.assistant

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import com.saltfish.assistant.ui.components.MessageHost
import com.saltfish.assistant.ui.splash.SplashScreen
import com.saltfish.assistant.ui.splash.isNetworkAvailable
import com.saltfish.assistant.ui.splash.isVpnOrProxyActive
import com.saltfish.assistant.ui.theme.SaltfishTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : ComponentActivity() {

    companion object {
        const val EXTRA_LOGGED_IN = "logged_in"
        private const val COUNTDOWN_SECONDS = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Immersive fullscreen
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            )
        }
        window.statusBarColor = android.graphics.Color.TRANSPARENT

        // Keep screen on during splash
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        setContent {
            SaltfishTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    Box {
                        SplashContent()
                        MessageHost()
                    }
                }
            }
        }
    }

    @Composable
    private fun SplashContent() {
        var countdown by remember { mutableStateOf(COUNTDOWN_SECONDS) }
        var checksDone by remember { mutableStateOf(false) }
        var loggedIn by remember { mutableStateOf(false) }
        var finished by remember { mutableStateOf(false) }
        var checking by remember { mutableStateOf(true) }

        val goNext: (Boolean) -> Unit = goNext@{ loggedInResult ->
            if (finished) return@goNext
            finished = true
            startActivity(
                Intent(this, MainActivity::class.java).apply {
                    putExtra(EXTRA_LOGGED_IN, loggedInResult)
                }
            )
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }

        // Background checks: network, VPN, refreshToken
        LaunchedEffect(Unit) {
            try {
                if (!isNetworkAvailable()) {
                    // No network, still proceed — just mark as not logged in
                    loggedIn = false
                } else if (isVpnOrProxyActive()) {
                    // VPN detected, still proceed but don't auto-login
                    loggedIn = false
                } else {
                    val app = applicationContext as SaltfishApp
                    loggedIn = app.accountRepository.refreshToken()
                }
            } catch (_: Exception) {
                loggedIn = false
            } finally {
                checksDone = true
                checking = false
            }
        }

        // Countdown timer
        LaunchedEffect(Unit) {
            for (s in COUNTDOWN_SECONDS downTo 1) {
                countdown = s
                delay(1000)
            }
            countdown = 0
            // Wait for checks to complete, then go
            if (checksDone) {
                goNext(loggedIn)
            }
        }

        // When countdown hits 0 and checks are done, auto-navigate
        LaunchedEffect(countdown, checksDone) {
            if (countdown <= 0 && checksDone && !finished) {
                goNext(loggedIn)
            }
        }

        SplashScreen(
            countdown = countdown,
            isChecking = checking,
            onSkip = { goNext(loggedIn) }
        )
    }

    override fun onBackPressed() {
        // Block back during splash
    }
}
