package com.example.core.di.modules


import android.content.Context
import com.example.core.BuildConfig.*
import com.example.core.di.network.*
import com.example.core.service.MovieWebService
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

import javax.inject.Singleton

@Module
class NetworkModule {


    @Provides
    @Singleton
    fun providesOkHttpClient(cache : Cache): OkHttpClient {
        val  client =  OkHttpClient.Builder()
            .addInterceptor { chain: Interceptor.Chain ->
                var request = chain.request()
                var httpUrl = request.url()
                httpUrl = httpUrl.newBuilder()
                    .addQueryParameter("api_key", API_Key)
                    .build()
                request = request.newBuilder().url(httpUrl).build()
                chain.proceed(request)
            }
            .cache(cache)
            .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT.toLong(),TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT.toLong(),TimeUnit.SECONDS)

        if(DEBUG){
           client.addNetworkInterceptor(StethoInterceptor())
        }
        return client.build()
              }

    @Provides
    @Singleton
    fun providesRetrofitInstance(okHttpClient: OkHttpClient) : Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    @Provides
    @Singleton
    fun providesOkhttpCache(context: Context): Cache {
        return Cache(context.cacheDir, CACHE_SIZE.toLong())
    }



    @Provides
    @Singleton
    fun provideMovieDbService(retrofit : Retrofit ) : MovieWebService{
        return retrofit.create(MovieWebService :: class.java)
    }

}