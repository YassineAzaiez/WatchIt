package com.example.watchit.viewmodelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.repositories.MoviesRepository

class MoviesViewModelFactory  (val repository: MoviesRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MoviesRepository::class.java)
            .newInstance(repository)
    }
}