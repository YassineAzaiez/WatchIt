package com.example.watchit.moviesFragment.favorites


import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Movie
import com.example.watchit.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*


class FavoriteMoviesAdpter(private val movies: List<Movie>) :
    RecyclerView.Adapter<FavoriteMoviesAdpter.MovieHolder>() {

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bindMovie(movies[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewe: Int): MovieHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieHolder(view)

    }


    override fun getItemCount(): Int {
        return movies.size
    }


    class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {

            this.itemView.setOnClickListener(this)
        }

        fun bindMovie(movie: Movie) {
            itemView.tvMovieTitle.text = movie.title
            Picasso.get()
                .load(Uri.parse("https://image.tmdb.org/t/p/w500" + movie.posterPath))
                .into(itemView.ivMoviePic)


        }

        override fun onClick(v: View?) {
            Log.d("movie", " clicked")
        }

    }


}