package com.mertkaragul.piztaurant.Service.DatabaseService

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.mertkaragul.piztaurant.Model.DatabaseModels.CartModel
import com.mertkaragul.piztaurant.Model.Pizza.OrderPizzaModel
import com.mertkaragul.piztaurant.Model.Pizza.PizzaDetailModel
import com.mertkaragul.piztaurant.Service.JsonService.JsonService
import com.mertkaragul.piztaurant.Service.JsonService.JsonService.Companion.CART_MODEL_TYPE_TOKEN
import com.mertkaragul.piztaurant.Service.JsonService.JsonService.Companion.ORDER_PIZZA_MODEL_TYPE_TOKEN
import com.mertkaragul.piztaurant.Service.JsonService.JsonService.Companion.PIZZA_DETAIL_MODEL_TYPE_TOKEN

class TypeConverters  {
    private val json = JsonService()
    @TypeConverter
    fun orderPizzaModelToString(orderPizzaModel: OrderPizzaModel) : String{
        return json.convertJson(orderPizzaModel)
    }
    @TypeConverter
    fun cartModelToString(cartModel: CartModel) : String{
        return json.convertJson(cartModel)
    }

    @TypeConverter
    fun detailPizzaModelToString(pizzaDetailModel: PizzaDetailModel) : String{
        return json.convertJson(pizzaDetailModel)
    }

    @TypeConverter
    fun toStringPizzaDetailModel(string: String) : PizzaDetailModel{
        return json.convertJsonToClass(string, PIZZA_DETAIL_MODEL_TYPE_TOKEN)
    }

    @TypeConverter
    fun toOrderPizzaModel(string: String) : OrderPizzaModel{
        return json.convertJsonToClass(string, ORDER_PIZZA_MODEL_TYPE_TOKEN)
    }
    @TypeConverter
    fun toCartModel(string: String) : CartModel{
        return json.convertJsonToClass(string, CART_MODEL_TYPE_TOKEN)
    }

}