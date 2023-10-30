package com.mertkaragul.piztaurant.Viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertkaragul.piztaurant.Enum.EInformationStatus
import com.mertkaragul.piztaurant.Model.InformationModel.InformationModel
import com.mertkaragul.piztaurant.Model.DatabaseModels.UserModel
import com.mertkaragul.piztaurant.Service.DatabaseService.DatabaseUtil.database
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    fun createAccount(username : String?, email : String?, password : String? , exceptions : (InformationModel) -> Unit){
        if (username.isNullOrEmpty() || email.isNullOrEmpty() || password.isNullOrEmpty()) return exceptions(InformationModel("Hata", "Kullanıcı adı, şifre veya email boş olamaz", EInformationStatus.ERROR))

        viewModelScope.launch(exceptionHandler(exceptions)) {
            database?.userDao()?.insert(UserModel(Math.random().toInt(), username,email,password))
            delay(500L)
            val user = database?.userDao()?.findByName(username)
            if (user != null){
                exceptions(InformationModel("Başarılı", "Hesap başarıyla oluşturuldu", EInformationStatus.SUCCESS))
            }else{
                exceptions(InformationModel("Hata", "Hesap oluşturulamadı, lütfen tekrar deneyiniz", EInformationStatus.ERROR))
            }
        }
    }

    private fun exceptionHandler(exceptions : (InformationModel) -> Unit) = CoroutineExceptionHandler { coroutineContext, throwable ->
        throwable.localizedMessage?.let { exceptions(InformationModel("Hata", it, EInformationStatus.ERROR)) }
        throwable.printStackTrace()
    }
}