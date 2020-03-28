package com.example.core.utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.core.service.MoviesDAO
import com.example.core.service.model.LocalMovie


@Database(entities = [LocalMovie::class],version = 1,exportSchema = false)
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