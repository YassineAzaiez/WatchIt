package com.example.data.repositories

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.core.responses.RemoteDataNotFoundException
import com.example.core.responses.Result
import com.example.core.service.MoviesDAO
import com.example.data.dataSources.remote.MoviesDataSource
import com.example.domain.LocalMovie
import com.example.domain.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepository(
    private val dataSource: MoviesDataSource,
    private val moviesDAO: MoviesDAO
) {

     companion object{
         var page_number = 0
     }

    suspend fun  getRecommendedMovies(id : Long , page: Int) : Result<List<Movie>> {
        return  when (val result = dataSource.loadRecommendedMovies(id , page)){
            is Result.Success -> Result.Success(result.data.results)
            else -> Result.Error(RemoteDataNotFoundException())
        }
    }

    suspend fun getMovieById(id : Long , language: String, appendToResponse: String) : Result<Movie>{
        return when (val result = dataSource.getMovieById(id, language, appendToResponse)){
            is Result.Success -> Result.Success(result.data)
            else -> Result.Error(RemoteDataNotFoundException())
        }
    }

    suspend fun loadMoviesFromApi(list: String, language: String, page: Int): Result<List<Movie>> {
        return when (val result = dataSource.getMovies(list, language, page)) {
            is Result.Success -> {
               page_number = result.data.totalPages
                Result.Success(result.data.results)

            }
            else -> Result.Error(RemoteDataNotFoundException())
        }

    }

    suspend fun  getSearchResults(language: String,query: String , page: Int
                                  , include_adult : Boolean) : Result<List<Movie>>{
        return when(val result = dataSource.getSearchResult(language,query,page,include_adult)){
            is Result.Success ->{
                Result.Success(result.data.results)
            }

            else -> Result.Error(RemoteDataNotFoundException())
        }
    }

    suspend fun addMovieToFavorite(movie: Movie?) = withContext(Dispatchers.IO) {

            moviesDAO.insertMovie(movie)
        }

    suspend fun removeMovieFromFavorite(id : Long ) =  withContext(Dispatchers.IO){
        moviesDAO.deleteMovie(id)
    }


    suspend fun loadMoviesFromDb(): Result<List<LocalMovie>> =
        withContext(Dispatchers.IO) {
            Log.d("selection is done : ", "Successfully")
           val  movies = moviesDAO.getAllMovies()
            Result.Success(movies)
        }


}