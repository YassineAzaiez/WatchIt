package com.example.data.dataSources

import android.util.Log
import com.example.core.responses.Result
import com.example.core.service.MovieDbService
import com.example.domain.ApiResponse
import com.example.domain.Movie
import retrofit2.Response
import java.io.IOException

class MoviesDataSource (private  val movieDbService: MovieDbService) {




     suspend fun getMovies(list : String  , language: String, page: Int) = safeApiCall(
         call = {loadMovies(list,language,page)}
         ,errorMessage= "Error occured"
     )


    private  suspend fun loadMovies (list : String ,language : String , page: Int): Result<ApiResponse<Movie>> {
      val response =  movieDbService.loadMovies(list,language, page).await()
        if(response.isSuccessful){
            return Result.Success(response.body()!!)
        }

        return  Result.Error(IOException("Error occurred during fetching movies!"))
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