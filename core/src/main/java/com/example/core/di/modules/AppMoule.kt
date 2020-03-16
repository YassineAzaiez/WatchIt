package com.example.core.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppMoule (context: Context){
  val appContext = context.applicationContext

    @Provides
    @Singleton
    fun provideAppContext() : Context{
        return appContext
    }
}