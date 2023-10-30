package com.mertkaragul.piztaurant.Service.DatabaseService

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mertkaragul.piztaurant.Interface.ICart
import com.mertkaragul.piztaurant.Interface.IUser
import com.mertkaragul.piztaurant.Model.DatabaseModels.CartModel
import com.mertkaragul.piztaurant.Model.DatabaseModels.UserModel



@Database(
    version = 1,
    entities = [UserModel::class, CartModel::class],
    exportSchema = true
)
@TypeConverters(com.mertkaragul.piztaurant.Service.DatabaseService.TypeConverters::class)
abstract class DatabaseDAO : RoomDatabase() {
    abstract fun userDao() : IUser
    abstract fun cartDao() : ICart
}