package com.ugurbuga.followtvmovie.bindings

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.ugurbuga.followtvmovie.core.adapter.BaseListAdapter
import com.ugurbuga.followtvmovie.core.adapter.ListAdapterItem
import com.ugurbuga.followtvmovie.core.extensions.orZero
import com.ugurbuga.followtvmovie.ui.discover.adapter.SpaceItemDecoration

@Suppress("UNCHECKED_CAST")
@BindingAdapter("submitList")
fun submitList(view: RecyclerView, list: List<ListAdapterItem>?) {
    val adapter = view.adapter as BaseListAdapter<ListAdapterItem>?
    adapter?.submitList(list?.toMutableList())
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("submitList")
fun submitList(view: ViewPager2, list: List<ListAdapterItem>?) {
    val adapter = view.adapter as BaseListAdapter<ListAdapterItem>?
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
            spacePaddingStart?.toInt().orZero(),
            spacePaddingTop?.toInt().orZero(),
            spacePaddingEnd?.toInt().orZero(),
            spacePaddingBottom?.toInt().orZero(),
        )
    )
}
