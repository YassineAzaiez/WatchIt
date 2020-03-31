package com.example.core.service



import androidx.room.*
import com.example.domain.LocalMovie
import com.example.domain.Movie


@Dao
interface MoviesDAO {

    @Query("SELECT id , title,posterPath FROM movies")
   suspend fun getAllMovies(): List<LocalMovie>

    @Query("SELECT * FROM movies WHERE id= :id")
   suspend fun getMovieById(id: Long): Movie

   @Query("DELETE FROM movies where id = :id")
    suspend fun deleteMovie(id : Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertMovie(movie: Movie?)





}