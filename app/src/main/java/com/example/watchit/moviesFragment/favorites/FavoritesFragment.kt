package com.example.watchit.moviesFragment.favorites

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.domain.LocalMovie
import com.example.watchit.MovieApplication
import com.example.watchit.R
import com.example.watchit.Utils
import com.example.watchit.moviesFragment.MovieLikedListener
import com.example.watchit.viewmodelFactory.MoviesViewModelFactory
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject

class FavoritesFragment : Fragment() ,MovieLikedListener{
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
        noInternet.visibility = View.GONE
        loading.visibility = View.GONE
        btngoToFavorite.visibility = View.GONE
        MovieApplication.appCoponent.inject(this)
        gridLayoutManager = GridLayoutManager(requireContext(), Utils.getColumnsNumber(requireActivity()))
        MovieList.hasFixedSize()
        viewModel = ViewModelProviders.of(this,moviesViewModelFactory).get(FavoritesViewModel::class.java)
        viewModel.getFavoriteMovies()

        val favoriteMoviesList = Observer<List<LocalMovie>>{
            MovieList.apply {
                layoutManager = gridLayoutManager
                moviesAdapter = FavoriteMoviesAdpter(it,this@FavoritesFragment)
                adapter = moviesAdapter

            }
        }

        viewModel.favoriteMovie.observe(this,favoriteMoviesList)


    }

    override fun onMovieLiked(position: Int) {
        Log.d("hello","there")
    }

    override fun onMovieDisliked(position: Int) {
        viewModel.removeFromFavorites(position)
        moviesAdapter.notifyItemRemoved(position)
        refreshFragment(this)

    }

    private fun refreshFragment(fragment: Fragment){
        fragment.fragmentManager!!.beginTransaction()
            .detach(this)
            .attach(this)
            .commit()
        }
    }

