package com.mertkaragul.piztaurant.Service.JsonService

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class JsonService {
    private val gson = Gson()
    fun <T> convertJsonToClass(data : String, typeToken: TypeToken<T>): T {
        return gson.fromJson(data,typeToken)
    }

    fun convertJson(data : Any) = gson.toJson(data)
}