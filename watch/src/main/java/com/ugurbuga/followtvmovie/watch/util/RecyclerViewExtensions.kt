package com.ugurbuga.followtvmovie.watch

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.scrollListener(
    scrollValues: ((visibleItemCount: Int, firstVisibleItemPosition: Int, totalItemCount: Int) -> Unit)?
) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager: LinearLayoutManager = layoutManager as LinearLayoutManager

            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

            scrollValues?.invoke(visibleItemCount, firstVisibleItemPosition, totalItemCount)
        }
    })
}