package com.mertkaragul.piztaurant.Model.DatabaseModels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.mertkaragul.piztaurant.Model.Pizza.OrderPizzaModel
import com.mertkaragul.piztaurant.Model.Pizza.PizzaDetailModel
import com.mertkaragul.piztaurant.Model.Pizza.PizzaModel

@Entity
data class CartModel (
    @PrimaryKey(autoGenerate = true) val uuid : Long = 0,
    val orderPizzaModel: OrderPizzaModel,
    val totalPrice : Long
)