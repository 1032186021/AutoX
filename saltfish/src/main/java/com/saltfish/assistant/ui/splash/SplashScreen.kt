package com.saltfish.assistant.ui.splash

import android.graphics.BitmapFactory
import android.net.NetworkCapabilities
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saltfish.assistant.BuildConfig
import com.saltfish.assistant.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class SplashOemInfo(
    val appName: String = "咸鱼助手",
    val appSlogan: String = "解放双手，做最有态度的咸鱼",
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

    // ── Animation state ──
    val bgAlpha = remember { Animatable(0f) }
    val bgScale = remember { Animatable(1f) }
    val logoAlpha = remember { Animatable(0f) }
    val textAlpha = remember { Animatable(0f) }
    val badgeAlpha = remember { Animatable(0f) }
    val skipAlpha = remember { Animatable(0f) }

    // Skip button press feedback
    val skipInteraction = remember { MutableInteractionSource() }
    val skipPressed = skipInteraction.collectIsPressedAsState().value
    val skipScale by animateFloatAsState(
        targetValue = if (skipPressed) 0.93f else 1f,
        animationSpec = spring(dampingRatio = 0.6f)
    )

    // Shimmer dots for loading
    val shimmerAlpha = remember { Animatable(0.3f) }
    LaunchedEffect(isChecking) {
        if (isChecking) {
            shimmerAlpha.animateTo(1f, tween(600, easing = LinearEasing))
        }
    }

    // ── Parallel staggered entrance ──
    LaunchedEffect(Unit) {
        // Background animates over 1.5s in parallel
        launch { bgAlpha.animateTo(1f, tween(800)) }
        launch { bgScale.animateTo(1.04f, tween(1200)) }
        // Logo appears early
        launch { delay(100); logoAlpha.animateTo(1f, tween(400)) }
        // Text follows
        launch { delay(200); textAlpha.animateTo(1f, tween(400)) }
        // Badge
        launch { delay(300); badgeAlpha.animateTo(1f, tween(300)) }
        // Skip button — must be visible quickly
        launch { skipAlpha.animateTo(1f, tween(300)) }
    }

    // Load splash background
    val splashBitmap = remember {
        runCatching {
            BitmapFactory.decodeResource(context.resources, R.drawable.splash_bg)
        }.getOrNull()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // ── Background ──
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(bgAlpha.value)
                .scale(bgScale.value)
        ) {
            if (splashBitmap != null) {
                Image(
                    bitmap = splashBitmap.asImageBitmap(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                // Fallback gradient
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    MaterialTheme.colorScheme.primary,
                                    MaterialTheme.colorScheme.primaryContainer,
                                    MaterialTheme.colorScheme.background
                                )
                            )
                        )
                )
            }
            // Uniform dark overlay for text readability
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x55000000))
            )
        }

        // ── Skip button ──
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 52.dp, end = 20.dp)
                .alpha(skipAlpha.value)
                .scale(skipScale)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White.copy(alpha = 0.15f))
                .clickable(
                    interactionSource = skipInteraction,
                    indication = null
                ) { onSkip() }
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = if (countdown > 0) "跳过 ${countdown}s" else "跳过",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )
        }

        // ── Center content ──
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo card with glass effect
            Box(
                modifier = Modifier
                    .alpha(logoAlpha.value)
                    .size(96.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.White.copy(alpha = 0.2f))
                    .then(
                        Modifier.clip(RoundedCornerShape(24.dp))
                    ),
                contentAlignment = Alignment.Center
            ) {
                // App icon
                val icon = remember {
                    runCatching {
                        BitmapFactory.decodeResource(
                            context.resources,
                            R.mipmap.ic_launcher
                        )
                    }.getOrNull()
                }
                if (icon != null) {
                    Image(
                        bitmap = icon.asImageBitmap(),
                        contentDescription = "logo",
                        modifier = Modifier
                            .size(60.dp)
                            .clip(RoundedCornerShape(14.dp))
                    )
                } else {
                    Text(
                        text = "🐟",
                        fontSize = 40.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // App name
            Text(
                text = oemInfo.appName,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.alpha(textAlpha.value)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Slogan
            Text(
                text = oemInfo.appSlogan,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White.copy(alpha = 0.85f),
                textAlign = TextAlign.Center,
                modifier = Modifier.alpha(textAlpha.value)
            )
        }

        // ── Bottom area ──
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 48.dp)
                .alpha(badgeAlpha.value),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Version pill
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White.copy(alpha = 0.12f))
                    .padding(horizontal = 14.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "v${oemInfo.appVersion}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White.copy(alpha = 0.7f),
                    letterSpacing = 0.5.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Loading indicator
            AnimatedVisibility(
                visible = isChecking,
                enter = fadeIn() + slideInVertically { it / 2 }
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        repeat(3) {
                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .alpha(shimmerAlpha.value)
                                    .clip(CircleShape)
                                    .background(Color.White.copy(alpha = 0.6f))
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "正在检查...",
                        fontSize = 12.sp,
                        color = Color.White.copy(alpha = 0.5f)
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
