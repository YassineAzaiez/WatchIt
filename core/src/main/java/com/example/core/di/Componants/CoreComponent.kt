package com.example.core.di.Componants

import android.content.Context
import com.example.core.di.modules.AppMoule
import com.example.core.di.modules.NetworkModule
import com.example.core.service.MovieDbService
import dagger.Component
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton


@Singleton
@Component(modules = [NetworkModule::class, AppMoule::class])
interface CoreComponent {
    fun getHttpClient() : OkHttpClient
    fun getCash() : Cache
    fun getContext() : Context
    fun getRetrofit() : Retrofit
    fun getMoviesService() : MovieDbService
}