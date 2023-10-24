package com.mertkaragul.piztaurant.Model.UserModel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class UserModel(
    @PrimaryKey
    val id : Int = 0,
    @ColumnInfo(defaultValue = "") val username : String,
    @ColumnInfo(defaultValue = "") val email : String,
    @ColumnInfo(defaultValue = "")  val password : String
)
