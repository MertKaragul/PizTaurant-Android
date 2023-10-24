package com.mertkaragul.piztaurant.View

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mertkaragul.piztaurant.Enum.ERoute
import com.mertkaragul.piztaurant.Model.InformationModel.InformationModel
import com.mertkaragul.piztaurant.View.Elements.PizTAlertDialog
import com.mertkaragul.piztaurant.View.Elements.PizTDefaultSpacerHeight
import com.mertkaragul.piztaurant.View.Elements.PizTPasswordTextField
import com.mertkaragul.piztaurant.View.Elements.PizTTextField
import com.mertkaragul.piztaurant.Viewmodel.RegisterViewModel
import com.mertkaragul.piztaurant.ui.theme.PizTaurantTheme

@Composable
fun RegisterPage(
    rememberNavHost: NavHostController,
    registerViewModel: RegisterViewModel = viewModel()
) {
    val width = LocalConfiguration.current.screenWidthDp
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    /** Error, Exception variables **/
    var showInformation by remember { mutableStateOf(false) }
    var informationModel by remember { mutableStateOf<InformationModel?>(null) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Let's create account",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 35.sp
        )
        PizTDefaultSpacerHeight()
        PizTTextField(
            username,
            onValueChange = {username = it},
            "Username",
            "Please fill username",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        PizTDefaultSpacerHeight()
        
        PizTTextField(
            email,
            onValueChange = {email = it},
            "Email",
            "Please fill email",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        PizTDefaultSpacerHeight()

        PizTPasswordTextField(
            password,
            onValueChange = {password = it},
            "Password",
            "Please fill password",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        PizTDefaultSpacerHeight()
        Button(onClick = {
            registerViewModel.createAccount(username, email, password){ exceptionMessage ->
                informationModel = exceptionMessage
                showInformation = true
            }

        }, modifier = Modifier.width((width * .7).dp),shape = RoundedCornerShape(5.dp) ){
            Text(
                "Create account"
            )
        }
        PizTDefaultSpacerHeight()
        Text(
            text = "You want will see delicious pizza, create account",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 12.sp
        )
    }


    AnimatedVisibility(visible = showInformation) {
        PizTAlertDialog(
            title = informationModel?.title ?: "Error" ,
            description = informationModel?.description ?: "Error, please try again",
            confirm = { Button(onClick =  {
                if (informationModel?.title?.contains("Success") != null) {
                    rememberNavHost.navigate(ERoute.HOME_PAGE.toString())
                }
                showInformation = !showInformation
            }
            ) { Text(text = "Ok")} },
            dismiss = {  },
            dismissReq = { showInformation = !showInformation })
    }
}


@Preview(showBackground = true)
@Composable
fun PrevRegisterPage() {
    PizTaurantTheme {
        RegisterPage(rememberNavController())
    }
}