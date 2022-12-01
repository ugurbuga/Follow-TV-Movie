package com.ugurbuga.followtvmovie.watch.ui.discover

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ugurbuga.followtvmovie.core.adapter.BaseViewHolder
import com.ugurbuga.followtvmovie.watch.databinding.ItemDiscoverBinding
import com.ugurbuga.followtvmovie.watch.extensions.setImageUrl

class DiscoverViewHolder(
    parent: ViewGroup,
    inflater: LayoutInflater
) : BaseViewHolder<ItemDiscoverBinding>(
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
        binding.menuIcon.setImageUrl(item.imageResourceId)
    }
}
