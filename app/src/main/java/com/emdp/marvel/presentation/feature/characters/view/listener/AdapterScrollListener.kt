package com.emdp.marvel.presentation.feature.characters.view.listener

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class AdapterScrollListener(
    private val layoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)

        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (!isLoading() &&
            visibleItemCount + firstVisibleItemPosition >= totalItemCount &&
            firstVisibleItemPosition > 0
        ) {
            loadMoreItems()
        }
    }

    protected abstract fun isLoading(): Boolean
    protected abstract fun loadMoreItems()
}