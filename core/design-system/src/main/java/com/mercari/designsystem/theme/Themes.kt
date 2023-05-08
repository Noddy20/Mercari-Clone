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
    primary = ColorBackgroundDark,
    primaryVariant = ColorBlack,
    secondary = ColorMercariBlue,
    secondaryVariant = ColorMercariYellow,
    background = ColorBackgroundDark,
    surface = ColorBackgroundDark,
    error = ColorRed,
    onPrimary = ColorFontTitleLight,
    onSecondary = ColorFontBodyLight,
    onBackground = ColorFontTitleLight,
    onSurface = ColorFontTitleLight,
    onError = ColorRed
)

private val LightColorScheme = lightColors(
    primary = ColorBackgroundLight,
    primaryVariant = ColorWhite,
    secondary = ColorMercariBlue,
    secondaryVariant = ColorMercariYellow,
    background = ColorBackgroundLight,
    surface = ColorBackgroundLight,
    error = ColorRed,
    onPrimary = ColorFontTitleDark,
    onSecondary = ColorFontBodyDark,
    onBackground = ColorFontTitleDark,
    onSurface = ColorFontTitleDark,
    onError = ColorRed
)

@Composable
fun MercariTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val typography = if (darkTheme) TypographyDark else TypographyLight
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.apply {
                statusBarColor = colorScheme.secondary.toArgb()
                WindowCompat.getInsetsController(
                    /* window = */
                    this,
                    /* view = */
                    view
                ).isAppearanceLightStatusBars = darkTheme
            }
        }
    }

    MaterialTheme(
        colors = colorScheme,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
