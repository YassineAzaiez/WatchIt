package com.example.watchit.moviesFragment.favorites

import android.content.res.Configuration
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.domain.Movie
import com.example.watchit.MovieApplication

import com.example.watchit.R
import com.example.watchit.adapters.MoviesAdpter
import com.example.watchit.viewmodelFactory.MoviesViewModelFactory
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject

class FavoritesFragment : Fragment() {
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var viewModel: FavoritesViewModel
    private lateinit var moviesAdapter: FavoriteMoviesAdpter

    @Inject
    lateinit var moviesViewModelFactory: MoviesViewModelFactory
    companion object {
        fun newInstance() = FavoritesFragment()
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        MovieApplication.appCoponent.inject(this)
        gridLayoutManager = GridLayoutManager(requireContext(), getColumnsNumber())
        viewModel = ViewModelProviders.of(this,moviesViewModelFactory).get(FavoritesViewModel::class.java)
        viewModel.getFavoriteMovies()

        val favoriteMoviesList = Observer<List<Movie>>{
            MovieList.apply {
                layoutManager = gridLayoutManager
                moviesAdapter = FavoriteMoviesAdpter(it)
                adapter = moviesAdapter

            }
        }

        viewModel.favoriteMovie.observe(this,favoriteMoviesList)


    }

    fun getColumnsNumber(): Int {
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
                3
            }


        }
    }

}
