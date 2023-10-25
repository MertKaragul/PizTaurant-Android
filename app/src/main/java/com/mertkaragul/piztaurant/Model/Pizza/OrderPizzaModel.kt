package com.mertkaragul.piztaurant.Model.Pizza

data class OrderPizzaModel(
    var pizzaName : String,
    val pizzaImage : Int,
    val pizzaPrice : Long,
    var pizzaPastry: ChoosePizzaPastry,
    var pizzaSizeElement: PizzaSizeElement
)
