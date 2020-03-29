package com.example.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by yassine 18/02/20 .
 */

data class Genres (
   @SerializedName("id") val id: Int = 0,
   @SerializedName("name") val name: String? = ""
): Serializable {

   companion object {
      private const val GENRE_ACTION = 28
      private const val GENRE_ADVENTURE = 12
      private const val GENRE_ANIMATION = 16
      private const val GENRE_COMEDY = 35
      private const val GENRE_CRIME = 80
      private const val GENRE_DOCUMENTARY = 99
      private const val GENRE_DRAMA = 18
      private const val GENRE_FAMILY = 10751
      private const val GENRE_FANTASY = 14
      private const val GENRE_HISTORY= 36
      private const val GENRE_HORROR = 27
      private const val GENRE_MUSIC = 10402
      private const val GENRE_MYSTERY = 9648
      private const val GENRE_ROMANCE = 10749
      private const val GENRE_SCIENCE_FICTION = 878
      private const val GENRE_TV_MOVIE = 10770
      private const val GENRE_THRILLER = 53
      private const val GENRE_WAR = 10752
      private const val GENRE_WESTERN = 37

      private val genres : List<Genres>
          get() {
             val genres = ArrayList<Genres>()
             genres.add(Genres(GENRE_ACTION,"Action"))
             genres.add(Genres(GENRE_ADVENTURE,"Adventure"))
             genres.add(Genres(GENRE_ANIMATION,"Animation"))
             genres.add(Genres(GENRE_COMEDY,"Comedy"))
             genres.add(Genres(GENRE_CRIME,"Crime"))
             genres.add(Genres(GENRE_DOCUMENTARY,"Documentary"))
             genres.add(Genres(GENRE_DRAMA,"Drama"))
             genres.add(Genres(GENRE_FAMILY,"Family"))
             genres.add(Genres(GENRE_FANTASY,"Fantasy"))
             genres.add(Genres(GENRE_HISTORY,"History"))
             genres.add(Genres(GENRE_HORROR,"Horror"))
             genres.add(Genres(GENRE_MUSIC,"Music"))
             genres.add(Genres(GENRE_MYSTERY,"Mystery"))
             genres.add(Genres(GENRE_ROMANCE,"Romance"))
             genres.add(Genres(GENRE_SCIENCE_FICTION,"Science Fiction"))
             genres.add(Genres(GENRE_TV_MOVIE,"Tv Movie"))
             genres.add(Genres(GENRE_THRILLER,"Thriller"))
             genres.add(Genres(GENRE_WAR,"War"))
             genres.add(Genres(GENRE_WESTERN,"Western"))

             return  genres
          }

      fun getGenreByID(genereId: Int) : Genres{
          for(genre : Genres in genres){
             if(genre.id == genereId){
                return genre
             }
          }
         return Genres(-1 , "")
      }

   }







}