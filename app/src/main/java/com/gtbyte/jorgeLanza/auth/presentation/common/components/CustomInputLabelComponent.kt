package com.gtbyte.jorgeLanza.auth.presentation.common.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun CustomInputLabelComponent(
    label: String,
    placeholder: String,
    value: String,
    hasError: Boolean,
    errorText: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onvValueChange: (String) -> Unit
){
    val isPassword = keyboardOptions.keyboardType == KeyboardType.Password
    Column(
        modifier = Modifier.fillMaxWidth()
    ){
        Text(text = label)

        Spacer(modifier = Modifier.height(5.dp))

        OutlinedTextField(
            value = value,
            onValueChange = onvValueChange,
            singleLine = true,
            placeholder = { Text(placeholder) },
            isError = hasError,
            supportingText = { if (hasError) Text(errorText)},
            shape = RoundedCornerShape(size = 40.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.primary,
                unfocusedTextColor = MaterialTheme.colorScheme.primary,
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.secondary,
                errorTextColor = MaterialTheme.colorScheme.error
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