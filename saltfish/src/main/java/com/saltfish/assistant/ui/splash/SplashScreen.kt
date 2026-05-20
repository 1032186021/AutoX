package com.saltfish.assistant.ui.splash

import android.graphics.BitmapFactory
import android.net.NetworkCapabilities
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saltfish.assistant.BuildConfig
import androidx.compose.material3.MaterialTheme
import java.io.File

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

    // Animations
    val bgAlpha = remember { Animatable(0f) }
    val bgScale = remember { Animatable(1f) }
    val skipAlpha = remember { Animatable(0f) }
    val bottomAlpha = remember { Animatable(0f) }

    // Skip button press animation
    val skipInteractionSource = remember { MutableInteractionSource() }
    val skipPressed = skipInteractionSource.collectIsPressedAsState().value
    val skipScale by animateFloatAsState(
        targetValue = if (skipPressed) 0.92f else 1f,
        animationSpec = tween(durationMillis = if (skipPressed) 80 else 120)
    )
    val skipButtonAlpha by animateFloatAsState(
        targetValue = if (skipPressed) 0.85f else 1f,
        animationSpec = tween(durationMillis = if (skipPressed) 80 else 120)
    )

    LaunchedEffect(Unit) {
        bgAlpha.animateTo(1f, tween(2500))
        bgScale.animateTo(1.05f, tween(2500))
        skipAlpha.animateTo(1f, tween(400))
        bottomAlpha.animateTo(1f, tween(600))
    }

    // Try to load splash.png from filesDir
    val splashFile = remember { File(context.filesDir, "splash.png") }
    val splashBitmap = remember { if (splashFile.exists()) BitmapFactory.decodeFile(splashFile.absolutePath) else null }

    Box(modifier = Modifier.fillMaxSize()) {
        // Background layer
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
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    MaterialTheme.colorScheme.primaryContainer,
                                    MaterialTheme.colorScheme.background,
                                    MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
                                )
                            )
                        )
                )
            }
        }

        // Skip button (top-right)
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 48.dp, end = 16.dp)
                .alpha(skipAlpha.value)
                .scale(skipScale)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0x66000000))
                .clickable(
                    interactionSource = skipInteractionSource,
                    indication = null
                ) {
                    onSkip()
                }
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Text(
                text = if (countdown > 0) "跳过 ${countdown}s" else "跳过",
                color = Color.White,
                fontSize = 12.sp
            )
        }

        // Bottom info area
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .alpha(bottomAlpha.value)
                .background(Color(0x4D000000))
                .padding(vertical = 24.dp, horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Logo
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Build,
                        contentDescription = null,
                        modifier = Modifier.size(36.dp),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

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

        // Checking indicator (shown when checks are still running after countdown)
        if (isChecking && countdown <= 0) {
            Text(
                text = "正在检查...",
                fontSize = 13.sp,
                color = Color(0xFFFFFFFF),
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(bottom = 120.dp)
            )
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
