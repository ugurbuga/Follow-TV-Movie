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
    value = ["spacePaddingLeft", "spacePaddingTop", "spacePaddingRight", "spacePaddingBottom"],
    requireAll = false
)
fun RecyclerView.setSpaceBinding(
    spacePaddingLeft: Float?,
    spacePaddingTop: Float?,
    spacePaddingRight: Float?,
    spacePaddingBottom: Float?
) {
    addItemDecoration(
        SpaceItemDecoration(
            spacePaddingLeft?.toInt() ?: 0,
            spacePaddingTop?.toInt() ?: 0,
            spacePaddingRight?.toInt() ?: 0,
            spacePaddingBottom?.toInt() ?: 0,
        )
    )
}
