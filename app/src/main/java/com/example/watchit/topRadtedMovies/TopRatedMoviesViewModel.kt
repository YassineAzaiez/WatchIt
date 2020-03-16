package com.example.watchit.topRadtedMovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repositories.MoviesRepository
import com.example.domain.Movie
import kotlinx.coroutines.launch

class TopRatedMoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {
   private val movieList = MutableLiveData<List<Movie>>()
     val topRatedMoviesList :LiveData<List<Movie>>
         get() = movieList

    fun loadTopRatedMovies() {
        viewModelScope.launch {
            val movies = moviesRepository.getTopRatedMovies()
            movieList.postValue(movies)
        }
    }

   


}
