package com.example.watchit.moviesFragment.favorites


import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.LocalMovie
import com.example.watchit.R
import com.example.watchit.moviesFragment.MovieLikedListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*


class FavoriteMoviesAdpter(private val movies: List<LocalMovie>, private val movieLikedListener: MovieLikedListener) :
    RecyclerView.Adapter<FavoriteMoviesAdpter.MovieHolder>() {

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


    class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bindMovie(movie: LocalMovie,movieLikedListener: MovieLikedListener) {
            itemView.tvMovieTitle.text = movie.title
            Picasso.get()
                .load(Uri.parse("https://image.tmdb.org/t/p/w500" + movie.posterPath))
                .into(itemView.ivMoviePic)

            itemView.ivFavorite.apply {
                setImageResource(R.drawable.ic_favorite_enable)
                setOnClickListener{
                    movieLikedListener.onMovieDisliked(adapterPosition)

                }
            }



        }
    }
}