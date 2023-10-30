package com.mertkaragul.piztaurant.View

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mertkaragul.piztaurant.Enum.ERoute
import com.mertkaragul.piztaurant.Model.BottomNav.BottomNavModel
import com.mertkaragul.piztaurant.View.Cart.CartPage
import com.mertkaragul.piztaurant.View.NavigationBottom.NavigationBottom
import com.mertkaragul.piztaurant.View.NavigationBottom.PizTTopBar
import com.mertkaragul.piztaurant.View.PizzaDetailPage.PizzaDetailPage
import com.mertkaragul.piztaurant.View.PizzaMenu.PizzaMenuPage
import com.mertkaragul.piztaurant.ui.theme.PizTaurantTheme


@Composable
fun RoutePage(
    rememberNavHost: NavHostController
) {
    val navBackStack by rememberNavHost.currentBackStackEntryAsState()
    val bottomNavigationList = listOf<BottomNavModel>(
        BottomNavModel(0, "Ana sayfa" , Icons.Filled.Home, ERoute.PIZZA_MENU_PAGE.toString()),
        BottomNavModel(1, "Siparişler" , Icons.Filled.ShoppingCart, ERoute.CART_PAGE.toString()),
    )

    val bannedBottomBarList = listOf<String>(
        ERoute.HOME_PAGE.toString(),
        ERoute.REGISTER_PAGE.toString(),
        "PIZZA_DETAIL_PAGE/{pizzaModel}"
    )

    val bannedTopBarList = listOf<String>(
        ERoute.HOME_PAGE.toString(),
        ERoute.REGISTER_PAGE.toString(),
        "PIZZA_DETAIL_PAGE/{pizzaModel}",
        ERoute.PIZZA_MENU_PAGE.toString(),
        ERoute.PIZZA_DETAIL_PAGE.toString()
    )

    Scaffold(
        topBar = {
            val bannedTopBar = navBackStack?.destination?.route
            AnimatedVisibility(visible = bannedTopBarList.find { it == bannedTopBar } == null ) {
                PizTTopBar("Siparişleriniz")
            }
        },

        bottomBar = {
            val bannedBottomBar = navBackStack?.destination?.route
            AnimatedVisibility(visible = bannedBottomBarList.find { it == bannedBottomBar } == null ) {
                NavigationBottom(bottomNavigationList, rememberNavHost)
            }
        },

        content = {
            NavHost(
                modifier = Modifier.padding(it),
                navController = rememberNavHost,
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
                    "${ERoute.PIZZA_DETAIL_PAGE}/{pizzaModel}",
                    arguments = listOf(navArgument("pizzaModel") { type = NavType.StringType})
                ){navStack ->
                    val json = navStack.arguments?.getString("pizzaModel")
                    PizzaDetailPage(
                        rememberNavHost,
                        json
                    )
                }

                composable(ERoute.CART_PAGE.toString()){
                    CartPage()
                }
            }
        }
    )
}


@Preview
@Composable
fun PreviewRoutePage() {
    PizTaurantTheme {
        RoutePage(rememberNavController())
    }
}