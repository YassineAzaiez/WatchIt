package com.example.watchit.moviesFragment.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.responses.Result
import com.example.data.repositories.MoviesRepository
import com.example.domain.LocalMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {
    private val moviesList = MutableLiveData<List<LocalMovie>>()
    val favoriteMovie: LiveData<List<LocalMovie>>
        get() = moviesList

    fun getFavoriteMovies() = viewModelScope.launch {
        when (val result = moviesRepository.loadMoviesFromDb()){
            is Result.Success ->   moviesList.postValue(result.data)
        }

    }

    fun removeFromFavorites(postion: Int) {
        viewModelScope.launch {
            val movie = moviesList.value?.get(postion)
            movie?.let { moviesRepository.removeMovieFromFavorite(movie.id) }

        }
    }
}
