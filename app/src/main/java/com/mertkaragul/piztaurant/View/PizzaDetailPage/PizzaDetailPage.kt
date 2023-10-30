package com.mertkaragul.piztaurant.View.PizzaDetailPage

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mertkaragul.piztaurant.Enum.EInformationStatus
import com.mertkaragul.piztaurant.Enum.ERoute
import com.mertkaragul.piztaurant.Model.DatabaseModels.CartModel
import com.mertkaragul.piztaurant.Model.InformationModel.InformationModel
import com.mertkaragul.piztaurant.Model.Pizza.OrderPizzaModel
import com.mertkaragul.piztaurant.View.Elements.PizTAlertDialog
import com.mertkaragul.piztaurant.View.Elements.PizTDefaultSpacerHeight
import com.mertkaragul.piztaurant.View.Elements.PizTTextField
import com.mertkaragul.piztaurant.Viewmodel.PizzaDetailPageViewModel
import com.mertkaragul.piztaurant.ui.theme.PizTaurantTheme

@Composable
fun PizzaDetailPage(
    navHostController: NavHostController,
    pizzaDetailJson : String?,
    pizzaDetailPageViewModel: PizzaDetailPageViewModel = viewModel()
) {
    if (pizzaDetailJson == null) navHostController.navigate(ERoute.PIZZA_MENU_PAGE.toString())

    var showSelectableFeatures by remember { mutableStateOf(false)  }
    var selectPastry by remember { mutableStateOf(false)  }

    var information by remember { mutableStateOf(InformationModel("Hata" , "Bir şeyler ters gitti lütfen tekrar deneyiniz", EInformationStatus.ERROR))  }
    var showInformation by remember {  mutableStateOf(false) }

    val height = LocalConfiguration.current.screenHeightDp
    val width = LocalConfiguration.current.screenWidthDp


    val pizzaDetailModel = pizzaDetailPageViewModel.pizzaDetailStringConvertModel(pizzaDetailJson)
    if (pizzaDetailModel == null) {
        navHostController.navigate(ERoute.PIZZA_MENU_PAGE.toString())
    }else{
        var getDefaultPizzaSpecials = pizzaDetailPageViewModel.defaultPizzaSpecials.observeAsState()
        var totalPrice = pizzaDetailPageViewModel.defaultPrice.observeAsState()

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                PizzaDetailHeader(
                    pizzaDetailModel.image,
                    pizzaDetailModel.pizzaModel.pizzaName
                )


                Text(
                    text = "Malzemeler",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(5.dp)
                )

                Text(
                    text = pizzaDetailModel.pizzaModel.pizzaIngredients,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(5.dp)
                )

                PizTDefaultSpacerHeight()

                PizTTextField(it = getDefaultPizzaSpecials.value?.pizzaPastry?.pastryName ?: "",
                    onValueChange = {},
                    placeholder = "Pizza hamuru seç",
                    errorMessage = "",
                    enabled = false,
                    modifier = Modifier.clickable {
                        selectPastry = true
                        showSelectableFeatures = !showSelectableFeatures
                    }
                )

                PizTDefaultSpacerHeight()

                PizTTextField(it = getDefaultPizzaSpecials.value?.pizzaSizeElement?.pizzaSize ?: "",
                    onValueChange = {},
                    placeholder = "Pizza boyutu seç",
                    errorMessage = "",
                    enabled = false,
                    modifier = Modifier.clickable {
                        selectPastry = false
                        showSelectableFeatures = !showSelectableFeatures
                    }
                )
            }

            Button(onClick = {
                if (totalPrice.value != null){
                    pizzaDetailPageViewModel.orderPizza( totalPrice.value ?: 0 ){
                        information = it
                        showInformation = true
                    }
                }
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .height((height * .09).dp),
                shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
            ) {
                Text("Sipariş ver : ${totalPrice.value}₺")
            }

            AnimatedVisibility(visible = showSelectableFeatures,
                enter = slideInVertically { +it },
                exit = slideOutVertically { it }
            ) {
                PizzaSelectionFeatures(
                    selectablePizzaPastry = pizzaDetailPageViewModel.defaultPizzaPastryList,
                    selectablePizzaSize = pizzaDetailPageViewModel.defaultPizzaSizeList,
                    selectPizzaPastry = selectPastry,
                    selectedPastry = { selectedPastry ->
                        pizzaDetailPageViewModel.updatePizzaPastry( selectedPastry )
                    },
                    selectedSize =  {
                        pizzaDetailPageViewModel.updatePizzaSize( it )
                    },
                    onDismissReq = {
                        showSelectableFeatures = it
                    }
                )
            }
        }


        /** Just one time working **/
        LaunchedEffect(key1 = Unit){
            pizzaDetailPageViewModel.defaultPizzaSpecial(pizzaDetailModel.pizzaModel,pizzaDetailModel.image){exception ->
                information = exception
                showInformation = true
            }
        }
    }





    AnimatedVisibility(visible = showInformation) {
        PizTAlertDialog(
            title = information.title,
            description = information.description ,
            confirm = { Button(onClick = { showInformation = !showInformation }) {Text("Ok")} },
            dismiss = {  },
            dismissReq = { showInformation = !showInformation }
        )
    }


}


@Preview(showBackground = true)
@Composable
fun PreviewPizzaDetailPage() {
    PizTaurantTheme {
        PizzaDetailPage(
            rememberNavController(),
            "{\"image\":2130968663,\"pizzaModel\":{\"choosePizzaPastry\":[{\"default\":true,\"pastryIngredients\":\"İnce ve hafif çıtır lezzet.\",\"pastryName\":\"İnce hamur\",\"pastryPrice\":0,\"status\":true},{\"default\":false,\"pastryIngredients\":\"Hafif ve kabarık klasik lezzet.\",\"pastryName\":\"Normal hamur\",\"pastryPrice\":4,\"status\":true}],\"discount\":false,\"discountPrice\":0,\"id\":2,\"image\":\"R.drawable.mushroom_pizza\",\"pizzaIngredients\":\"Sucuk, sosis, mantar, yeşil biber, mısır, domates, yeşil zeytin, kaşar peyniri\",\"pizzaName\":\"Mantarlı pizza\",\"pizzaPrice\":130,\"pizzaSize\":[{\"default\":true,\"pizzaSize\":\"Küçük\",\"price\":10,\"status\":true},{\"default\":false,\"pizzaSize\":\"Orta\",\"price\":15,\"status\":true},{\"default\":false,\"pizzaSize\":\"Büyük\",\"price\":35,\"status\":true}],\"status\":true}}"
        )
    }
}