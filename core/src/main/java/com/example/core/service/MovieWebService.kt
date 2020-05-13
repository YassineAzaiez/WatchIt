package com.example.core.service

import com.example.domain.ApiResponse
import com.example.domain.Movie
import com.example.domain.Review
import com.example.domain.Video
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by yassine 20/02/20 .
 */
interface MovieWebService {

    //Movies

    @GET("search/movie?")
    fun searchMovies(
        @Query("language") lang: String,
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("include_adult") adult: Boolean
    ): Deferred<Response<ApiResponse<Movie>>>

    @GET("movie/{movie_id}/reviews")
    fun reviews(
        @Path("movie_id") id: Long,
        @Query("api_key") apiKey: String,
        //@Query("language") language: String,
        @Query("page") page: Int
    ): Deferred<Response<ApiResponse<Review>>>


    @GET("movie/{list}")
    fun loadMovies(
        @Path("list") list: String,
        @Query("language") language: String,
        @Query("page") page: Int
    )
            : Deferred<Response<ApiResponse<Movie>>>


    @GET("movie/{movie_id}")
    fun loadMovieById(
        @Path("movie_id") id: Long,
        @Query("language") language: String,
        @Query("append_to_response") appendToResponse: String
    ): Deferred<Response<Movie>>


    @GET("movie/{movie_id}/videos")
    fun loadMovieTrailers(
        @Path("movie_id") id: Long,
        @Query("language") lang: String
    ): Deferred<Response<ApiResponse<Video>>>


    @GET("movie/{movie_id}/recommendations")
    fun loadRecomandedMovies(
        @Path("movie_id") id: Long
        , @Query("page") page: Int
    ): Deferred<Response<ApiResponse<Movie>>>


}