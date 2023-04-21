package com.mercari.designsystem.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColors(
    primary = ColorBlack,
    primaryVariant = ColorBlack,
    secondary = ColorMercariBlue,
    secondaryVariant = ColorMercariYellow,
    background = ColorBlack,
    surface = ColorBlack,
    error = ColorRed,
    onPrimary = ColorWhite,
    onSecondary = ColorWhite,
    onBackground = ColorWhite,
    onSurface = ColorWhite,
    onError = ColorRed
)

private val LightColorScheme = lightColors(
    primary = ColorWhite,
    primaryVariant = ColorWhite,
    secondary = ColorMercariBlue,
    secondaryVariant = ColorMercariYellow,
    background = ColorWhite,
    surface = ColorWhite,
    error = ColorRed,
    onPrimary = ColorBlack,
    onSecondary = ColorBlack,
    onBackground = ColorBlack,
    onSurface = ColorBlack,
    onError = ColorRed
)

@Composable
fun MercariTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.apply {
                statusBarColor = colorScheme.secondary.toArgb()
                WindowCompat.getInsetsController(
                    /* window = */ this,
                    /* view = */ view
                ).isAppearanceLightStatusBars = darkTheme
            }
        }
    }

    MaterialTheme(
        colors = colorScheme,
        typography = Typography,
        shapes = shapes,
        content = content
    )
}