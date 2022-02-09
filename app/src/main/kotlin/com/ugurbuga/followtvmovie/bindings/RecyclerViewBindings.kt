package com.ugurbuga.followtvmovie.bindings

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nextlua.corelib.core.adapter.FTMBaseListAdapter
import com.ugurbuga.followtvmovie.base.adapter.ListAdapterItem
import com.ugurbuga.followtvmovie.ui.discover.popularlist.adapter.SpaceItemDecoration

@Suppress("UNCHECKED_CAST")
@BindingAdapter("submitList")
fun submitList(view: RecyclerView, list: List<ListAdapterItem>?) {
    val adapter = view.adapter as FTMBaseListAdapter<ListAdapterItem>?
    adapter?.submitList(list?.toMutableList())
}

@BindingAdapter(
    value = ["spacePaddingStart", "spacePaddingTop", "spacePaddingEnd", "spacePaddingBottom"],
    requireAll = false
)
fun RecyclerView.setSpaceBinding(
    spacePaddingStart: Float?,
    spacePaddingTop: Float?,
    spacePaddingEnd: Float?,
    spacePaddingBottom: Float?
) {
    addItemDecoration(
        SpaceItemDecoration(
            spacePaddingStart?.toInt() ?: 0,
            spacePaddingTop?.toInt() ?: 0,
            spacePaddingEnd?.toInt() ?: 0,
            spacePaddingBottom?.toInt() ?: 0,
        )
    )
}
