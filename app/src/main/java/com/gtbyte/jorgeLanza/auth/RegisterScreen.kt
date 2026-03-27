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
fun RegisterScreen(navController: NavController) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirm by remember { mutableStateOf("") }

    val usernameHasError = username.length < 8 && username != ""
    val invalidPassword = (password.length < 6 || !password.matches(Regex(".*[A-Z].*"))) && password != ""
    val invalidPasswordMessage = if(!password.matches(Regex(".*[A-Z].*"))) "Debe contener al menos una letra Mayúscula." else "Debe contener al menos 6 caracteres"
    val invalidPasswordConfirm = password != passwordConfirm && password != "" && passwordConfirm != ""
    val buttonEnabled = !usernameHasError && !invalidPassword && !invalidPasswordConfirm && username != "" && password != "" && passwordConfirm != ""

    val context = LocalContext.current
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

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Registrate",
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(30.dp))

        CustomInputLabelComponent(
            label = "Usuario",
            placeholder = "Ingresa un usuario",
            value = username,
            hasError = usernameHasError,
            errorText = "*Debe contener al menos 8 caracteres",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            onvValueChange = {username = it}
        )

        Spacer(modifier = Modifier.height(5.dp))

        CustomInputLabelComponent(
            label = "Contraseña",
            placeholder = "Ingresa una contraseña",
            value = password,
            hasError = invalidPassword,
            errorText = invalidPasswordMessage,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            onvValueChange = {password = it}
        )

        Spacer(modifier = Modifier.height(10.dp))

        CustomInputLabelComponent(
            label = "Confirmar contraseña",
            placeholder = "Ingrese de nuevo la contraseña",
            value = passwordConfirm,
            hasError = invalidPasswordConfirm,
            errorText = "Las contraseñas no coinciden",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            onvValueChange = {passwordConfirm = it}
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                SaveUser(context, username, password)
                val intent = Intent(context, HomeActivity::class.java)
                intent.putExtra("username", username)
                context.startActivity(intent)
            },
            enabled = buttonEnabled,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrarse")
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Ya cuento con un usuario",
                style = TextStyle(
                    textDecoration = TextDecoration.Underline,
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier
                    .clickable {  navController.navigate("login") }
                    .padding(8.dp)
            )
        }
    }
}

fun SaveUser(context: Context, username: String, password: String){
    val sharedPreferences = context.getSharedPreferences("byteJorge", Context.MODE_PRIVATE)
    sharedPreferences.edit().putString(username, "$username:$password").apply()
}