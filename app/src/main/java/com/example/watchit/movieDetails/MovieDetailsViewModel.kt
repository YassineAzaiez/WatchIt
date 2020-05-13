package com.example.watchit.movieDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.responses.Result
import com.example.data.repositories.MoviesRepository
import com.example.domain.Movie
import com.example.domain.Video
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val repository: MoviesRepository) : ViewModel() {
    private var _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie>
        get() = _movie

    private var _trailers = MutableLiveData<List<Video>>()
    val trailers: LiveData<List<Video>>
        get() = _trailers

    private var _recommendedMovies = MutableLiveData<List<Movie>>()
    val recommendedMovies: LiveData<List<Movie>>
        get() = _recommendedMovies

    private var _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _loadings = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loadings


    fun getMovieById(id: Long, language: String, appendToResponse: String) {
        _loadings.postValue(true)
        viewModelScope.launch {
            when (val result = repository.getMovieById(id, language, appendToResponse)) {

                is Result.Success -> {
                    _movie.postValue(result.data)
                    _loadings.postValue(false)
                }

                is Result.Error -> {
                    _error.postValue(result.exception.message)
                    _loadings.postValue(false)
                }

            }
        }
    }

    fun getRecommendedMovies(id: Long, page: Int) {
        _loadings.postValue(true)
        viewModelScope.launch {
            when (val result = repository.getRecommendedMovies(id, page)) {
                is Result.Success -> {
                    _recommendedMovies.postValue(result.data)
                    _loadings.postValue(false)
                }

                is Result.Error -> {
                    _error.postValue(result.exception.message)
                    _loadings.postValue(false)
                }
            }
        }
    }

    fun getMovieTrailers(id: Long, language: String) {
        viewModelScope.launch {
            when (val result = repository.getMovieTrailers(id, language)) {
                is Result.Success -> {
                    _trailers.postValue(result.data)
                }

                is Result.Error -> {
                    _error.postValue(result.exception.message)
                }
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelScope.coroutineContext.cancel()
    }
}
