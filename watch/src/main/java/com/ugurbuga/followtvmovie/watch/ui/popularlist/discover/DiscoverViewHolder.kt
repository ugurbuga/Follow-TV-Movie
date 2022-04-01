package com.ugurbuga.followtvmovie.watch.ui.popularlist.discover

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ugurbuga.followtvmovie.watch.base.FTMBaseViewHolder
import com.ugurbuga.followtvmovie.watch.databinding.ItemDiscoverBinding
import com.ugurbuga.followtvmovie.watch.ui.discover.DiscoverItem

class DiscoverViewHolder(
    parent: ViewGroup,
    inflater: LayoutInflater
) : FTMBaseViewHolder<ItemDiscoverBinding>(
    binding = ItemDiscoverBinding.inflate(inflater, parent, false)
) {

    fun bind(
        item: DiscoverItem,
        onItemClick: ((item: DiscoverItem) -> Unit)? = null,
    ) {
        binding.menuItem.text = item.title
        binding.root.setOnClickListener {
            onItemClick?.invoke(item)
        }

        Glide.with(binding.menuIcon)
            .load(item.imageResourceId)
            .circleCrop()
            .into(binding.menuIcon)
    }
}
