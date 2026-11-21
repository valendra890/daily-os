package com.dailyos.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dailyos.app.data.AppDatabase
import com.dailyos.app.data.TransactionEntity
import com.dailyos.app.ui.components.GlassCard
import com.dailyos.app.ui.theme.DailyColors
import kotlinx.coroutines.launch

@Composable
fun FinanceScreen() {
    val ctx = LocalContext.current
    val db = remember { AppDatabase.get(ctx) }
    val scope = rememberCoroutineScope()
    val tx by db.transactionDao().observeAll().collectAsState(initial = emptyList())
    var amount by remember { mutableStateOf("") }

    val balance = tx.filter { it.type == "INCOME" }.sumOf { it.amount } - tx.filter { it.type == "EXPENSE" }.sumOf { it.amount }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Keuangan", fontSize = 22.sp, color = DailyColors.TextPrimary)
        GlassCard(Modifier.padding(vertical = 8.dp)) {
            Text("Saldo: Rp ${"%,d".format(balance)}", color = DailyColors.TextPrimary, fontSize = 20.sp)
        }
        Row {
            OutlinedTextField(amount, { amount = it.filter(Char::isDigit) }, Modifier.weight(1f), placeholder = { Text("Nominal") })
            Button(onClick = {
                amount.toLongOrNull()?.let { v -> if (v > 0) scope.launch {
                    db.transactionDao().insert(TransactionEntity(type = "EXPENSE", amount = v, category = "Umum")); amount = ""
                } }
            }) { Text("+ Keluar") }
        }
    }
}
