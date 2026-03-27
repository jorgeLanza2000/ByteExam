package com.gtbyte.jorgeLanza.auth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.gtbyte.jorgeLanza.auth.navigation.AppNavGraph
import com.gtbyte.jorgeLanza.ui.theme.ByteAplicationTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ByteAplicationTheme {
                AppNavGraph()
            }
        }
    }
}