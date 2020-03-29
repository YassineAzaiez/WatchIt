package com.example.core.utils.typeConverters

import androidx.room.TypeConverter
import com.example.domain.Genres
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypeIntConverter {

    @TypeConverter
    fun fromString(value: String?): List<Int>? {
        val listType = object : TypeToken<List<Int>?>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<Int>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}