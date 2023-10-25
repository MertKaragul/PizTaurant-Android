package com.mertkaragul.piztaurant.View.PizzaDetailPage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mertkaragul.piztaurant.Model.Pizza.ChoosePizzaPastry
import com.mertkaragul.piztaurant.Model.Pizza.PizzaSizeElement
import com.mertkaragul.piztaurant.Viewmodel.PizzaDetailPageViewModel
import com.mertkaragul.piztaurant.ui.theme.PizTaurantTheme

@Composable
fun PizzaSelectionFeatures(
    selectablePizzaPastry : List<ChoosePizzaPastry>?,
    selectablePizzaSize : List<PizzaSizeElement>?,
    selectPizzaPastry : Boolean,

    selectedPastry: (ChoosePizzaPastry) -> Unit,
    selectedSize : (PizzaSizeElement) -> Unit,

    onDismissReq : (Boolean) -> Unit
) {
    if (selectablePizzaPastry.isNullOrEmpty() || selectablePizzaSize.isNullOrEmpty()) return
    val height = LocalConfiguration.current.screenHeightDp

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ){
        LazyColumn(modifier = Modifier
            .clip(RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
        ){
            if (selectPizzaPastry){
                items(selectablePizzaPastry){
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)) {
                        if (it.status){
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceAround) {
                                Text(
                                    it.pastryName,
                                    fontSize = 35.sp,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                                Text(
                                    text = "${it.pastryPrice}₺",
                                    fontSize = 23.sp,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                            }
                            Text(
                                text = it.pastryIngredients,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.secondary,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Button(
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.onSecondaryContainer
                                ),
                                onClick = {
                                    selectedPastry(it)
                                    onDismissReq(false)
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    "Seç",
                                    color = MaterialTheme.colorScheme.onSecondary
                                )
                            }
                        }
                    }
                }
            }else {
                items(selectablePizzaSize){
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)) {
                        if(it.status){
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceAround) {
                                Text(
                                    it.pizzaSize,
                                    fontSize = 35.sp,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                                Text(
                                    text = "${it.price}₺",
                                    fontSize = 23.sp,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                            }

                            Button(
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.onSecondaryContainer
                                ),
                                onClick = { selectedSize(it)
                                    onDismissReq(false) },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    "Seç",
                                    color = MaterialTheme.colorScheme.onSecondary
                                )
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
fun PreviewPizzaSelectionFeatures() {
    PizTaurantTheme {
        val pizzaSize = listOf(
            PizzaSizeElement(
                "Small",
                true,
                false,
                100L
            )
        )

        val pizzaPastry = listOf(
            ChoosePizzaPastry(
                "İnce hamur",
                status = true,
                default = false,
                pastryIngredients = "Hafif ve kabarık klasik lezzet.",
                pastryPrice = 50
            )
        )
        PizzaSelectionFeatures(pizzaPastry, pizzaSize , selectPizzaPastry = true,  selectedPastry = {} , selectedSize = {}, onDismissReq = {})

    }
}