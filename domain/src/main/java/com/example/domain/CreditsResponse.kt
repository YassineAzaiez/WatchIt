package com.example.domain

import androidx.annotation.NonNull
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CreditsResponse (
    @SerializedName("id")  val creditId: Int = 0,
    @SerializedName("cast") val cast: List<Cast>?= emptyList(),
    @SerializedName("crew") val crew: List<Crew>?= emptyList()

 ) : Serializable