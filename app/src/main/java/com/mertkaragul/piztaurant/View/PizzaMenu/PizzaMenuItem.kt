package com.mertkaragul.piztaurant.View.PizzaMenu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import com.mertkaragul.piztaurant.Enum.ERoute
import com.mertkaragul.piztaurant.Model.Pizza.PizzaImageModel
import com.mertkaragul.piztaurant.Model.Pizza.PizzaModel
import com.mertkaragul.piztaurant.R
import com.mertkaragul.piztaurant.View.Elements.PizTDiscountText
import com.mertkaragul.piztaurant.Viewmodel.PizzaMenuViewModel

@Composable
fun PizzaMenuItem(pizzaMenuViewModel : PizzaMenuViewModel, pizzaModel : PizzaModel, image : Int, height : Int, width : Int, rememberNavHost : NavHostController) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)) {
        Row{
            Image(
                painterResource(id = image),
                contentDescription = "",
                modifier = androidx.compose.ui.Modifier.size(((height + width) * .1).dp).padding(10.dp)
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
                    Text("Ekle")
                }
            }
        }
    }
}