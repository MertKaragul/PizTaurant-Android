package com.mertkaragul.piztaurant.Viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertkaragul.piztaurant.Model.DatabaseModels.CartModel
import com.mertkaragul.piztaurant.Model.InformationModel.InformationModel
import com.mertkaragul.piztaurant.Model.Pizza.OrderPizzaModel
import com.mertkaragul.piztaurant.Model.Pizza.PizzaImageModel
import com.mertkaragul.piztaurant.Service.DatabaseService.DatabaseUtil
import com.mertkaragul.piztaurant.Service.File.File
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {
    private val database = DatabaseUtil.database
    private val _cartModelList = MutableLiveData<MutableList<CartModel>>(mutableListOf())
    val cartModelList = _cartModelList

    init {
        getPizzaOrders()
    }

    fun getPizzaOrders(){
        viewModelScope.launch {
            _cartModelList.value = mutableListOf()
            _cartModelList.value = database?.cartDao()?.getAll()
        }
    }

    fun deletePizzaOrder(cartModel: CartModel){
        viewModelScope.launch {
            database?.cartDao()?.delete(cartModel)
            delay(1000L)
            getPizzaOrders()
        }
    }
}