package com.example.core.utils.typeConverters

import androidx.room.TypeConverter
import com.example.domain.Cast
import com.example.domain.Genres
import com.example.domain.Language
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable

class TypeLanguageConverter :Serializable {

    @TypeConverter
    fun fromString(value: String?): List<Language>? {
        val listType = object : TypeToken<List<Language>?>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<Language>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

}