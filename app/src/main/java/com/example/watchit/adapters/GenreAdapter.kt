package com.example.watchit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Genres
import com.example.watchit.R
import kotlinx.android.synthetic.main.item_genre.view.*
import java.util.zip.Inflater

class GenreAdapter(private val genreList : ArrayList<Genres>) : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) : GenreViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.item_genre,parent,false)
        return GenreViewHolder(view)
    }

    override fun getItemCount(): Int {
      return genreList.size
    }

    override fun onBindViewHolder(holder: GenreAdapter.GenreViewHolder, position: Int) {
        holder.bind(genreList[position])
    }

    class GenreViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){

       fun bind (genre : Genres){
             itemView.movieGenre.text = genre.name
       }

    }
}