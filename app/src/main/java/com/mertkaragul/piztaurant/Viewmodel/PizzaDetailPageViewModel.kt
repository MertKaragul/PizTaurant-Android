package com.mertkaragul.piztaurant.Viewmodel

import androidx.lifecycle.ViewModel
import com.google.gson.reflect.TypeToken
import com.mertkaragul.piztaurant.Model.NavigationModel.NavigationPizzaModel
import com.mertkaragul.piztaurant.Service.JsonService.JsonService

class PizzaDetailPageViewModel : ViewModel() {
    private val jsonService = JsonService()
    private val pizzaDetailTypeToken = object : TypeToken<NavigationPizzaModel>(){}

    fun pizzaDetailStringConvertModel(json : String?): NavigationPizzaModel? {
        if (json.isNullOrEmpty()) return null
        return try{
            jsonService.convertJsonToClass(json,pizzaDetailTypeToken)
        }catch (e:Exception){
            null
        }
    }
}