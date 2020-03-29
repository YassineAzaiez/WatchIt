package com.example.core.service


import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.domain.Movie


@Dao
interface MoviesDAO {

    @Query("SELECT * FROM movies")
   suspend fun getAllMovies(): List<Movie>

    @Query("SELECT * FROM movies WHERE id= :id")
   suspend fun getMovieById(id: Long): Movie

   @Query("DELETE FROM movies where id = :id")
    suspend fun deleteMovie(id : Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertMovie(movie: Movie?)





}