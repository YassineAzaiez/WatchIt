package com.example.data.repositories

import android.util.Log
import com.example.data.dataSources.MoviesDataSource
import com.example.domain.Movie

class MoviesRepository(private val dataSource: MoviesDataSource) {

     suspend fun getTopRatedMovies() : MutableList<Movie> {
        val moviesResponse = dataSource.getTopRatedMovies()
         Log.d("is empty","empty : "+moviesResponse?.results.isNullOrEmpty())
         return moviesResponse!!.results.toMutableList()
    }

    suspend fun getUpComingMovies() : MutableList<Movie> {
        val moviesResponse = dataSource.upComingMovies()
         Log.d("is empty","empty : "+moviesResponse?.results.isNullOrEmpty())
         return moviesResponse!!.results.toMutableList()
    }

    suspend fun getNowPlyingMovies() : MutableList<Movie> {
        val moviesResponse = dataSource.getNowPlayingMovies()
         Log.d("is empty","empty : "+moviesResponse?.results.isNullOrEmpty())
         return moviesResponse!!.results.toMutableList()
    }
}