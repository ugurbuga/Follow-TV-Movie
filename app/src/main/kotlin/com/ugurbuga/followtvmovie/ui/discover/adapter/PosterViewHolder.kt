package com.ugurbuga.followtvmovie.ui.discover.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.ViewCompat
import com.ugurbuga.followtvmovie.bindings.executeAfter
import com.ugurbuga.followtvmovie.core.adapter.BaseViewHolder
import com.ugurbuga.followtvmovie.databinding.ItemPosterBinding
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel

class PosterViewHolder(
    parent: ViewGroup,
    inflater: LayoutInflater
) : BaseViewHolder<ItemPosterBinding>(
    binding = ItemPosterBinding.inflate(inflater, parent, false)
) {
    fun bind(
        poster: PosterItemUIModel,
        onPosterClick: ((poster: PosterItemUIModel, imageView: AppCompatImageView) -> Unit)? = null,
    ) {
        binding.executeAfter {
            this.item = poster

            ViewCompat.setTransitionName(posterImage, poster.name)

            root.setOnClickListener { onPosterClick?.invoke(poster, posterImage) }
        }
    }
}
