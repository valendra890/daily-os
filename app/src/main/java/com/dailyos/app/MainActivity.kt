package com.dailyos.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dailyos.app.ui.navigation.AppNav
import com.dailyos.app.ui.theme.DailyOSTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { DailyOSTheme { AppNav() } }
    }
}
