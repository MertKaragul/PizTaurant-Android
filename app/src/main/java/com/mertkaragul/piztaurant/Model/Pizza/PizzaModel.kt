package com.mertkaragul.piztaurant.Model.Pizza

data class PizzaModel (
    val id: Long,
    val pizzaName: String,
    val pizzaIngredients: String,
    val image : String,
    val pizzaPrice: Long,
    val status: Boolean,
    val discount: Boolean,
    val discountPrice: Long,
    val choosePizzaPastry: List<ChoosePizzaPastry>,
    val pizzaSize: List<PizzaSizeElement>
)

data class ChoosePizzaPastry (
    val pastryName: String,
    val default: Boolean,
    val status: Boolean,
    val pastryPrice: Long,
    val pastryIngredients: String
)

data class PizzaSizeElement (
    val pizzaSize: String,
    val status: Boolean,
    val default: Boolean,
    val price: Long
)