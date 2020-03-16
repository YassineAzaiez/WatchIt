package com.example.watchit.upcommingMovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repositories.MoviesRepository
import com.example.domain.Movie
import kotlinx.coroutines.launch

class UpComingMoviesViewModel(private  val moviesRepository: MoviesRepository) : ViewModel() {
    private val upComingMoviesList = MutableLiveData<List<Movie>>()
     val upComingMovieslist : LiveData<List<Movie>>
    get() = upComingMoviesList

    fun laodUpComingMovies(){
        viewModelScope.launch {
           val movies =  moviesRepository.getUpComingMovies()
            upComingMoviesList.postValue(movies)
        }
    }
}
