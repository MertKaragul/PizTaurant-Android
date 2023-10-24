package com.mertkaragul.piztaurant.View

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mertkaragul.piztaurant.Enum.ERoute
import com.mertkaragul.piztaurant.ui.theme.PizTaurantTheme


@Composable
fun RoutePage(
    rememberNavHost: NavHostController = rememberNavController()
) {
    NavHost(navController = rememberNavHost,
        startDestination = ERoute.HOME_PAGE.toString()){
        composable(ERoute.HOME_PAGE.toString()){
            Home(rememberNavHost)
        }

        composable(ERoute.REGISTER_PAGE.toString()){
            RegisterPage(rememberNavHost)
        }

        composable(ERoute.PIZZA_DETAIL_PAGE.toString()){

        }

        composable(ERoute.CART_PAGE.toString()){

        }
    }
}


@Preview
@Composable
fun PreviewRoutePage() {
    PizTaurantTheme {
        RoutePage()
    }
}