package com.ugurbuga.followtvmovie.ui.moviedetail

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nextlua.corelib.core.adapter.FTMBaseViewHolder
import com.ugurbuga.followtvmovie.bindings.executeAfter
import com.ugurbuga.followtvmovie.databinding.ItemTrailerBinding
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.TrailerUIModel

class TrailerViewHolder(
    parent: ViewGroup, inflater: LayoutInflater
) : FTMBaseViewHolder<ItemTrailerBinding>(
    binding = ItemTrailerBinding.inflate(inflater, parent, false)
) {
    fun bind(trailer: TrailerUIModel, onTrailerClick: ((trailer: TrailerUIModel) -> Unit)?) {
        binding.executeAfter {
            this.item = trailer
            root.setOnClickListener {
                onTrailerClick?.invoke(trailer)
            }
        }
    }
}
