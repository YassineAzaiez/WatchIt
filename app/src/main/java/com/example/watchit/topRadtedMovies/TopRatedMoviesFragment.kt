package com.example.watchit.topRadtedMovies

import android.content.res.Configuration
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.domain.Movie
import com.example.watchit.MovieApplication
import com.example.watchit.R
import com.example.watchit.adapters.GridItemDecoration
import com.example.watchit.adapters.MoviesAdpter
import com.example.watchit.viewmodelFactory.MoviesViewModelFactory
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class TopRatedMoviesFragment : Fragment() {
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var topRatedMoviesVm: TopRatedMoviesViewModel
    private lateinit var topRatedMoviesAdapter: MoviesAdpter

    @Inject
    lateinit var topRatedMoviesVmFactory: MoviesViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MovieApplication.appCoponent.inject(this)

        gridLayoutManager = GridLayoutManager(requireContext(), getColumnsNumber())

        topRatedMoviesVm = ViewModelProviders.of(
            this,
            topRatedMoviesVmFactory
        )[TopRatedMoviesViewModel::class.java]
        topRatedMoviesVm.loadTopRatedMovies()

        val topRatedMoviesList = Observer<List<Movie>> { movies ->
            MovieList.apply {
                topRatedMoviesAdapter = MoviesAdpter(movies)
                layoutManager = gridLayoutManager
                addItemDecoration(GridItemDecoration(2, 3))
                adapter = topRatedMoviesAdapter

            }


        }
        topRatedMoviesVm.topRatedMoviesList.observe(this, topRatedMoviesList)


    }

    private fun getColumnsNumber(): Int {
        val displayMetrics = DisplayMetrics()
        activity!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels
        return when (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            width > 1000 -> {
                2
            }

            width > 1700 -> {
               4
            }

            width > 1200 -> {
               3
            }
            else -> {
               2
            }


        }
    }
}


