package com.mertkaragul.piztaurant.Service.File

import android.content.Context
import android.util.Log
import java.io.File
import java.io.FileNotFoundException
import kotlin.jvm.Throws

class File {
    companion object{
        const val PIZZA_FILE_NAME = "pizza-data.json"
    }
    @Throws
    fun readFileFromAssets(path : String, context : Context): String {
        val json = context.assets.open(path).bufferedReader().use {
            it.readText()
        }
        return json
    }
}