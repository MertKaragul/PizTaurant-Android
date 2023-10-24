package com.mertkaragul.piztaurant.Service.DatabaseService

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.mertkaragul.piztaurant.Interface.IUser
import com.mertkaragul.piztaurant.Model.UserModel.UserModel



@Database(
    version = 2,
    entities = [UserModel::class],
    autoMigrations = [
        AutoMigration (from = 1, to = 2)
    ],
    exportSchema = true
)
abstract class DatabaseDAO : RoomDatabase() {
    abstract fun userDao() : IUser
}