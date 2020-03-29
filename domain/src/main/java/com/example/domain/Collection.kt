package com.example.domain

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Collection (
    @SerializedName("id") @PrimaryKey val idCol: Int = 0,
    @SerializedName("name") val name: String? = null,
    @SerializedName("overview") val overviewCol: String? = null,
    @SerializedName("poster_path") val posterPathCol: String? = null,
    @SerializedName("backdrop_path") val backdropPathCol: String? = null,
    @SerializedName("parts") val parts: List<Movie> = emptyList()
): Serializable
