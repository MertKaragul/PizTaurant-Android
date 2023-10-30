package com.mertkaragul.piztaurant.Viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertkaragul.piztaurant.Model.DatabaseModels.UserModel
import com.mertkaragul.piztaurant.Service.DatabaseService.DatabaseUtil.database
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _userLiveData = MutableLiveData<UserModel?>(null)
    val user = _userLiveData

    init {
        checkUser()
    }

    fun checkUser(){
        viewModelScope.launch {
            if (!database?.userDao()?.getAll().isNullOrEmpty()){
                _userLiveData.value = database?.userDao()?.getAll()?.first()
            }
        }
    }
}