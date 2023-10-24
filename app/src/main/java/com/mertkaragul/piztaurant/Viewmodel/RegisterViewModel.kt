package com.mertkaragul.piztaurant.Viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertkaragul.piztaurant.Model.InformationModel.InformationModel
import com.mertkaragul.piztaurant.Model.UserModel.UserModel
import com.mertkaragul.piztaurant.Service.DatabaseService.DatabaseUtil.database
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.jvm.Throws

class RegisterViewModel : ViewModel() {
    fun createAccount(username : String?, email : String?, password : String? , exceptions : (InformationModel) -> Unit){
        if (username.isNullOrEmpty() || email.isNullOrEmpty() || password.isNullOrEmpty()) return exceptions(InformationModel("Error", "Username, email, password cannot be empty"))

        viewModelScope.launch(exceptionHandler(exceptions)) {
            database?.userDao()?.insert(UserModel(Math.random().toInt(), username,email,password))
            delay(500L)
            val user = database?.userDao()?.findByName(username)
            if (user != null){
                exceptions(InformationModel("Success", "Account successfully created"))
            }else{
                exceptions(InformationModel("Error", "Account cannot be created, please try again"))
            }
        }
    }

    private fun exceptionHandler(exceptions : (InformationModel) -> Unit) = CoroutineExceptionHandler { coroutineContext, throwable ->
        throwable.localizedMessage?.let { exceptions(InformationModel("Error", it)) }
        throwable.printStackTrace()
    }
}