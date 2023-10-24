package com.mertkaragul.piztaurant.Model.UserModel

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class UserModel(
    @PrimaryKey
    val id : Int = 0,
    val username : String,
    val email : String,
    val password : String
)
