package com.example.core.di.Componants

import android.content.Context
import com.example.core.di.modules.AppMoule
import com.example.core.di.modules.NetworkModule
import com.example.core.di.modules.StorageModule
import com.example.core.service.MovieWebService
import com.example.core.service.MoviesDAO
import com.example.core.utils.AppDataBase
import dagger.Component
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton


@Singleton
@Component(modules = [NetworkModule::class, AppMoule::class,StorageModule::class])
interface CoreComponent {
    fun getHttpClient() : OkHttpClient
    fun getCash() : Cache
    fun getContext() : Context
    fun getRetrofit() : Retrofit
    fun getMoviesService() : MovieWebService
    fun getAppDb() : AppDataBase
    fun getMoviesDao() : MoviesDAO
}