package com.example.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Crew (
    @SerializedName("credit_id") val creditId: String?="",
    @SerializedName("department") val department : String?="",
    @SerializedName("gender") val gender : Int = 0,
    @SerializedName("id") val id : Int = 0,
    @SerializedName("job") val job : String? = "",
    @SerializedName("name") val name : String? = "",
    @SerializedName("profile_path") val profilePath : String? = ""
) : Serializable









