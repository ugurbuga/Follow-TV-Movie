package com.ugurbuga.followtvmovie.extensions

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nextlua.corelib.core.adapter.FTMBaseListAdapter
import com.ugurbuga.followtvmovie.base.adapter.ListAdapterItem

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

fun <T : ListAdapterItem> FTMBaseListAdapter<T>.updateList(list: MutableList<T>) {
    submitList(list.toMutableList())
}