package com.ugurbuga.followtvmovie.bindings

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.ugurbuga.followtvmovie.base.adapter.FTMBaseListAdapter
import com.ugurbuga.followtvmovie.base.adapter.ListAdapterItem
import com.ugurbuga.followtvmovie.ui.discover.adapter.SpaceItemDecoration

@Suppress("UNCHECKED_CAST")
@BindingAdapter("submitList")
fun submitList(view: RecyclerView, list: List<ListAdapterItem>?) {
    val adapter = view.adapter as FTMBaseListAdapter<ListAdapterItem>?
    adapter?.submitList(list?.toMutableList())
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("submitList")
fun submitList(view: ViewPager2, list: List<ListAdapterItem>?) {
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
