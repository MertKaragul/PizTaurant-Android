package com.mertkaragul.piztaurant.Viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.reflect.TypeToken
import com.mertkaragul.piztaurant.Enum.EInformationStatus
import com.mertkaragul.piztaurant.Model.DatabaseModels.CartModel
import com.mertkaragul.piztaurant.Model.InformationModel.InformationModel
import com.mertkaragul.piztaurant.Model.Pizza.PizzaDetailModel
import com.mertkaragul.piztaurant.Model.Pizza.ChoosePizzaPastry
import com.mertkaragul.piztaurant.Model.Pizza.OrderPizzaModel
import com.mertkaragul.piztaurant.Model.Pizza.PizzaModel
import com.mertkaragul.piztaurant.Model.Pizza.PizzaSizeElement
import com.mertkaragul.piztaurant.Service.DatabaseService.DatabaseUtil
import com.mertkaragul.piztaurant.Service.JsonService.JsonService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class PizzaDetailPageViewModel : ViewModel() {
    private val jsonService = JsonService()
    private val pizzaDetailTypeToken = object : TypeToken<PizzaDetailModel>(){}

    private val _defaultPrice = MutableLiveData<Long>(0)
    private val _defaultPizzaSpecials = MutableLiveData<OrderPizzaModel?>(null)

    val defaultPrice = _defaultPrice
    var defaultPizzaSpecials : LiveData<OrderPizzaModel?> = _defaultPizzaSpecials


    var defaultPizzaSizeList = listOf<PizzaSizeElement>()
    var defaultPizzaPastryList = listOf<ChoosePizzaPastry>()


    fun pizzaDetailStringConvertModel(json : String?): PizzaDetailModel? {
        if (json.isNullOrEmpty()) return null
        return try{
            jsonService.convertJsonToClass(json,pizzaDetailTypeToken)
        }catch (e:Exception){
            null
        }
    }

    fun defaultPizzaSpecial(pizzaModel: PizzaModel?, image : Int?, exception : (InformationModel) -> Unit){
        if (pizzaModel == null || image == null) return exception(InformationModel("Error" , "Setup failed please try again", EInformationStatus.ERROR))

        val defaultPrice = if (pizzaModel.discount) pizzaModel.discountPrice else pizzaModel.pizzaPrice
        val defaultPizzaSize = pizzaModel.pizzaSize.find { it.default }
        val defaultPizzaPastry = pizzaModel.choosePizzaPastry.find { it.default }
        _defaultPrice.value = defaultPrice

        defaultPizzaSizeList = pizzaModel.pizzaSize
        defaultPizzaPastryList = pizzaModel.choosePizzaPastry


        if (defaultPizzaPastry != null && defaultPizzaSize != null){
            _defaultPizzaSpecials.value = OrderPizzaModel(pizzaModel.pizzaName, image ,defaultPrice,defaultPizzaPastry ,defaultPizzaSize)
        }else{
            return exception(InformationModel("Hata" , "Pizza hamuru veya fiyatlandırma hatalı lütfen daha sonra tekrar deneytiniz.", EInformationStatus.ERROR))
        }
    }
    fun updatePizzaPastry(orderPizzaModel: ChoosePizzaPastry?){
        if (orderPizzaModel == null) return
        viewModelScope.launch {
            if (_defaultPizzaSpecials.value?.pizzaPastry?.pastryName != orderPizzaModel.pastryName){
                _defaultPrice.value = _defaultPrice.value!! - (_defaultPizzaSpecials.value?.pizzaPastry?.pastryPrice ?: 0)
                _defaultPrice.value = _defaultPrice.value!! + orderPizzaModel.pastryPrice
                _defaultPizzaSpecials.value?.pizzaPastry = orderPizzaModel
            }
        }
    }

    fun updatePizzaSize(orderPizzaModel: PizzaSizeElement?){
        if (orderPizzaModel == null) return
        viewModelScope.launch {
            if (_defaultPizzaSpecials.value?.pizzaSizeElement?.pizzaSize != orderPizzaModel.pizzaSize){
                _defaultPrice.value = _defaultPrice.value!! - (_defaultPizzaSpecials.value?.pizzaSizeElement?.price ?: 0)
                _defaultPrice.value = _defaultPrice.value!! + orderPizzaModel.price
                _defaultPizzaSpecials.value?.pizzaSizeElement = orderPizzaModel
            }
        }
    }


    fun orderPizza(price : Long, status : (InformationModel) -> Unit){
        viewModelScope.launch(exceptionHandler(status)) {
            val database = DatabaseUtil.database
            if (_defaultPizzaSpecials.value != null){
                database?.cartDao()?.insert(CartModel(0, _defaultPizzaSpecials.value!!, price))
                status(InformationModel("Başarılı" ,"Pizza siparişiniz alınmıştır.", EInformationStatus.SUCCESS))
            }
        }
    }

    private fun exceptionHandler(status: (InformationModel) -> Unit) = CoroutineExceptionHandler { coroutineContext, throwable ->
        status(InformationModel("Hata" , throwable.localizedMessage ?: "Bir şeyler ters gitti, lütfen tekrar deneyin", EInformationStatus.ERROR))
    }
}