package com.ugurbuga.followtvmovie.watch.popularlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ugurbuga.followtvmovie.watch.base.FTMBaseViewHolder
import com.ugurbuga.followtvmovie.watch.data.api.ApiConstants
import com.ugurbuga.followtvmovie.watch.databinding.ItemPosterBinding
import com.ugurbuga.followtvmovie.watch.popularlist.model.MovieResponse

class PosterViewHolder(
    parent: ViewGroup,
    inflater: LayoutInflater
) : FTMBaseViewHolder<ItemPosterBinding>(
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

        Glide.with(binding.menuIcon)
            .load(ApiConstants.BASE_IMAGE_URL + poster.posterPath)
            .circleCrop()
            .into(binding.menuIcon)
    }
}
