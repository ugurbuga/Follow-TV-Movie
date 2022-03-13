package com.ugurbuga.followtvmovie.watch

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ugurbuga.followtvmovie.watch.databinding.ItemPosterBinding

class PosterViewHolder(
    parent: ViewGroup,
    inflater: LayoutInflater
) : FTMBaseViewHolder<ItemPosterBinding>(
    binding = ItemPosterBinding.inflate(inflater, parent, false)
) {

    val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"

    fun bind(
        poster: MovieResponse,
        onPosterClick: ((poster: MovieResponse) -> Unit)? = null,
    ) {
        binding.menuItem.text = poster.title

        Glide.with(binding.menuIcon).load(BASE_IMAGE_URL + poster.posterPath).circleCrop().into(binding.menuIcon)
    }
}
