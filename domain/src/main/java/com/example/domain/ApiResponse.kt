package com.example.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by yassine 17/02/20 .
 */

data class ApiResponse<T>(
    @Expose @SerializedName("id") val id: Int = 0,
    @Expose @SerializedName("page") val page: Int = 0,
    @Expose @SerializedName("results") val results: List<T> = emptyList(),
    @Expose @SerializedName("total_pages") val totalPages: Int = 0,
    @Expose @SerializedName("total_results") val totalResults: Int = 0,
    @Expose @SerializedName("dates") val dates: Dates? = null
)