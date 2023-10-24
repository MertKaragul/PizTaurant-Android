package com.mertkaragul.piztaurant.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    pizzaMenuViewModel.getPizza(context)

    val pizzaList = pizzaMenuViewModel.pizzaList.observeAsState()
    val pizzaImageList = pizzaMenuViewModel.pizzaImages.observeAsState()
    
    pizzaList.value?.let {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ){
            items(it){ pizzaModel ->
                val image = pizzaImageList.value?.find { it.pizzaName == pizzaModel.pizzaName }?.pizzaImage ?: R.drawable.ic_launcher_foreground
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)) {
                    Row{
                        Image(
                            painterResource(id = image),
                            contentDescription = "",
                            modifier = Modifier.size(((height + width) * .1).dp).padding(10.dp)
                        )

                        Column(modifier = Modifier.fillMaxWidth().padding(start = 10.dp)) {
                            Text(
                                pizzaModel.pizzaName,
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 25.sp
                            )

                            Text(
                                text = pizzaModel.pizzaSize.find { it.default }?.pizzaSize ?: "",
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 14.sp
                            )

                            if (pizzaModel.discount){
                                PizTDiscountText(
                                    pizzaModel.pizzaPrice,
                                    pizzaModel.discountPrice
                                )
                            }else{
                                Text(
                                    text = "${pizzaModel.pizzaPrice}₺",
                                    color = MaterialTheme.colorScheme.primary,
                                    fontSize = 14.sp
                                )
                            }

                            Button(onClick = {
                                val navigationJson = pizzaMenuViewModel.pizzaDetailPageJson(pizzaModel, image) ?: ""
                                rememberNavHost.navigate("${ERoute.PIZZA_DETAIL_PAGE}/$navigationJson")
                            }, modifier = Modifier.fillMaxWidth() ){
                                Text("Add")
                            }
                        }
                    }
                }
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun PreviewMenuPage() {
    PizTaurantTheme {
        PizzaMenuPage(rememberNavHost = rememberNavController())
    }
}