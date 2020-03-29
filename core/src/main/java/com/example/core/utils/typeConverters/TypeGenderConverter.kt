package com.example.core.utils.typeConverters

import androidx.room.TypeConverter
import com.example.domain.Genres
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable

class TypeGenderConverter :Serializable {

    @TypeConverter
    fun fromString(value: String?): List<Genres>? {
        val listType = object : TypeToken<List<Genres>?>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<Genres>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

}