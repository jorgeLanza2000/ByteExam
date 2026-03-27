package com.gtbyte.jorgeLanza.resgister

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun RegisterScreen(navController: NavController) {

    Column {
        Text("Registro")

        Button(onClick = {
            navController.popBackStack() // volver al login
        }) {
            Text("Volver")
        }
    }
}