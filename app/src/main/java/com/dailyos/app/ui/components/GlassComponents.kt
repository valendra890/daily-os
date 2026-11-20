package com.dailyos.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GlassCard(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {
    val shape = RoundedCornerShape(24.dp)
    Box(
        modifier
            .clip(shape)
            .background(Brush.linearGradient(listOf(Color(0x2A1E2A5E), Color(0x1A0A0F2C))))
            .border(1.dp, Brush.linearGradient(listOf(Color(0x40FFFFFF), Color(0x10FFFFFF))), shape)
            .padding(16.dp)
    ) { Column(content = content) }
}
