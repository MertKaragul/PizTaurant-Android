package com.mertkaragul.piztaurant.Interface

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mertkaragul.piztaurant.Model.UserModel.UserModel

@Dao
interface IUser {
    @Query("SELECT * FROM UserModel")
    suspend fun getAll(): List<UserModel>

    @Query("SELECT * FROM UserModel WHERE username LIKE :first")
    suspend fun findByName(first: String): UserModel

    @Insert
    suspend fun insert(user: UserModel)

    @Update
    suspend fun update(user : UserModel)

    @Delete
    suspend fun delete(user: UserModel)
}
