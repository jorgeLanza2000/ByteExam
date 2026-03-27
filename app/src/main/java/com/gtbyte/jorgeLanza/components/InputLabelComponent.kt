package com.gtbyte.jorgeLanza.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.gtbyte.jorgeLanza.ui.theme.darkBlue
import com.gtbyte.jorgeLanza.ui.theme.lightBlue

@Composable
fun CustomInputLabelComponent(
    label: String,
    placeholder: String,
    value: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onvValueChange: (String) -> Unit
){
    val isPassword = keyboardOptions.keyboardType == KeyboardType.Password
    Column(
        modifier = Modifier.fillMaxWidth()
    ){
        Text(text = label)

        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(
            value = value,
            onValueChange = onvValueChange,
            singleLine = true,
            placeholder = { Text(placeholder) },
            shape = RoundedCornerShape(size = 40.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = lightBlue,
                unfocusedTextColor = lightBlue,
                unfocusedPlaceholderColor = darkBlue
            ),
            keyboardOptions = keyboardOptions,
            visualTransformation = if (isPassword)
                PasswordVisualTransformation()
            else
                VisualTransformation.None,
            modifier = Modifier.fillMaxWidth()
        )
    }
}