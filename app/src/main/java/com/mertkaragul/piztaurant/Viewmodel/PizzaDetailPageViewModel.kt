package com.mertkaragul.piztaurant.Viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.reflect.TypeToken
import com.mertkaragul.piztaurant.Model.InformationModel.InformationModel
import com.mertkaragul.piztaurant.Model.NavigationModel.NavigationPizzaModel
import com.mertkaragul.piztaurant.Model.Pizza.ChoosePizzaPastry
import com.mertkaragul.piztaurant.Model.Pizza.OrderPizzaModel
import com.mertkaragul.piztaurant.Model.Pizza.PizzaModel
import com.mertkaragul.piztaurant.Model.Pizza.PizzaSizeElement
import com.mertkaragul.piztaurant.Service.JsonService.JsonService
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PizzaDetailPageViewModel : ViewModel() {
    private val jsonService = JsonService()
    private val pizzaDetailTypeToken = object : TypeToken<NavigationPizzaModel>(){}


    private val _defaultPrice = MutableLiveData<Long>(0)
    private val _defaultPizzaSpecials = MutableLiveData<OrderPizzaModel?>(null)

    val defaultPrice = _defaultPrice
    var defaultPizzaSpecials : LiveData<OrderPizzaModel?> = _defaultPizzaSpecials


    var defaultPizzaSizeList = listOf<PizzaSizeElement>()
    var defaultPizzaPastryList = listOf<ChoosePizzaPastry>()


    fun pizzaDetailStringConvertModel(json : String?): NavigationPizzaModel? {
        if (json.isNullOrEmpty()) return null
        return try{
            jsonService.convertJsonToClass(json,pizzaDetailTypeToken)
        }catch (e:Exception){
            null
        }
    }

    fun defaultPizzaSpecial(pizzaModel: PizzaModel?, image : Int?, exception : (InformationModel) -> Unit){
        if (pizzaModel == null || image == null) return exception(InformationModel("Error" , "Setup failed please try again"))

        val defaultPrice = if (pizzaModel.discount) pizzaModel.discountPrice else pizzaModel.pizzaPrice
        val defaultPizzaSize = pizzaModel.pizzaSize.find { it.default }
        val defaultPizzaPastry = pizzaModel.choosePizzaPastry.find { it.default }
        _defaultPrice.value = defaultPrice

        defaultPizzaSizeList = pizzaModel.pizzaSize
        defaultPizzaPastryList = pizzaModel.choosePizzaPastry


        if (defaultPizzaPastry != null && defaultPizzaSize != null){
            _defaultPizzaSpecials.value = OrderPizzaModel(pizzaModel.pizzaName, image ,defaultPrice,defaultPizzaPastry ,defaultPizzaSize)
        }else{
            return exception(InformationModel("Error" , "Pizza pastry and pizza price error please try again"))
        }
    }

    fun updatePizzaPastry(orderPizzaModel: ChoosePizzaPastry?){
        if (orderPizzaModel == null) return
        viewModelScope.launch {
            _defaultPizzaSpecials.value?.pizzaPastry = orderPizzaModel
        }
    }

    fun updatePizzaSize(orderPizzaModel: PizzaSizeElement?){
        if (orderPizzaModel == null) return
        viewModelScope.launch {
            _defaultPizzaSpecials.value?.pizzaSizeElement = orderPizzaModel
        }
    }
}