package com.ugurbuga.followtvmovie.ui.discover.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nextlua.corelib.core.adapter.FTMBaseViewHolder
import com.ugurbuga.followtvmovie.databinding.ItemLoadingBinding

class ProgressViewHolder(
    parent: ViewGroup,
    inflater: LayoutInflater
) : FTMBaseViewHolder<ItemLoadingBinding>(
    binding = ItemLoadingBinding.inflate(inflater, parent, false)
)
