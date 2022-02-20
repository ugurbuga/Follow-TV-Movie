package com.ugurbuga.followtvmovie.ui.discover.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ugurbuga.followtvmovie.base.adapter.FTMBaseViewHolder
import com.ugurbuga.followtvmovie.databinding.ItemEmptyBinding

class EmptyViewHolder(
    parent: ViewGroup,
    inflater: LayoutInflater
) : FTMBaseViewHolder<ItemEmptyBinding>(
    binding = ItemEmptyBinding.inflate(inflater, parent, false)
)
