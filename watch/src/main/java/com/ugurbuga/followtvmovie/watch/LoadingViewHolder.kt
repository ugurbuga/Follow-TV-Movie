package com.ugurbuga.followtvmovie.watch

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ugurbuga.followtvmovie.watch.databinding.ItemLoadingBinding

class LoadingViewHolder(
    parent: ViewGroup,
    inflater: LayoutInflater
) : FTMBaseViewHolder<ItemLoadingBinding>(
    binding = ItemLoadingBinding.inflate(inflater, parent, false)
)