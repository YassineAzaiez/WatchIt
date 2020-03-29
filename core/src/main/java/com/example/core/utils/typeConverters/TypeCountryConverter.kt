package com.example.core.utils.typeConverters

import androidx.room.TypeConverter
import com.example.domain.Cast
import com.example.domain.Country
import com.example.domain.Genres
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable

class TypeCountryConverter :Serializable {

    @TypeConverter
    fun fromString(value: String?): List<Country>? {
        val listType = object : TypeToken<List<Country>?>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<Country>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

}