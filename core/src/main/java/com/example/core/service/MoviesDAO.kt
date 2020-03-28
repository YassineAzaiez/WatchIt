package com.example.core.service


import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.core.service.model.LocalMovie

@Dao
interface MoviesDAO {

    @Query("SELECT * FROM movies")
    fun getAllMovies(): List<LocalMovie>

    @Query("SELECT * FROM movies WHERE id= :id")
    fun getMovieById(id: Long): LocalMovie

   @Query("DELETE FROM movies where id = :id")
    suspend fun deleteMovie(id : Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: LocalMovie)





}