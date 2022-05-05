package com.ugurbuga.followtvmovie.ui.moviedetail.genre

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ugurbuga.followtvmovie.bindings.executeAfter
import com.ugurbuga.followtvmovie.core.adapter.BaseViewHolder
import com.ugurbuga.followtvmovie.databinding.ItemGenreBinding
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.GenreUIModel

class GenreViewHolder(
    parent: ViewGroup,
    inflater: LayoutInflater
) : BaseViewHolder<ItemGenreBinding>(
    binding = ItemGenreBinding.inflate(inflater, parent, false)
) {
    fun bind(genre: GenreUIModel) {
        binding.executeAfter {
            this.item = genre
        }
    }
}
