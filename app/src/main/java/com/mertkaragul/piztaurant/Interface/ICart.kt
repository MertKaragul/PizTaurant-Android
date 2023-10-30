package com.mertkaragul.piztaurant.Interface

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mertkaragul.piztaurant.Model.DatabaseModels.CartModel
import com.mertkaragul.piztaurant.Model.DatabaseModels.UserModel

@Dao
interface ICart {
    @Query("SELECT * FROM CartModel")
    suspend fun getAll(): MutableList<CartModel>

    @Insert
    suspend fun insert(cartModel: CartModel)

    @Update
    suspend fun update(cartModel : CartModel)

    @Delete
    suspend fun delete(cartModel: CartModel)
}