package com.example.watchit.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.responses.Result
import com.example.data.repositories.MoviesRepository
import com.example.domain.Movie
import kotlinx.coroutines.launch
import java.lang.Exception

class SearchViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {
    private val movieSearchResult = MutableLiveData<ArrayList<Movie>>()
    val searchMoviesResult: LiveData<ArrayList<Movie>>
        get() = movieSearchResult
    private val movieSearchError = MutableLiveData< Exception>()
    val searchError: LiveData<Exception>
        get() = movieSearchError

    fun getSearchResult(language: String,query: String , page: Int
                        , include_adult : Boolean){
           viewModelScope.launch {
              when(val result = moviesRepository.getSearchResults(language,query,page,include_adult)){
                  is Result.Success ->{
                      movieSearchResult.postValue(result.data as ArrayList<Movie>?)
                  }
                  is Result.Error ->{
                      movieSearchError.postValue(result.exception)
                  }
              }
           }
    }


}
