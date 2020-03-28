package com.example.watchit.moviesFragment

interface MovieLikedListener {

    fun onMovieLiked(position : Int)
    fun onMovieDisliked(position: Int)
}