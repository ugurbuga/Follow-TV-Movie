package com.ugurbuga.followtvmovie.ui.discover.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.nextlua.corelib.core.adapter.FTMBaseViewHolder
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.databinding.ItemPosterBinding
import com.ugurbuga.followtvmovie.domain.populartvshow.model.PosterItemUIModel

class PosterViewHolder(
    parent: ViewGroup,
    inflater: LayoutInflater
) : FTMBaseViewHolder<ItemPosterBinding>(
    binding = ItemPosterBinding.inflate(inflater, parent, false)
) {
    fun bind(
        poster: PosterItemUIModel,
        imageHeight: Double?,
        onPosterClick: ((id: Int) -> Unit)? = null
    ) {
        val context = itemView.context

        binding.apply {
            root.setOnClickListener { onPosterClick?.invoke(poster.id) }
            this.item = poster

            val imageViewParams = ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                imageHeight?.toInt() ?: context.resources.getDimensionPixelSize(R.dimen.height_210)
            )
            posterImage.layoutParams = imageViewParams

            executePendingBindings()
        }
    }
}
