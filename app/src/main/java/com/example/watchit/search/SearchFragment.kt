package com.example.watchit.search

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Movie
import com.example.watchit.MovieApplication

import com.example.watchit.R
import com.example.watchit.Utils
import com.example.watchit.adapters.MoviesAdapter
import com.example.watchit.adapters.OnLoadListener
import com.example.watchit.adapters.RecyclerViewLoadMoreScroll
import com.example.watchit.viewmodelFactory.MoviesViewModelFactory
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.snippet_search_toolbar.*
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment() , OnLoadListener{

    private lateinit var searchViewModel: SearchViewModel
    private lateinit var searchAdapter: MoviesAdapter
    private lateinit var gridLayoutManager: GridLayoutManager
    @Inject
    lateinit var viewModelFactory: MoviesViewModelFactory
    private var searchQuery : String ? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MovieApplication.appCoponent.inject(this)
        searchViewModel = ViewModelProviders.of(this,viewModelFactory)[SearchViewModel::class.java]
        searchAdapter = MoviesAdapter()
        searchList.hasFixedSize()
        gridLayoutManager = GridLayoutManager(requireContext(),Utils.getColumnsNumber(requireActivity()))
        searchList.addOnScrollListener(RecyclerViewLoadMoreScroll(gridLayoutManager,this))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            moviesSearchToolbar.navigationIcon =
                requireActivity().getDrawable(R.drawable.ic_arrow_back)
        }

        moviesSearchToolbar.setNavigationOnClickListener { requireActivity().finish() }

        searchEditText.setOnEditorActionListener{v, actionId, event ->
             if(event != null && event.keyCode == KeyEvent.KEYCODE_ENTER ||  actionId == EditorInfo.IME_ACTION_SEARCH){
                 searchQuery(v.text.toString().trim())
                 hideSoftKeyBoard(v)
                 return@setOnEditorActionListener  true
             }
            return@setOnEditorActionListener  false
        }



        val searchResult = Observer<ArrayList<Movie>>{movies->
            searchList.apply {
                searchAdapter  = MoviesAdapter(movies)
                layoutManager = gridLayoutManager
                adapter = searchAdapter
            }
        }

        searchViewModel.searchMoviesResult.observe(this,searchResult)

    }


    private fun searchQuery(query: String) {
        searchQuery = query
        searchAdapter.clear()
        noResult.visibility = View.GONE
        searchViewModel.getSearchResult(Locale.getDefault().language,query,1,true)


    }

  private fun  hideSoftKeyBoard(view : View){
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onLoadMore(page: Int) {
        searchQuery(searchQuery!!)
    }
}
