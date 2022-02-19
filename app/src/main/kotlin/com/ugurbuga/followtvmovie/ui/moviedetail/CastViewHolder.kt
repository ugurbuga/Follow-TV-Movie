package com.ugurbuga.followtvmovie.ui.moviedetail

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ugurbuga.followtvmovie.base.adapter.FTMBaseViewHolder
import com.ugurbuga.followtvmovie.bindings.executeAfter
import com.ugurbuga.followtvmovie.databinding.ItemCastBinding
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.CastUIModel

class CastViewHolder(
    parent: ViewGroup, inflater: LayoutInflater
) : FTMBaseViewHolder<ItemCastBinding>(
    binding = ItemCastBinding.inflate(inflater, parent, false)
) {
    fun bind(cast: CastUIModel, onCastClicked: ((cast: CastUIModel) -> Unit)?) {
        binding.executeAfter {
            this.item = cast
            root.setOnClickListener {
                onCastClicked?.invoke(cast)
            }
        }
    }
}
