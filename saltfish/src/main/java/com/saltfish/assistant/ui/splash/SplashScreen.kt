package com.saltfish.assistant.ui.splash

import android.graphics.BitmapFactory
import android.net.NetworkCapabilities
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saltfish.assistant.BuildConfig
import com.saltfish.assistant.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class SplashOemInfo(
    val appName: String = "咸鱼助手",
    val appVersion: String = BuildConfig.VERSION_NAME,
    val scriptVersion: String = BuildConfig.SCRIPT_VERSION
)

@Composable
fun SplashScreen(
    countdown: Int,
    isChecking: Boolean,
    oemInfo: SplashOemInfo = SplashOemInfo(),
    onSkip: () -> Unit
) {
    val context = LocalContext.current
    val density = LocalDensity.current
    val statusBarH = with(density) {
        WindowInsets.statusBars.getTop(density).toDp()
    }

    // Animations
    val bgAlpha = remember { Animatable(0f) }
    val skipAlpha = remember { Animatable(0f) }
    val bottomAlpha = remember { Animatable(0f) }

    // Skip button press
    val skipInteraction = remember { MutableInteractionSource() }
    val skipPressed = skipInteraction.collectIsPressedAsState().value
    val skipScale by animateFloatAsState(
        targetValue = if (skipPressed) 0.92f else 1f,
        animationSpec = tween(if (skipPressed) 80 else 120)
    )

    // Staggered entrance
    LaunchedEffect(Unit) {
        launch { bgAlpha.animateTo(1f, tween(800)) }
        launch { delay(200); bottomAlpha.animateTo(1f, tween(500)) }
        launch { delay(400); skipAlpha.animateTo(1f, tween(300)) }
    }

    // Load splash background
    val splashBitmap = remember {
        runCatching {
            BitmapFactory.decodeResource(context.resources, R.drawable.splash_bg)
        }.getOrNull()
    }

    // Load app icon for bottom bar
    val appIcon = remember {
        runCatching {
            BitmapFactory.decodeResource(context.resources, R.mipmap.ic_launcher)
        }.getOrNull()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // ── Background image ──
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(bgAlpha.value)
        ) {
            if (splashBitmap != null) {
                Image(
                    bitmap = splashBitmap.asImageBitmap(),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        // ── Skip button (top-right) ──
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = statusBarH, end = 15.dp)
                .alpha(skipAlpha.value)
                .scale(skipScale)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0x66000000))
                .clickable(
                    interactionSource = skipInteraction,
                    indication = null
                ) { onSkip() }
                .padding(8.dp)
        ) {
            Text(
                text = if (countdown > 0) "跳过 ${countdown}s" else "跳过",
                color = Color.White,
                fontSize = 12.sp
            )
        }

        // ── Loading indicator (center, shown when checks still running) ──
        if (isChecking && countdown <= 0) {
            Text(
                text = "正在检查...",
                fontSize = 13.sp,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(bottom = 120.dp)
            )
        }

        // ── Bottom bar ──
        val bottomH = 120.dp
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(bottomH)
                .alpha(bottomAlpha.value)
                .background(Color(0x4D000000)),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // App logo
                if (appIcon != null) {
                    Image(
                        bitmap = appIcon.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier
                            .size(72.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )
                }

                Spacer(modifier = Modifier.width(15.dp))

                Column {
                    Text(
                        text = oemInfo.appName,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "version: ${oemInfo.appVersion}-${oemInfo.scriptVersion}",
                        fontSize = 14.sp,
                        color = Color(0xFFCCCCCC)
                    )
                }
            }
        }
    }
}

/** Check if VPN/proxy is active */
fun isVpnOrProxyActive(): Boolean {
    return try {
        val cm = com.saltfish.assistant.SaltfishApp.instance
            .getSystemService(android.content.Context.CONNECTIVITY_SERVICE)
            as? android.net.ConnectivityManager ?: return false
        val network = cm.activeNetwork ?: return false
        val caps = cm.getNetworkCapabilities(network) ?: return false
        caps.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
    } catch (_: Exception) { false }
}

/** Check if network is available */
fun isNetworkAvailable(): Boolean {
    return try {
        val cm = com.saltfish.assistant.SaltfishApp.instance
            .getSystemService(android.content.Context.CONNECTIVITY_SERVICE)
            as? android.net.ConnectivityManager ?: return false
        val network = cm.activeNetwork ?: return false
        val caps = cm.getNetworkCapabilities(network) ?: return false
        caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    } catch (_: Exception) { false }
}
