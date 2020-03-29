package com.example.core.utils

import android.content.Context
import androidx.room.*
import com.example.core.service.MoviesDAO
import com.example.core.utils.typeConverters.*
import com.example.domain.Movie


@Database(entities = [Movie::class],version = 1,exportSchema = false)
@TypeConverters(TypeGenderConverter::class,
    TypeCastConverter::class,
    TypeCrewConverter::class,
    TypeIntConverter::class,
    TypeMovieConverter::class,
    TypeCountryConverter::class,
    TypeCompanyConverter::class,
    TypeLanguageConverter::class)
abstract class AppDataBase : RoomDatabase(){

    abstract fun moviesDao() : MoviesDAO

    companion object {
        @Volatile
        private var INSTANCE : AppDataBase? = null

        fun getDatabase(context: Context) : AppDataBase {

            return INSTANCE ?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext ,
                   AppDataBase::class.java ,
                   "moviesDb"
              ).fallbackToDestructiveMigration()
               .build()
               .also { INSTANCE = it }
            }

        }
    }

}