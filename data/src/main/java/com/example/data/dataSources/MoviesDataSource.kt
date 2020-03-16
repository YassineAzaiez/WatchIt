package com.example.data.dataSources

import android.util.Log
import com.example.core.responses.Result
import com.example.core.service.MovieDbService
import com.example.domain.ApiResponse
import com.example.domain.Movie
import retrofit2.Response
import java.io.IOException

class MoviesDataSource (private  val movieDbService: MovieDbService) {

   suspend fun getTopRatedMovies() : ApiResponse<Movie>?{
       val call = movieDbService.loadTopRatedMovies("en-US",3).await()
     return  safeApiCall(call = {call}, errorMessage = "Error occured")
   }

   suspend fun upComingMovies():ApiResponse<Movie>?{
       val call = movieDbService.loadUpCommingMovies("en-US",3).await()
       return safeApiCall(call = {call},errorMessage = "Error occured")
   }

    suspend fun getNowPlayingMovies():ApiResponse<Movie>?{
       val call = movieDbService.loadNowPlayingMovies("en-US",3).await()
       return safeApiCall(call = {call},errorMessage = "Error occured")
   }



    private suspend fun <T: Any> safeApiCall(call: suspend ()-> Response<T>,errorMessage : String): T?{
        val result : Result<T> = safeApiResult(call,errorMessage)
        var data : T? = null

        when (result){
            is Result.Success ->
                data = result.data
            is Result.Error ->
                Log.d("Movie dataSource ", "$errorMessage")

        }
        return  data
    }

    private suspend fun <T: Any> safeApiResult(call : suspend () -> Response<T>,errorMessage : String): Result<T>{
        val response =  call.invoke()
        if(response.isSuccessful){
            return Result.Success(response.body()!!)
        }

        return Result.Error(IOException("Error Occurred during getting safe Api result, ERROR - $errorMessage"))

}}