package com.example.core.service

import com.example.domain.*
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by yassine 20/02/20 .
 */
interface MovieDbService {

    //Movies

    @GET("search/movie?")
    fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("language") lang: String,
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("include_adult") adult: Boolean,
        @Query("region") region: String
    ): Deferred<Response<ApiResponse<Movie>>>

    @GET("movie/{movie_id}/reviews")
    fun reviews(
        @Path("movie_id") id: Long,
        @Query("api_key") apiKey: String,
        //@Query("language") language: String,
        @Query("page") page: Int
    ): Deferred<Response<ApiResponse<Review>>>



    @GET("movie/{list}")
    fun loadMovies (@Path("list")list : String ,
                    @Query("language") language : String,
                    @Query("page") page : Int)
            : Deferred<Response<ApiResponse<Movie>>>



    @GET("movie/{movie_id}")
    fun loadMovieById(
        @Path("movie_id") id: Long,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("append_to_response") appendToResponse: String
    ): Deferred<Response<ApiResponse<Movie>>>

    @GET("movie/{movie_id}/keywords")
    fun loadMovieKeywords(
        @Path("movie_id") id: Long,
        @Query("api_key") apiKey: String
    ): Deferred<Response<KeywordsResponse>>

    @GET("movie/{movie_id}/videos")
    fun loadMovieTrailers(
        @Path("movie_id") id: Long,
        @Query("api_key") apiKey: String
        //@Query("language") lang: String
    ): Deferred<Response<ApiResponse<Video>>>


    @GET("movie/{movie_id}/recommendations")
    fun loadRecomandedMovies(@Path("movie_id") id : Int
                             , @Query("page") page : Int): Deferred<Response<ApiResponse<Movie>>>

    @GET("keyword/{keyword_id}/movies")
    fun moviesByKeyword(
        @Path("keyword_id") id: Long,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("include_adult") adult: Boolean,
        @Query("page") page: Int
    ): Deferred<Response<ApiResponse<Movie>>>





}