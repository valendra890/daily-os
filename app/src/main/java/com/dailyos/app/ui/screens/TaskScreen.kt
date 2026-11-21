package com.dailyos.app.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dailyos.app.data.AppDatabase
import com.dailyos.app.data.TaskEntity
import com.dailyos.app.ui.theme.DailyColors
import kotlinx.coroutines.launch

@Composable
fun TaskScreen() {
    val ctx = LocalContext.current
    val db = remember { AppDatabase.get(ctx) }
    val scope = rememberCoroutineScope()
    val tasks by db.taskDao().observeAll().collectAsState(initial = emptyList())
    var input by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Tugas", fontSize = 22.sp, color = DailyColors.TextPrimary)
        Row(Modifier.padding(vertical = 8.dp)) {
            OutlinedTextField(input, { input = it }, Modifier.weight(1f), placeholder = { Text("Tugas...") }, singleLine = true)
            Button(onClick = {
                if (input.isNotBlank()) scope.launch {
                    db.taskDao().insert(TaskEntity(title = input.trim())); input = ""
                }
            }) { Text("+") }
        }
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(tasks) { task ->
                Card(Modifier.fillMaxWidth().clickable {
                    scope.launch { db.taskDao().update(task.copy(isCompleted = !task.isCompleted)) }
                }) {
                    Row(Modifier.padding(12.dp)) {
                        Checkbox(checked = task.isCompleted, onCheckedChange = {
                            scope.launch { db.taskDao().update(task.copy(isCompleted = it)) }
                        })
                        Text(task.title, color = DailyColors.TextPrimary)
                    }
                }
            }
        }
    }
}
