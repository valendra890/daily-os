package com.dailyos.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkScheme = darkColorScheme(
    primary = DailyColors.ElectricBlue,
    secondary = DailyColors.Cyan,
    tertiary = DailyColors.Violet,
    background = DailyColors.MidnightNavy,
    surface = Color(0xFF0B1026),
    onPrimary = Color.White,
    onBackground = DailyColors.TextPrimary,
    onSurface = DailyColors.TextPrimary
)

@Composable
fun DailyOSTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = DarkScheme, content = content)
}
