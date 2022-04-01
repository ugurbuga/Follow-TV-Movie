package com.ugurbuga.followtvmovie.watch.ui.popularlist.discover

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ugurbuga.followtvmovie.watch.base.FTMBaseListAdapter
import com.ugurbuga.followtvmovie.watch.ui.discover.DiscoverItem

class DiscoverAdapter(
    private val onItemClick: ((item: DiscoverItem) -> Unit)? = null,
) : FTMBaseListAdapter<DiscoverItem>(
    itemsSame = { old, new -> old == new },
    contentsSame = { old, new -> old == new }
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return DiscoverViewHolder(parent, inflater)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DiscoverViewHolder -> {
                val item = getItem(position) as DiscoverItem
                holder.bind(
                    item,
                    onItemClick
                )
            }
        }
    }
}
