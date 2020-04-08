package com.example.watchit.moviesFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.responses.Result
import com.example.data.repositories.MoviesRepository
import com.example.domain.LocalMovie
import com.example.domain.Movie
import kotlinx.coroutines.launch

class MoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {
    private var page: Int = 0
    private val movieList = MutableLiveData<List<Movie>>()
    private val likedMovies = MutableLiveData<List<LocalMovie>>()

    val topRatedMoviesList: LiveData<List<Movie>>
        get() = movieList
    val likedMoviesList: LiveData<List<LocalMovie>>
        get() = likedMovies


    fun loadLikedMovies() = viewModelScope.launch {
        when (val result = moviesRepository.loadMoviesFromDb()){
            is Result.Success -> likedMovies.postValue(result.data)
        }
    }


    fun loadMovies(list: String, language: String) {
        page +=1
        viewModelScope.launch {
            when (val movies = moviesRepository.loadMoviesFromApi(list, language, page)) {
                is Result.Success ->{
                    movieList.postValue(movies.data)
                }
            }
        }
    }

    fun addTofavorite(postion: Int) =
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



}
