package com.ugurbuga.followtvmovie.watch.ui.popularlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ugurbuga.followtvmovie.watch.base.BaseViewHolder
import com.ugurbuga.followtvmovie.watch.data.api.ApiConstants
import com.ugurbuga.followtvmovie.watch.databinding.ItemPosterBinding
import com.ugurbuga.followtvmovie.watch.domain.popularlist.MovieResponse
import com.ugurbuga.followtvmovie.watch.extensions.setImageUrl

class PosterViewHolder(
    parent: ViewGroup,
    inflater: LayoutInflater
) : BaseViewHolder<ItemPosterBinding>(
    binding = ItemPosterBinding.inflate(inflater, parent, false)
) {

    fun bind(
        poster: MovieResponse,
        onPosterClick: ((poster: MovieResponse) -> Unit)? = null,
    ) {
        binding.menuItem.text = poster.title
        binding.root.setOnClickListener {
            onPosterClick?.invoke(poster)
        }
        binding.menuIcon.setImageUrl(ApiConstants.BASE_IMAGE_URL + poster.posterPath)
    }
}
