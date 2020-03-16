package com.example.watchit.nowplayingMovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repositories.MoviesRepository
import com.example.domain.Movie
import kotlinx.coroutines.launch

class NowPlayingMoviesViewModel (private val repository: MoviesRepository) : ViewModel() {
    private val movieList = MutableLiveData<List<Movie>>()
     val nowPlayingMoviesList : LiveData<List<Movie>>
         get() = movieList


    fun loadNowPlayingMovies(){
        viewModelScope.launch {
            val movies = repository.getNowPlyingMovies()
            movieList.postValue(movies)
        }
    }
}
