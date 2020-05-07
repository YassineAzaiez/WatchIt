package com.example.domain

import androidx.annotation.NonNull
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by yassine 17/02/20 .
 */

@Entity(tableName = "movies")

 data class Movie (
    @PrimaryKey @NonNull
    @Expose @SerializedName("id") val id: Long = 0,
    @Expose @SerializedName("imdb_id") val imdbId: String? ="",
    @Expose @SerializedName("adult") val adult: Boolean = false,
    @Expose @SerializedName("backdrop_path") val backdropPath: String? = "",
    @Expose @SerializedName("budget") val budget: Int = 0,
    @Expose @SerializedName("genres") val genres: List<Genres>?  = emptyList<Genres>(),
    @Expose @SerializedName("homepage") val homepage: String? = "",
    @Expose @SerializedName("original_language") val originalLanguage: String ?= "",
    @Expose @SerializedName("original_title") val originalTitle: String ?= "",
    @Expose @SerializedName("overview") val overview: String? = "",
    @Expose @SerializedName("popularity") val popularity: Double = 0.0,
    @Expose @SerializedName("poster_path") val posterPath: String? ="",
    @Expose @SerializedName("production_companies") val companies: List<Company> ?= emptyList(),
    @Expose @SerializedName("production_countries") val countries: List<Country>? = emptyList(),
    @Expose @SerializedName("release_date") val releaseDate: String? = "",
    @Expose @SerializedName("revenue") val revenue: Long = 0L,
    @Expose @SerializedName("runtime") val runtime: Int ,
    @Expose @SerializedName("spoken_languages") val languages: List<com.example.domain.Language> ?= emptyList(),
    @Expose @SerializedName("status") val status: String ?= "",
    @Expose @SerializedName("tagline") val tagline: String ?= "",
    @Expose @SerializedName("title") val title: String? = "",
    @Expose @SerializedName("video") val video: Boolean = false,
    @Expose @SerializedName("vote_average") val voteAverage: Float = 0F,
    @Expose @SerializedName("vote_count") val voteCount: Int = 0,
    @Expose @SerializedName("media_type") val mediaType: String ?= "",
    @Expose @SerializedName("genre_ids") val genreIds: List<Int> ?= emptyList(),
    @Embedded @Expose @SerializedName("credits") val credits: CreditsResponse?,
    var isBookmarked: Boolean = false
) :Serializable{


    companion object {
        const val NOW_PLAYING = "now_playing"
        const val POPULAR = "popular"
        const val TOP_RATED = "top_rated"
        const val UPCOMING = "upcoming"
        const val SIMILAR = "similar"
        const val RECOMMENDATIONS = "recommendations"
    }

}