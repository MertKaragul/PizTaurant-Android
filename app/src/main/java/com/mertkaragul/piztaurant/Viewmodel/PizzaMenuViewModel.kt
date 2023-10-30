package com.mertkaragul.piztaurant.Viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.reflect.TypeToken
import com.mertkaragul.piztaurant.Model.Pizza.PizzaDetailModel
import com.mertkaragul.piztaurant.Model.Pizza.PizzaImageModel
import com.mertkaragul.piztaurant.Model.Pizza.PizzaModel
import com.mertkaragul.piztaurant.Service.File.File
import com.mertkaragul.piztaurant.Service.File.File.Companion.PIZZA_FILE_NAME
import com.mertkaragul.piztaurant.Service.JsonService.JsonService

class PizzaMenuViewModel : ViewModel() {
    private val _pizzaListLiveData = MutableLiveData<MutableList<PizzaModel>>(mutableListOf())
    private val _pizzaImageLiveData = MutableLiveData<MutableList<PizzaImageModel>>(mutableListOf())
    val pizzaList = _pizzaListLiveData
    val pizzaImages = _pizzaImageLiveData

    private val jsonService = JsonService()
    private val fileService = File()
    private val typeToken = object : TypeToken<List<PizzaModel>>(){}
    private val navPizzaToken = object : TypeToken<PizzaDetailModel>(){}
    fun getPizza(context : Context){
        val json = fileService.readFileFromAssets(PIZZA_FILE_NAME,context)
        val pizzaList = jsonService.convertJsonToClass(data = json, typeToken)
        val convertPizzaListToModel = try {
            pizzaList as MutableList<PizzaModel>
        }catch (e:Exception){
            mutableListOf<PizzaModel>()
        }
        _pizzaListLiveData.value = convertPizzaListToModel
        getPizzaImage(context)
    }

    private fun getPizzaImage(context : Context){
        _pizzaListLiveData.value?.forEach {
            _pizzaImageLiveData
                .value
            ?.add(PizzaImageModel(it.pizzaName, context.resources.getIdentifier(it.image.split(".").last(),"drawable", context.packageName)))
        }
    }

    fun pizzaDetailPageJson(pizzaModel: PizzaModel , image : Int): String? {
        return jsonService.convertJson(PizzaDetailModel(pizzaModel, image))
    }
}