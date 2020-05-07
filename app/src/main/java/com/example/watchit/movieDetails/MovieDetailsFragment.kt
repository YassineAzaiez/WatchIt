package com.example.watchit.movieDetails


import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager
import com.example.domain.Genres
import com.example.domain.Movie
import com.example.watchit.R
import com.example.watchit.adapters.GenreAdapter
import com.example.watchit.adapters.MoviesAdapter
import com.example.watchit.moviesFragment.MoviesFragment
import com.example.watchit.viewmodelFactory.MoviesViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_details_fragment.*
import javax.inject.Inject

class MovieDetailsFragment : Fragment() {

    private lateinit var movieDetailsVm: MovieDetailsViewModel
    private lateinit var genreAdapter: GenreAdapter
    private lateinit var recommendedMoviesAdapter: MoviesAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

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

        movieTitle.text = movie.title
        movieTagLine.text = movie.tagline

        average.text = String.format(
            resources.getString(R.string.vote_average),
            movie.voteAverage
        )

        numbPeople.text = String.format(
            resources.getString(R.string.number_of_voters)
            , movie.voteCount
        )

        if(movie.tagline.isNullOrEmpty()){
            movieTagLine.visibility = View.GONE
        }else{
            movieTagLine.text = movie.tagline
        }
        overView.text = movie.overview

        movieReleaseDate.text = movie.releaseDate
        movieDuration.text = movie.runtime.toString()

        Picasso.get().load("https://image.tmdb.org/t/p/w500/${movie.posterPath}").into(moviePoster)
        linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val genresList = ArrayList<Genres>()
        movie.genreIds?.forEach { genresList.add(Genres.getGenreByID(it)) }
        genreAdapter = GenreAdapter(genresList)
        movieGenre.apply {
            layoutManager = ChipsLayoutManager.newBuilder(requireContext()).setOrientation(ChipsLayoutManager.HORIZONTAL).build()
            adapter = genreAdapter
        }


    }

    private fun minutesToHours(time: Int): String {
        var hours = time / 60
        var min = hours % 10
        return "$hours : $min"
    }
}
