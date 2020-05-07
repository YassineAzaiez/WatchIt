package com.example.watchit.movieDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.responses.Result
import com.example.data.repositories.MoviesRepository
import com.example.domain.Movie
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val repository: MoviesRepository) : ViewModel() {
    private var _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie>
        get() = _movie

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


    override fun onCleared() {
        super.onCleared()
        viewModelScope.coroutineContext.cancel()
    }
}
