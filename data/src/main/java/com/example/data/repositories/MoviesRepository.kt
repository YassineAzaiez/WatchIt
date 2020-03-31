package com.example.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
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


    suspend fun loadMoviesFromApi(list: String, language: String, page: Int): Result<List<Movie>> {
        return when (val result = dataSource.getMovies(list, language, page)) {
            is Result.Success -> {

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
           var  movies = moviesDAO.getAllMovies()
            Result.Success(movies)
        }


}