package com.ugurbuga.followtvmovie.ui.discover.popularlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import com.nextlua.corelib.core.adapter.FTMBaseViewHolder
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.databinding.ItemPosterBinding
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel

class PosterViewHolder(
    parent: ViewGroup,
    inflater: LayoutInflater
) : FTMBaseViewHolder<ItemPosterBinding>(
    binding = ItemPosterBinding.inflate(inflater, parent, false)
) {
    fun bind(
        poster: PosterItemUIModel,
        imageHeight: Double?,
        onPosterClick: ((poster: PosterItemUIModel, imageView: AppCompatImageView) -> Unit)? = null,
    ) {
        val context = itemView.context

        binding.apply {
            this.item = poster

            val imageViewParams = ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                imageHeight?.toInt() ?: context.resources.getDimensionPixelSize(R.dimen.height_210)
            )
            posterImage.layoutParams = imageViewParams
            ViewCompat.setTransitionName(posterImage, poster.name)

            root.setOnClickListener { onPosterClick?.invoke(poster, posterImage) }

            executePendingBindings()
        }
    }
}
