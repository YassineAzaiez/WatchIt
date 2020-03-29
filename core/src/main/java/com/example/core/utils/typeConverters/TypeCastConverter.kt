package com.example.core.utils.typeConverters

import androidx.room.TypeConverter
import com.example.domain.Cast
import com.example.domain.Genres
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable

class TypeCastConverter :Serializable {

    @TypeConverter
    fun fromString(value: String?): List<Cast>? {
        val listType = object : TypeToken<List<Cast>?>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<Cast>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

}