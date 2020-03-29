package com.example.core.utils.typeConverters

import androidx.room.TypeConverter
import com.example.domain.Genres
import com.example.domain.Movie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable

class TypeMovieConverter :Serializable {

    @TypeConverter
    fun fromString(value: String?): List<Movie>? {
        val listType = object : TypeToken<List<Movie>?>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<Movie>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

}