package com.example.watchit.moviesFragment


import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.core.utils.isNetworkAvailabe
import com.example.domain.LocalMovie
import com.example.domain.Movie
import com.example.domain.Movie.Companion.TOP_RATED
import com.example.watchit.MovieApplication
import com.example.watchit.R
import com.example.watchit.Utils
import com.example.watchit.adapters.MoviesAdapter
import com.example.watchit.adapters.OnLoadListener
import com.example.watchit.adapters.RecyclerViewLoadMoreScroll
import com.example.watchit.moviesFragment.favorites.FavoritesFragment
import com.example.watchit.viewmodelFactory.MoviesViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.fragment_search.*
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


/**
 * A simple [Fragment] subclass.
 */
class MoviesFragment : Fragment(), MovieLikedListener, OnLoadListener {
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var moviesVm: MoviesViewModel
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var list: String
    private lateinit var mRecyclerViewLoadMoreScroll: RecyclerViewLoadMoreScroll
    private var movieList: ArrayList<Movie> = ArrayList()
    private var likedMovieList = emptyList<LocalMovie>()


    companion object {
        private inline fun <T : Fragment> T.withArgs(
            argsBuilder: Bundle.() -> Unit
        ): T =
            apply {
                arguments = Bundle().apply(argsBuilder)
            }

        const val EXTRA_LIST = "movies"

        internal fun newInstance(list: String) = MoviesFragment().withArgs {
            putString(EXTRA_LIST, list)
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
        MovieApplication.appCoponent.inject(this)
        moviesVm = ViewModelProviders.of(this, viewModelFactory)[MoviesViewModel::class.java]
        initView()
    }


    override fun onMovieLiked(position: Int) {
        moviesVm.addToFavorite(position)
        moviesAdapter.notifyItemChanged(position)

    }

    override fun onMovieDisliked(position: Int) {
        moviesVm.removeFromFavorites(position)
        moviesAdapter.notifyItemChanged(position)

    }

    private fun getBookmarkedMovies(movies: ArrayList<Movie>): ArrayList<Movie> {

        val result = ArrayList<Movie>()

        if (!likedMovieList.isNullOrEmpty()) {
            for (movie in movies) {
                for (local in likedMovieList) {
                    if (movie.id == local.id)
                        movie.isBookmarked = true

                }
                result.add(movie)
            }

            return result
        }
        return movies
    }

    override fun onLoadMore(page: Int) {
        moviesVm.loadMovies(list, Locale.getDefault().language, page)
        moviesAdapter.notifyDataSetChanged()

    }


    private fun initView() {
        gridLayoutManager =
            GridLayoutManager(requireContext(), Utils.getColumnsNumber(requireActivity()))
        mRecyclerViewLoadMoreScroll = RecyclerViewLoadMoreScroll(gridLayoutManager, this)
        moviesAdapter = MoviesAdapter(movieList)
        MovieList.hasFixedSize()
        if (requireContext().isNetworkAvailabe()) {
            intiMoviesData()
            noInternet.visibility = View.GONE
            btngoToFavorite.visibility = View.GONE
        } else {
            btngoToFavorite.setOnClickListener {
                goToFavorite()
            }
            loading.visibility = View.GONE
            noInternet.visibility = View.VISIBLE
            MovieList.visibility = View.GONE
        }

    }

    private fun intiMoviesData() {

        moviesVm.loadMovies(list, Locale.getDefault().language, 1)
        moviesVm.loadLikedMovies()
        moviesVm.topRatedMoviesList.removeObservers(this)
        moviesVm.likedMoviesList.removeObservers(this)
        moviesVm.loading.removeObservers(this)
        moviesVm.errors.removeObservers(this)
        val topRatedMoviesList = Observer<ArrayList<Movie>> { movies ->
            movieList = getBookmarkedMovies(movies)

            MovieList.apply {
                moviesAdapter = MoviesAdapter(movieList, this@MoviesFragment)
                layoutManager = gridLayoutManager
                adapter = moviesAdapter
            }
        }
        val likedMovies = Observer<List<LocalMovie>> {
            likedMovieList = it
        }

        val loading = Observer<Boolean> {
            if (it) loading.visibility = View.VISIBLE else loading.visibility = View.GONE
        }

        val error = Observer<String> {
            if (it.isNullOrEmpty()) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            }
        }
        moviesVm.topRatedMoviesList.observe(this, topRatedMoviesList)
        moviesVm.likedMoviesList.observe(this, likedMovies)
        moviesVm.loading.observe(this, loading)
        moviesVm.errors.observe(this, error)

    }

    private fun goToFavorite() {
        val fragment = FavoritesFragment()
        val fragmentTransaction =
            requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
            .addToBackStack(null)
        fragmentTransaction.commit()
    }

}






