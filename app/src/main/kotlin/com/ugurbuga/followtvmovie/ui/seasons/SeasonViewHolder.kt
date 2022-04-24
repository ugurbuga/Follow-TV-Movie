package com.ugurbuga.followtvmovie.ui.seasons

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ugurbuga.followtvmovie.base.adapter.FTMBaseViewHolder
import com.ugurbuga.followtvmovie.bindings.executeAfter
import com.ugurbuga.followtvmovie.databinding.ItemSeasonBinding
import com.ugurbuga.followtvmovie.domain.tvshowdetail.detail.SeasonUIModel

class SeasonViewHolder(
    parent: ViewGroup, inflater: LayoutInflater
) : FTMBaseViewHolder<ItemSeasonBinding>(
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
