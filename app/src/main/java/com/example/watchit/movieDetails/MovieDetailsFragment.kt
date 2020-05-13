package com.example.watchit.movieDetails


import android.os.Build

import android.os.Bundle


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager
import com.example.domain.Genres
import com.example.domain.Movie
import com.example.domain.Video
import com.example.watchit.MovieApplication
import com.example.watchit.R
import com.example.watchit.adapters.GenreAdapter
import com.example.watchit.adapters.MoviesAdapter
import com.example.watchit.adapters.TrailersAdapter
import com.example.watchit.moviesFragment.MovieLikedListener
import com.example.watchit.moviesFragment.MoviesFragment
import com.example.watchit.utils.Utils.formatDate
import com.example.watchit.utils.Utils.formatTime
import com.example.watchit.viewmodelFactory.MoviesViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_details_fragment.*
import java.text.FieldPosition
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class MovieDetailsFragment : Fragment(),TrailerClickListener {

    private lateinit var movieDetailsVm: MovieDetailsViewModel
    private lateinit var genreAdapter: GenreAdapter
    private lateinit var trailersAdapter: TrailersAdapter
    private lateinit var recommendedMoviesAdapter: MoviesAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var trailersLayoutManager: LinearLayoutManager
     private var trailerList = ArrayList<Video>()

    @Inject
    lateinit var viewModelFactory: MoviesViewModelFactory


    companion object {
        fun newInstance(movie: Movie): MovieDetailsFragment {
            val args = Bundle()

            args.putSerializable(MoviesFragment.EXTRA_ITEM, movie)

            val fragment = MovieDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var viewModel: MovieDetailsViewModel
    private lateinit var movie: Movie

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_details_fragment, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movie = arguments?.getSerializable(MoviesFragment.EXTRA_ITEM) as Movie
        MovieApplication.appCoponent.inject(this)
        movieDetailsVm =
            ViewModelProviders.of(this, viewModelFactory)[MovieDetailsViewModel::class.java]
        movieDetailsVm.getRecommendedMovies(movie.id, 1)
        movieDetailsVm.getMovieTrailers(movie.id, Locale.getDefault().language)

        Log.d("movie id", "is : ${movie.id}")
        movieTitle.text = movie.title
        movieTagLine.text = movie.tagline

        average.text = activity!!.getString(
            R.string.vote_average,
            movie.voteAverage.toString()
        )

        numbPeople.text = String.format(
            resources.getString(R.string.number_of_voters)
            , movie.voteCount
        )

        if (movie.tagline.isNullOrEmpty()) {
            movieTagLine.visibility = View.GONE
        } else {
            movieTagLine.text = movie.tagline
        }
        overView.text = movie.overview

        movieReleaseDate.text = formatDate(movie.releaseDate!!)
        movieDuration.text = formatTime(movie.runtime)

        Picasso.get().load("https://image.tmdb.org/t/p/w500/${movie.posterPath}").into(moviePoster)
        linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        trailersLayoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)

        val genresList = ArrayList<Genres>()
        movie.genreIds?.forEach { genresList.add(Genres.getGenreByID(it)) }
        genreAdapter = GenreAdapter(genresList)
        movieGenre.apply {
            layoutManager = ChipsLayoutManager.newBuilder(requireContext())
                .setOrientation(ChipsLayoutManager.HORIZONTAL).build()
            adapter = genreAdapter
        }


        movieDetailsVm.recommendedMovies.observe(this, Observer { movies ->

            recommendedMoviesList.apply {
                layoutManager = linearLayoutManager
                recommendedMoviesAdapter =
                    MoviesAdapter(
                        movies as ArrayList<Movie>
                        , null, true
                    )
                adapter = recommendedMoviesAdapter
            }
        })

      movieDetailsVm.trailers.observe(this, Observer {  trailer ->
          trailer.forEachIndexed { index, video ->trailerList.add(index,video)  }
          similarMoviesList.apply {
              layoutManager = trailersLayoutManager
              trailersAdapter = TrailersAdapter(trailer as ArrayList<Video>,this@MovieDetailsFragment)
              adapter = trailersAdapter

          }


      })
    }

    override fun onTrailerClicked(trailerKey: String , position: Int ) {
     val videoDialog = TrailerVideoFragment.newInstance(trailerList[position].key)
        videoDialog.show(activity!!.supportFragmentManager,"")
    }




}
