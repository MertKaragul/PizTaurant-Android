package com.mertkaragul.piztaurant.Service.DatabaseService

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mertkaragul.piztaurant.Interface.IUser
import com.mertkaragul.piztaurant.Model.UserModel.UserModel


@Database(entities = [UserModel::class], version = 1)
abstract class DatabaseDAO : RoomDatabase() {
    abstract fun userDao() : IUser
}