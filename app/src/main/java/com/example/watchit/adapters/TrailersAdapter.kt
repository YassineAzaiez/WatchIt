package com.example.watchit.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Video
import com.example.watchit.R
import com.example.watchit.movieDetails.TrailerClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_trailer.view.*
import java.util.*
import kotlin.collections.ArrayList

class TrailersAdapter(
    private val trailers: ArrayList<Video> = ArrayList(),
    private val trailerClickListener: TrailerClickListener
) :
    RecyclerView.Adapter<TrailersAdapter.TrailersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailersViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_trailer, parent, false)
        return TrailersViewHolder(view)
    }

    override fun getItemCount(): Int {
        return trailers.size
    }

    override fun onBindViewHolder(holder: TrailersViewHolder, position: Int) {
        holder.bind(trailers[position],trailerClickListener)
    }

    class TrailersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(trailer: Video, trailerClickListener: TrailerClickListener) {
            itemView.setOnClickListener {
                trailerClickListener.onTrailerClicked(trailer.key,adapterPosition)
            }
            Picasso.get().load(String.format(Locale.US, YOUTUBE_IMAGE, trailer.key))
                .into(itemView.trailerThumbnail)
            itemView.trailerName.text = trailer.name
        }

    }

    companion object {
        const val YOUTUBE_IMAGE = "https://img.youtube.com/vi/%s/0.jpg"
    }

}
