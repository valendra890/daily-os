package com.dailyos.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dailyos.app.ui.components.GlassCard
import com.dailyos.app.ui.theme.DailyColors

@Composable
fun DashboardScreen() {
    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("DAILY OS", color = DailyColors.TextPrimary, fontWeight = FontWeight.Bold, fontSize = 22.sp)
        Text("Selamat Pagi! 👋", color = DailyColors.TextPrimary, fontSize = 20.sp)
        
        GlassCard {
            Text("AI Assistant", color = DailyColors.Cyan, fontWeight = FontWeight.SemiBold)
            Text("Siap produktif hari ini?", color = DailyColors.TextPrimary)
        }
        
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            GlassCard(Modifier.weight(1f)) {
                Text("92%", color = DailyColors.Cyan, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                Text("Produktivitas", color = DailyColors.TextMuted, fontSize = 12.sp)
            }
            GlassCard(Modifier.weight(1f)) {
                Text("Saldo", color = DailyColors.TextMuted, fontSize = 12.sp)
                Text("Rp 12.750.000", color = DailyColors.TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
