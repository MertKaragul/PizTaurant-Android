package com.mertkaragul.piztaurant.View.Elements

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mertkaragul.piztaurant.ui.theme.PizTaurantTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PizTTextField(
    it : String,
    onValueChange : (String) -> Unit,
    placeholder : String,
    errorMessage : String
) {
    var error by remember {
        mutableStateOf(false)
    }

    TextField(
        value = it ,
        onValueChange = {
            onValueChange(it)
            error = it.isEmpty()
        },
        label = {
            AnimatedContent(
                targetState = if (error) errorMessage else placeholder ,
                label = "" ,
                content = {
                    Text(
                        text = it,
                        color = if (error) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary

                    )
                }
            )
        },
        shape = RoundedCornerShape(10.dp),
        singleLine = true,
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedPrefixColor = Color.Transparent,
            unfocusedSuffixColor = Color.Transparent,
            unfocusedPlaceholderColor = Color.Transparent,
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            unfocusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedTextColor = MaterialTheme.colorScheme.primary,

            focusedTextColor = MaterialTheme.colorScheme.primary,
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            focusedIndicatorColor = Color.Transparent,
            focusedPrefixColor = Color.Transparent,
            focusedSuffixColor = Color.Transparent,
            focusedPlaceholderColor = Color.Transparent,

            errorContainerColor = MaterialTheme.colorScheme.errorContainer,
            errorTextColor = MaterialTheme.colorScheme.error,
            errorLabelColor = MaterialTheme.colorScheme.error,
            errorIndicatorColor = Color.Transparent,
            errorPrefixColor = Color.Transparent,
            errorSuffixColor = Color.Transparent,
            errorPlaceholderColor = Color.Transparent,

        ),

        isError = error,
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewPizTTextField() {
    PizTaurantTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            var text by remember { mutableStateOf("") }
            PizTTextField(text,{ text = it },placeholder = "Username" , "Please fill username")
        }
    }
}