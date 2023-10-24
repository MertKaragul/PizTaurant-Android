package com.mertkaragul.piztaurant.View

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mertkaragul.piztaurant.Enum.ERoute
import com.mertkaragul.piztaurant.View.PizzaDetailPage.PizzaDetailPage
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

        composable(ERoute.PIZZA_MENU_PAGE.toString()){
            PizzaMenuPage(rememberNavHost)
        }

        composable(
            "${ERoute.PIZZA_DETAIL_PAGE.toString()}/{pizzaModel}",
            arguments = listOf(navArgument("pizzaModel") { type = NavType.StringType})
        ){
            val json = it.arguments?.getString("pizzaModel")
            PizzaDetailPage(
                rememberNavHost,
                json
            )
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