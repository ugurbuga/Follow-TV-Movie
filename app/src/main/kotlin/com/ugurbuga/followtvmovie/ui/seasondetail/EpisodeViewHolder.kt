package com.ugurbuga.followtvmovie.ui.seasondetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.bindings.executeAfter
import com.ugurbuga.followtvmovie.core.adapter.BaseViewHolder
import com.ugurbuga.followtvmovie.core.extensions.dimenToPx
import com.ugurbuga.followtvmovie.databinding.ItemEpisodePosterBinding
import com.ugurbuga.followtvmovie.domain.seasondetail.model.EpisodeUIModel

class EpisodeViewHolder(
    parent: ViewGroup,
    inflater: LayoutInflater
) : BaseViewHolder<ItemEpisodePosterBinding>(
    binding = ItemEpisodePosterBinding.inflate(inflater, parent, false)
) {
    fun bind(
        episode: EpisodeUIModel,
        imageHeight: Double?,
        onEpisodeClicked: ((episode: EpisodeUIModel, imageView: AppCompatImageView) -> Unit)? = null,
    ) {
        binding.executeAfter {
            this.item = episode

            val imageViewParams = ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                imageHeight?.toInt() ?: dimenToPx(R.dimen.height_210)
            )
            posterImage.layoutParams = imageViewParams
            ViewCompat.setTransitionName(posterImage, episode.name)

            root.setOnClickListener { onEpisodeClicked?.invoke(episode, posterImage) }
        }
    }
}
