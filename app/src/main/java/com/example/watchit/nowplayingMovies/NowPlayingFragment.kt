package com.example.watchit.nowplayingMovies

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
import com.example.watchit.upcommingMovies.UpComingMoviesViewModel
import com.example.watchit.viewmodelFactory.MoviesViewModelFactory
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject


class NowPlayingFragment : Fragment() {
    private  lateinit var  gridLayoutManager: GridLayoutManager
    private  lateinit var nowPlayingMoviesViewModel: NowPlayingMoviesViewModel
    private  lateinit var nowPlayingMoviesAdapter: MoviesAdpter
    @Inject
    lateinit var viewModelFactory: MoviesViewModelFactory
    companion object {
        fun newInstance() =
            NowPlayingFragment()
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
        nowPlayingMoviesViewModel = ViewModelProviders.of(this,viewModelFactory).get(NowPlayingMoviesViewModel::class.java)
        nowPlayingMoviesViewModel.loadNowPlayingMovies()
        gridLayoutManager = GridLayoutManager(activity,2)
        val nowPlayingMovies = Observer<List<Movie>>{movies ->
            MovieList.apply{
            nowPlayingMoviesAdapter = MoviesAdpter(movies)
            layoutManager = gridLayoutManager
            adapter = nowPlayingMoviesAdapter
            }
        }

        nowPlayingMoviesViewModel.nowPlayingMoviesList.observe(this,nowPlayingMovies)
    }

}
