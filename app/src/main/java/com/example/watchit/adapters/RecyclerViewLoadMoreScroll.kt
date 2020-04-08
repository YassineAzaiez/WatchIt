package com.example.watchit.adapters

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

abstract class RecyclerViewLoadMoreScroll(private var mLayoutManager: RecyclerView.LayoutManager) :
    RecyclerView.OnScrollListener() {

    private var visibleThreshold = 0
    private  var page = 0
    private  var previousTotalItemCount = 20

    private var isLoading: Boolean = true
    private var lastVisibleItem: Int = 0
    private var totalItemCount: Int = 0

    init {
        when (mLayoutManager){
            is GridLayoutManager -> visibleThreshold = (mLayoutManager as GridLayoutManager).spanCount
            is StaggeredGridLayoutManager -> visibleThreshold =  (mLayoutManager as StaggeredGridLayoutManager).spanCount
            else -> visibleThreshold = 5
        }
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        totalItemCount = mLayoutManager.itemCount

        getLastVisibleItemFromLayout()
        if (!isLoading && totalItemCount <= lastVisibleItem + visibleThreshold) {
            onLoadMore(++page)
            isLoading = true
        }

        if(isLoading && (totalItemCount > previousTotalItemCount)){
            isLoading = false
            previousTotalItemCount = totalItemCount
        }


    }


    private fun getLastVisibleItemFromLayout() = when (mLayoutManager) {
        is StaggeredGridLayoutManager -> {
            val lastVisibleItemPositions =
                (mLayoutManager as StaggeredGridLayoutManager).findLastVisibleItemPositions(null)
            lastVisibleItem = getLastVisibleItem(lastVisibleItemPositions)
        }

        is GridLayoutManager -> {
            lastVisibleItem = (mLayoutManager as GridLayoutManager).findLastVisibleItemPosition()
        }

        else -> {
            lastVisibleItem = (mLayoutManager as LinearLayoutManager).findLastVisibleItemPosition()
        }

    }


    private fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }

    fun resetState() {
        page = 1
        previousTotalItemCount = 0
        isLoading = true
    }
    abstract fun onLoadMore(page : Int)
}