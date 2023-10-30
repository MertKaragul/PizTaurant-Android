package com.mertkaragul.piztaurant.View.Cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mertkaragul.piztaurant.Model.DatabaseModels.CartModel
import com.mertkaragul.piztaurant.View.Elements.PizTCustomSpacerWidth
import com.mertkaragul.piztaurant.Viewmodel.CartViewModel

@Composable
fun CartItem(cartViewModel: CartViewModel,cartItem : CartModel, height: Int,width : Int) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)) {
        Row{
            Image(
                painterResource(id = cartItem.orderPizzaModel.pizzaImage),
                contentDescription = "",
                modifier = Modifier.size(((height + width) * .1).dp).padding(10.dp)
            )

            PizTCustomSpacerWidth(size = .05)

            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.SpaceBetween) {
                Text(
                    cartItem.orderPizzaModel.pizzaName,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 25.sp
                )

                Text(
                    text = cartItem.orderPizzaModel.pizzaSizeElement.pizzaSize,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 14.sp
                )

                Button(onClick = { cartViewModel.deletePizzaOrder(cartItem) }, modifier = Modifier.fillMaxWidth() ) {
                    Text(
                        text = "Siparişi iptal et",
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }

            }
        }
    }
}