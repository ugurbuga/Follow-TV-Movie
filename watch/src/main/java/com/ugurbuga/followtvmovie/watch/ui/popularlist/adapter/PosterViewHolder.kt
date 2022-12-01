package com.ugurbuga.followtvmovie.watch.ui.popularlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ugurbuga.followtvmovie.core.adapter.BaseViewHolder
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import com.ugurbuga.followtvmovie.watch.databinding.ItemPosterBinding
import com.ugurbuga.followtvmovie.watch.extensions.setImageUrl

class PosterViewHolder(
    parent: ViewGroup,
    inflater: LayoutInflater
) : BaseViewHolder<ItemPosterBinding>(
    binding = ItemPosterBinding.inflate(inflater, parent, false)
) {

    fun bind(
        poster: PosterItemUIModel,
        onPosterClick: ((poster: PosterItemUIModel) -> Unit)? = null,
    ) {
        binding.menuItem.text = poster.name
        binding.root.setOnClickListener {
            onPosterClick?.invoke(poster)
        }
        binding.menuIcon.setImageUrl(poster.posterPath)
    }
}
