package com.example.domain

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Language(
    @SerializedName("iso_639_1") @PrimaryKey val language: String,
    @SerializedName("name") val name: String
): Serializable