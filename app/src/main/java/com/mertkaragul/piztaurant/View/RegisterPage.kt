package com.mertkaragul.piztaurant.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.mertkaragul.piztaurant.View.Elements.PizTDefaultSpacerHeight
import com.mertkaragul.piztaurant.View.Elements.PizTTextField
import com.mertkaragul.piztaurant.ui.theme.PizTaurantTheme

@Composable
fun RegisterPage() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var text by remember {
            mutableStateOf("")
        }
        Text(
            text = "Let's create account",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 35.sp
        )
        PizTDefaultSpacerHeight()
        PizTTextField(
            text,
            onValueChange = {text = it},
            "Username",
            "Please fill username"
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PrevRegisterPage() {
    PizTaurantTheme {
        RegisterPage()
    }
}