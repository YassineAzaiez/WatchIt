package com.example.domain

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Country(
    @SerializedName("iso_3166_1")  val country: String,
    @SerializedName("title") val name: String
): Serializable