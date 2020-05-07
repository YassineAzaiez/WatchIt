package com.example.data.dataSources.remote


import com.example.core.responses.Result
import com.example.core.service.MovieDbService
import com.example.domain.ApiResponse
import com.example.domain.Movie
import java.io.IOException

class MoviesDataSource(private val movieDbService: MovieDbService) {

    val errorMessage = "Error occurred while fetching data"
    suspend fun getMovies(list: String, language: String, page: Int) = safeApiCall(
        call = { loadMovies(list, language, page) }
        , errorMessage = errorMessage
    )


    private suspend fun loadMovies(
        list: String,
        language: String,
        page: Int
    ): Result<ApiResponse<Movie>> {
        val response = movieDbService.loadMovies(list, language, page).await()
        if (response.isSuccessful) {
            return Result.Success(response.body()!!)

        }

        return Result.Error(IOException("Error occurred during fetching movies!"))
    }

    suspend fun getMovieById(
        id: Long, language: String,
        appendToResponse: String
    ) = safeApiCall(
        { loadMovieById(id, language, appendToResponse) }, errorMessage
    )

    suspend fun loadRecommendedMovies(id: Long, page: Int) = safeApiCall(
        { getRecommendedMovies(id, page) }, errorMessage
    )

    private suspend fun loadMovieById(
        id: Long, language: String,
        appendToResponse: String
    ): Result<Movie> {
        val response = movieDbService.loadMovieById(id, language, appendToResponse).await()
        if (response.isSuccessful) {
            return Result.Success(response.body()!!)
        }
        return Result.Error(IOException("Error occured during fetching for movie"))
    }

    private suspend fun getRecommendedMovies(id: Long, page: Int): Result<ApiResponse<Movie>> {
        val response = movieDbService.loadRecomandedMovies(id, page).await()
        if (response.isSuccessful) {
            return Result.Success(response.body()!!)
        }
        return Result.Error(IOException("Error occured during fetching for movie"))
    }


    suspend fun getSearchResult(
        language: String,
        query: String,
        page: Int,
        include_adult: Boolean
    ) = safeApiCall(
        call = { searchForMovies(language, query, page, include_adult) }
        , errorMessage = errorMessage
    )

    private suspend fun searchForMovies(
        language: String,
        query: String,
        page: Int,
        include_adult: Boolean
    ): Result<ApiResponse<Movie>> {
        val response = movieDbService.searchMovies(language, query, page, include_adult).await()
        if (response.isSuccessful) {
            return Result.Success(response.body()!!)
        }

        return Result.Error(IOException("Error occurred during fetching movies!"))
    }

    private suspend fun <T : Any> safeApiCall(
        call: suspend () -> Result<T>,
        errorMessage: String
    ): Result<T> = try {
        call.invoke()
    } catch (e: Exception) {
        Result.Error(IOException(errorMessage, e))


    }


}