package com.example.watchit.upcommingMovies

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
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


class UpCommingMoviesFragment : Fragment() {
  private  lateinit var  gridLayoutManager: GridLayoutManager
  private  lateinit var  upComingMoviesViewModel: UpComingMoviesViewModel
  private  lateinit var  upComingMoviesAdapter: MoviesAdpter

    @Inject
    lateinit var upComingMoviesVmFactory: MoviesViewModelFactory

    companion object {
        fun newInstance() =
            UpCommingMoviesFragment()
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MovieApplication.appCoponent.inject(this)
        upComingMoviesViewModel = ViewModelProviders
            .of(this,upComingMoviesVmFactory)[UpComingMoviesViewModel::class.java]
        upComingMoviesViewModel.laodUpComingMovies()
        gridLayoutManager = GridLayoutManager(activity,2)
        val upComingMoviesObserver = Observer<List<Movie>>{movies ->

            MovieList.apply {
                upComingMoviesAdapter = MoviesAdpter(movies)
                layoutManager = gridLayoutManager
                adapter = upComingMoviesAdapter
            }

        }

        upComingMoviesViewModel.upComingMovieslist.observe(this,upComingMoviesObserver)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}
