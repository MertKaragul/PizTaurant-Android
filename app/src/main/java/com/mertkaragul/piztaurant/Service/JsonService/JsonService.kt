package com.mertkaragul.piztaurant.Service.JsonService

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mertkaragul.piztaurant.Model.DatabaseModels.CartModel
import com.mertkaragul.piztaurant.Model.Pizza.OrderPizzaModel
import com.mertkaragul.piztaurant.Model.Pizza.PizzaDetailModel
import java.lang.reflect.Type

class JsonService {
    private val gson = Gson()
    companion object {
        val ORDER_PIZZA_MODEL_TYPE_TOKEN = object : TypeToken<OrderPizzaModel>(){}
        val CART_MODEL_TYPE_TOKEN = object : TypeToken<CartModel>(){}
        val PIZZA_DETAIL_MODEL_TYPE_TOKEN = object : TypeToken<PizzaDetailModel>(){}
    }

    fun <T> convertJsonToClass(data : String, typeToken: TypeToken<T>): T {
        return gson.fromJson(data,typeToken)
    }


    fun convertJson(data : Any) = gson.toJson(data)
}