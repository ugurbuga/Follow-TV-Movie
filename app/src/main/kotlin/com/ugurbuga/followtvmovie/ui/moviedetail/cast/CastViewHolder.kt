package com.ugurbuga.followtvmovie.ui.moviedetail.cast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.ViewCompat
import com.ugurbuga.followtvmovie.bindings.executeAfter
import com.ugurbuga.followtvmovie.core.adapter.BaseViewHolder
import com.ugurbuga.followtvmovie.databinding.ItemCastBinding
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.CastUIModel

class CastViewHolder(
    parent: ViewGroup, inflater: LayoutInflater
) : BaseViewHolder<ItemCastBinding>(
    binding = ItemCastBinding.inflate(inflater, parent, false)
) {
    fun bind(
        cast: CastUIModel,
        onCastClicked: ((cast: CastUIModel, imageView: AppCompatImageView) -> Unit)?
    ) {
        binding.executeAfter {
            this.item = cast
            ViewCompat.setTransitionName(posterImage, cast.name)

            root.setOnClickListener { onCastClicked?.invoke(cast, posterImage) }
        }
    }
}
