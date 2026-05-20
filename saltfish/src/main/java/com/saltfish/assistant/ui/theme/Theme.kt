package com.saltfish.assistant.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColors(
    primary = Yellow700,
    primaryVariant = Orange500,
    secondary = Blue500,
    background = LightBackground,
    surface = LightSurface,
    error = Red500
)

private val DarkColorScheme = darkColors(
    primary = Yellow300,
    primaryVariant = Orange500,
    secondary = Blue500,
    background = DarkBackground,
    surface = DarkSurface,
    error = Red500
)

@Composable
fun SaltfishTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) DarkColorScheme else LightColorScheme,
        content = content
    )
}
