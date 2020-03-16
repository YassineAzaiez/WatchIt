package com.example.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Dates(
    @SerializedName("minimum") val minimumDate: String,
    @SerializedName("maximum") val maximumDate: String
): Serializable