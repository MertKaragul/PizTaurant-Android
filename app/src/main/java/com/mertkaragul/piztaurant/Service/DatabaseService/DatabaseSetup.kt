package com.mertkaragul.piztaurant.Service.DatabaseService

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mertkaragul.piztaurant.R

class DatabaseSetup {
    fun setupDatabase(context : Context): DatabaseDAO {
        return Room.databaseBuilder(context, DatabaseDAO::class.java ,name = context.getString(R.string.app_name))
            .fallbackToDestructiveMigration()
            .build()
    }
}