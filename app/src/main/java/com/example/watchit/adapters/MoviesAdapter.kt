package com.example.watchit.adapters


import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Movie
import com.example.watchit.R
import com.example.watchit.moviesFragment.MovieLikedListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*


class MoviesAdapter constructor(private val movies:ArrayList<Movie> = ArrayList(), private val movieLikedListener: MovieLikedListener?=null) :
    RecyclerView.Adapter<MoviesAdapter.MovieHolder>() {



    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bindMovie(movies[position],movieLikedListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewe: Int): MovieHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieHolder(view)

    }


    override fun getItemCount(): Int {
        return movies.size
    }

    fun addAll(movieList:ArrayList<Movie>) : ArrayList<Movie>{
         movies.addAll(movieList)
       return  movies
    }

    fun clear() {
        movies.clear()
        notifyDataSetChanged()
    }


    class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView){



        fun bindMovie(movie: Movie,movieLikedListener: MovieLikedListener?) {
            itemView.tvMovieTitle.text = movie.title
            Picasso.get()
                .load(Uri.parse("https://image.tmdb.org/t/p/w500" + movie.posterPath))
                .placeholder(R.drawable.placeholder_rectangle)
                .into(itemView.ivMoviePic)


                var postion = adapterPosition
                if (movie.isBookmarked) {
                    itemView.ivFavorite.setImageResource(R.drawable.ic_favorite_enable)

                } else {
                    itemView.ivFavorite.setImageResource(R.drawable.ic_favorite_disabled)
                }
            itemView.ivFavorite.setOnClickListener {
                when (movie.isBookmarked){
                    true -> {
                        movieLikedListener?.onMovieDisliked(postion)
                        movie.isBookmarked = false
                    }
                     false ->{
                         movieLikedListener?.onMovieLiked(postion)
                         movie.isBookmarked = true
                     }
                }

            }
        }



    }


}