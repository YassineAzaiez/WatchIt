package com.example.watchit.movieDetails

import java.text.FieldPosition

interface TrailerClickListener {
    fun onTrailerClicked(trailerKey: String , position: Int)
}