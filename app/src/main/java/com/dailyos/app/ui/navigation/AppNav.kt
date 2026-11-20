package com.dailyos.app.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.dailyos.app.ui.screens.*

@Composable
fun AppNav() {
    val nav = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(selected = true, onClick = { nav.navigate("dashboard") }, icon = { Icon(Icons.Default.Home, null) }, label = { Text("Home") })
                NavigationBarItem(selected = false, onClick = { nav.navigate("tasks") }, icon = { Icon(Icons.Default.CheckCircle, null) }, label = { Text("Tugas") })
                NavigationBarItem(selected = false, onClick = { nav.navigate("finance") }, icon = { Icon(Icons.Default.AccountBalanceWallet, null) }, label = { Text("Uang") })
            }
        }
    ) { pad ->
        NavHost(nav, startDestination = "dashboard", modifier = Modifier.padding(pad)) {
            composable("dashboard") { DashboardScreen() }
            composable("tasks") { TaskScreen() }
            composable("finance") { FinanceScreen() }
        }
    }
}
