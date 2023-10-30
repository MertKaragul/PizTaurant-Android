package com.mertkaragul.piztaurant.View.Cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mertkaragul.piztaurant.Viewmodel.CartViewModel
import com.mertkaragul.piztaurant.ui.theme.PizTaurantTheme

@Composable
fun CartPage(
    cartViewModel : CartViewModel = viewModel()
) {
    val height = LocalConfiguration.current.screenHeightDp
    val width = LocalConfiguration.current.screenWidthDp
    val listState = rememberLazyListState()
    val cartModel by cartViewModel.cartModelList.observeAsState()

    if (cartModel != null && !cartModel.isNullOrEmpty()){
        LazyColumn(modifier = Modifier.fillMaxSize(), state = listState){
            items(cartModel!!){
                CartItem(cartViewModel,it,height,width)
            }
        }
    }else{
        CartEmpty()
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCartPage() {
    PizTaurantTheme {
        CartPage()
    }
}