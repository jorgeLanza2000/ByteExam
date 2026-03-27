package com.gtbyte.jorgeLanza.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.gtbyte.jorgeLanza.ui.theme.ByteAplicationTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val username = intent.getStringExtra("username") ?: "Invitado"

        setContent {
            ByteAplicationTheme {
                HomeScreen(username = username)
            }
        }
    }
}
