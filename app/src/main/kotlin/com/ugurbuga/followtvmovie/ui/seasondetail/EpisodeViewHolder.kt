package com.ugurbuga.followtvmovie.ui.seasondetail

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ugurbuga.followtvmovie.bindings.executeAfter
import com.ugurbuga.followtvmovie.core.adapter.BaseViewHolder
import com.ugurbuga.followtvmovie.databinding.ItemSeasonBinding
import com.ugurbuga.followtvmovie.domain.tvshowdetail.detail.SeasonUIModel

class EpisodeViewHolder(
    parent: ViewGroup, inflater: LayoutInflater
) : BaseViewHolder<ItemSeasonBinding>(
    binding = ItemSeasonBinding.inflate(inflater, parent, false)
) {
    fun bind(
        season: SeasonUIModel,
        onSeasonClicked: ((season: SeasonUIModel) -> Unit)?,
    ) {
        binding.executeAfter {
            this.item = season
            root.setOnClickListener {
                onSeasonClicked?.invoke(season)
            }
        }
    }
}
