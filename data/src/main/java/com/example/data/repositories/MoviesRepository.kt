package com.example.data.repositories

import android.util.Log
import com.example.core.responses.Result
import com.example.data.dataSources.MoviesDataSource
import com.example.domain.ApiResponse
import com.example.domain.Movie

class MoviesRepository(private val dataSource: MoviesDataSource) {

     suspend fun loadMovies(list: String, language : String, page : Int) : Result<ApiResponse<Movie>> {
        return  dataSource.getMovies(list,language,page)

    }


}