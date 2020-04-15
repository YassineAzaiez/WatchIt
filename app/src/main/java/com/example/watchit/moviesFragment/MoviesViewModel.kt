package com.example.watchit.moviesFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.responses.Result
import com.example.data.repositories.MoviesRepository
import com.example.domain.LocalMovie
import com.example.domain.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {
    private val movieList = MutableLiveData<ArrayList<Movie>>()
    private val likedMovies = MutableLiveData<List<LocalMovie>>()
    private val loadings = MutableLiveData<Boolean>()
    private val error = MutableLiveData<String>()
    val topRatedMoviesList: LiveData<ArrayList<Movie>>
        get() = movieList
    val likedMoviesList: LiveData<List<LocalMovie>>
        get() = likedMovies
    val loading: LiveData<Boolean>
        get() = loadings
    val errors: LiveData<String>
        get() = error


    fun loadLikedMovies() = viewModelScope.launch {
        when (val result = moviesRepository.loadMoviesFromDb()) {
            is Result.Success -> likedMovies.postValue(result.data)
        }
    }


    fun loadMovies(list: String, language: String, page: Int) {

        loadings.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {

            when (val movies = moviesRepository.loadMoviesFromApi(
                list,
                language,
                if (page <= MoviesRepository.page_number) page else 1
            )) {
                is Result.Success -> {
                    movieList.postValue(movies.data as ArrayList<Movie>?)
                    loadings.postValue(false)
                }

                is Result.Error -> {
                    error.postValue(movies.exception.message)
                    loadings.postValue(false)
                }
            }
        }
    }

    fun addToFavorite(postion: Int) =
        viewModelScope.launch {
            val movie = movieList.value?.get(postion)
            movie?.let { moviesRepository.addMovieToFavorite(it) }
        }


    fun removeFromFavorites(postion: Int) {
        viewModelScope.launch {
            val movie = movieList.value?.get(postion)
            movie?.let { moviesRepository.removeMovieFromFavorite(movie.id) }

        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.coroutineContext.cancel()
    }
}
