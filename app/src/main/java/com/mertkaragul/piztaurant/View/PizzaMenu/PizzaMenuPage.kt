package com.mertkaragul.piztaurant.View.PizzaMenu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mertkaragul.piztaurant.Enum.ERoute
import com.mertkaragul.piztaurant.R
import com.mertkaragul.piztaurant.View.Elements.PizTDiscountText
import com.mertkaragul.piztaurant.Viewmodel.PizzaMenuViewModel
import com.mertkaragul.piztaurant.ui.theme.PizTaurantTheme

@Composable
fun PizzaMenuPage(
    rememberNavHost : NavHostController,
    pizzaMenuViewModel: PizzaMenuViewModel = viewModel()
) {
    val height = LocalConfiguration.current.screenHeightDp
    val width = LocalConfiguration.current.screenWidthDp
    val context = LocalContext.current
    val listState = rememberLazyListState()

    val pizzaList = pizzaMenuViewModel.pizzaList.observeAsState()
    val pizzaImageList = pizzaMenuViewModel.pizzaImages.observeAsState()
    
    pizzaList.value?.let {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState
        ){
            items(it){ pizzaModel ->
                val image = pizzaImageList.value?.find { it.pizzaName == pizzaModel.pizzaName }?.pizzaImage ?: R.drawable.ic_launcher_foreground
                PizzaMenuItem(
                    pizzaMenuViewModel,
                    pizzaModel,
                    image,
                    height,
                    width,
                    rememberNavHost
                )
            }
        }
    }

    LaunchedEffect(key1 = Unit){
        pizzaMenuViewModel.getPizza(context)
    }


}

@Preview(showBackground = true)
@Composable
fun PreviewMenuPage() {
    PizTaurantTheme {
        PizzaMenuPage(rememberNavHost = rememberNavController())
    }
}