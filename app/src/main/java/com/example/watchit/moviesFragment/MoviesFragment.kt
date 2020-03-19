package com.example.watchit.moviesFragment

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
import com.example.domain.Movie.Companion.TOP_RATED
import com.example.watchit.MovieApplication
import com.example.watchit.R
import com.example.watchit.adapters.GridItemDecoration
import com.example.watchit.adapters.MoviesAdpter
import com.example.watchit.viewmodelFactory.MoviesViewModelFactory
import kotlinx.android.synthetic.main.fragment_movies.*
import java.util.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class MoviesFragment : Fragment() {
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var moviesVm: MoviesViewModel
    private lateinit var moviesAdapter: MoviesAdpter
    private lateinit var list : String




    companion object{
        private inline fun <T: Fragment> T.withArgs(
            argsBuilder: Bundle.() -> Unit): T =
           apply {
                arguments = Bundle().apply(argsBuilder)
            }
        const val  EXTRA_LIST = "movies"

        internal  fun newInstance(list: String) = MoviesFragment().withArgs {
            putString(EXTRA_LIST,list)
        }
    }

    @Inject
    lateinit var viewModelFactory: MoviesViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        list = arguments?.getString(EXTRA_LIST) ?: TOP_RATED
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        gridLayoutManager = GridLayoutManager(requireContext(), getColumnsNumber())
         MovieApplication.appCoponent.inject(this)
        moviesVm = ViewModelProviders.of(this,viewModelFactory)[MoviesViewModel::class.java]
      moviesVm.loadMovies(list, Locale.getDefault().language,1)

        val topRatedMoviesList = Observer<List<Movie>> { movies ->
            MovieList.apply {
                moviesAdapter = MoviesAdpter(movies)
                layoutManager = gridLayoutManager
                addItemDecoration(GridItemDecoration(2, 3))
                adapter = moviesAdapter

            }


        }
        moviesVm.topRatedMoviesList.observe(this, topRatedMoviesList)


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
              3
            }


        }
    }


}

