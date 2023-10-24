package com.mertkaragul.piztaurant.Model.NavigationModel

import android.os.Parcelable
import com.mertkaragul.piztaurant.Model.Pizza.PizzaModel
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.io.Serializable

data class NavigationPizzaModel(
    val pizzaModel: PizzaModel,
    val image : Int
)
