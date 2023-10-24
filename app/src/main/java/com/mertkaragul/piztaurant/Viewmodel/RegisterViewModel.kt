package com.mertkaragul.piztaurant.Viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertkaragul.piztaurant.Model.UserModel.UserModel
import com.mertkaragul.piztaurant.Service.DatabaseService.DatabaseUtil.database
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.jvm.Throws

class RegisterViewModel : ViewModel() {
    @Throws
    fun createAccount(userModel: UserModel?){
        if (userModel == null) throw Exception("Please check form, one form input sending empty")
        viewModelScope.launch {
            database?.userDao()?.insert(userModel)
        }
    }

    @Throws
    fun checkAccount(username : String?, getUser : (UserModel) -> Unit){
        if(username.isNullOrEmpty()) throw Exception("Username cannot be empty")
        viewModelScope.launch {
            val userModel = database?.userDao()?.findByName(username)
            if (userModel != null) {
                getUser(userModel)
            }
        }
    }
}