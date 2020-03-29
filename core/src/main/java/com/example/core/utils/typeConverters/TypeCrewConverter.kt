package com.example.core.utils.typeConverters

import androidx.room.TypeConverter
import com.example.domain.Crew
import com.example.domain.Genres
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable

class TypeCrewConverter :Serializable {

    @TypeConverter
    fun fromString(value: String?): List<Crew>? {
        val listType = object : TypeToken<List<Crew>?>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<Crew>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

}