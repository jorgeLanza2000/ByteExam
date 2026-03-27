package com.gtbyte.jorgeLanza

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import com.gtbyte.jorgeLanza.navigation.AppNavGraph
import com.gtbyte.jorgeLanza.ui.theme.ByteAplicationTheme

class MainActivity : ComponentActivity() {
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