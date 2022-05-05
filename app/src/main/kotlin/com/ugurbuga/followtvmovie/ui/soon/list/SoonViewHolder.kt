package com.ugurbuga.followtvmovie.ui.soon.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.ViewCompat
import com.ugurbuga.followtvmovie.bindings.executeAfter
import com.ugurbuga.followtvmovie.core.adapter.BaseViewHolder
import com.ugurbuga.followtvmovie.databinding.ItemSoonBinding
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel

class SoonViewHolder(
    parent: ViewGroup,
    inflater: LayoutInflater
) : BaseViewHolder<ItemSoonBinding>(
    binding = ItemSoonBinding.inflate(inflater, parent, false)
) {
    fun bind(
        poster: PosterItemUIModel,
        onPosterClick: ((poster: PosterItemUIModel, imageView: AppCompatImageView) -> Unit)? = null,
        isFirst: Boolean,
        isLast: Boolean,
    ) {
        binding.executeAfter {
            item = poster
            this.isFirst = isFirst
            this.isLast = isLast

            ViewCompat.setTransitionName(image, poster.name)

            root.setOnClickListener { onPosterClick?.invoke(poster, image) }
        }
    }
}
