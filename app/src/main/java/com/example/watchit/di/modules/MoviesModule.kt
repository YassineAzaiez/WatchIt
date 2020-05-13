package com.example.watchit.di.modules

import com.example.core.service.MovieWebService
import com.example.core.service.MoviesDAO
import com.example.data.dataSources.remote.MoviesDataSource
import com.example.data.repositories.MoviesRepository
import com.example.watchit.viewmodelFactory.MoviesViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Scope
@Retention
annotation class MovieScope
@Module
class MoviesModule {


    @MovieScope
    @Provides
    fun provideMovieDataSource(movieWebService: MovieWebService) : MoviesDataSource {
        return MoviesDataSource(
            movieWebService
        )
    }

    @MovieScope
    @Provides
    fun provideMovieRepository(moviesDataSource: MoviesDataSource,moviesDAO :MoviesDAO ) : MoviesRepository{
        return MoviesRepository(dataSource = moviesDataSource,moviesDAO = moviesDAO)
    }

    @MovieScope
   @Provides
    fun provideViewModelFactory(moviesRepository: MoviesRepository) : MoviesViewModelFactory{
        return MoviesViewModelFactory( repository = moviesRepository)
    }
}