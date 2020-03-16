package com.example.watchit.di.modules

import com.example.core.service.MovieDbService
import com.example.data.dataSources.MoviesDataSource
import com.example.data.repositories.MoviesRepository
import com.example.watchit.viewmodelFactory.MoviesViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Scope
import javax.inject.Singleton

@Scope
@Retention
annotation class MovieScope
@Module
class MoviesModule {


    @MovieScope
    @Provides
    fun provideMovieDataSource(movieDbService: MovieDbService) : MoviesDataSource{
        return MoviesDataSource(movieDbService)
    }

    @MovieScope
    @Provides
    fun provideMovieRepository(moviesDataSource: MoviesDataSource) : MoviesRepository{
        return MoviesRepository(dataSource = moviesDataSource)
    }

    @MovieScope
   @Provides
    fun provideViewModelFactory(moviesRepository: MoviesRepository) : MoviesViewModelFactory{
        return MoviesViewModelFactory( repository = moviesRepository)
    }
}