package com.gtbyte.jorgeLanza.auth

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gtbyte.jorgeLanza.R
import com.gtbyte.jorgeLanza.components.CustomInputLabelComponent
import com.gtbyte.jorgeLanza.home.HomeActivity

@Composable
fun LoginScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var logginHasError by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("byteJorge", Context.MODE_PRIVATE)
    val credentials = sharedPreferences.getString(username, null) ?: ""


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 50.dp, end = 50.dp, top = 100.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.size(200.dp)
        )

        if(logginHasError){
            Spacer(modifier = Modifier.height(35.dp))
            Text(
                text = "Usuario/Clave inválida",
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.error

            )
            Spacer(modifier = Modifier.height(15.dp))
        } else {
            Spacer(modifier = Modifier.height(70.dp))
        }

        CustomInputLabelComponent(
            label = "Usuario",
            placeholder = "Ingresa tu usuario",
            value = username,
            hasError = logginHasError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            onvValueChange = {
                username = it
                logginHasError = false
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        CustomInputLabelComponent(
            label = "Contraseña",
            placeholder = "Ingresa tu contraseña",
            value = password,
            hasError = logginHasError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            onvValueChange = {
                password = it
                logginHasError = false
            }
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            enabled = username != "" && password != "",
            onClick = {
                logginHasError = checkCredentials(credentials, username, password)
                if(!logginHasError){
                    val intent = Intent(context, HomeActivity::class.java)
                    intent.putExtra("USERNAME", username)
                    context.startActivity(intent)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ingresar")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "Registrarme",
                style = TextStyle(
                    textDecoration = TextDecoration.Underline,
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier
                    .clickable {  navController.navigate("register") }
                    .padding(8.dp)
            )
        }
    }
}

fun checkCredentials(credentials: String, user: String, password: String): Boolean {
    val credentialsArray = credentials.split(":")
    return credentialsArray[0] != user || credentialsArray[1] != password
}