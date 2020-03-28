package com.example.core.di.modules

import android.app.Application
import com.example.core.service.MoviesDAO
import com.example.core.utils.AppDataBase

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule (private val application: Application){
    private  var appDb : AppDataBase = AppDataBase.getDatabase(application)


    @Singleton
    @Provides
    fun provideRoomDatabase() = appDb

    @Singleton
    @Provides
    fun providesMoviesDao(appDataBase: AppDataBase) : MoviesDAO = appDataBase.moviesDao()

}