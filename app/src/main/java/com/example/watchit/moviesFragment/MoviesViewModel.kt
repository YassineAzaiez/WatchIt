package com.example.watchit.moviesFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.responses.Result
import com.example.data.repositories.MoviesRepository
import com.example.domain.Movie
import kotlinx.coroutines.launch

class MoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {
   private val movieList = MutableLiveData<List<Movie>>()
     val topRatedMoviesList :LiveData<List<Movie>>
         get() = movieList

    fun loadMovies(list:String ,language : String , page : Int ) {
        viewModelScope.launch {
            when (val movies = moviesRepository.loadMovies(list,language, page)){
             is Result.Success ->movieList.postValue(movies.data.results)
           }
        }
    }

   


}