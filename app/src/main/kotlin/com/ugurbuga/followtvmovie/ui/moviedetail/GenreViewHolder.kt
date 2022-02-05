package com.ugurbuga.followtvmovie.ui.moviedetail

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nextlua.corelib.core.adapter.FTMBaseViewHolder
import com.ugurbuga.followtvmovie.bindings.executeAfter
import com.ugurbuga.followtvmovie.databinding.ItemGenreBinding
import com.ugurbuga.followtvmovie.domain.moviedetail.model.GenreUIModel

class GenreViewHolder(
    parent: ViewGroup,
    inflater: LayoutInflater
) : FTMBaseViewHolder<ItemGenreBinding>(
    binding = ItemGenreBinding.inflate(inflater, parent, false)
) {
    fun bind(genre: GenreUIModel) {
        binding.executeAfter {
            this.item = genre
        }
    }
}
