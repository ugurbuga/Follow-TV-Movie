package com.ugurbuga.followtvmovie.ui.moviedetail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.adapter.FTMBaseViewHolder
import com.ugurbuga.followtvmovie.bindings.executeAfter
import com.ugurbuga.followtvmovie.databinding.ItemImageBinding
import com.ugurbuga.followtvmovie.domain.image.model.ImageUIModel

class ImageViewHolder(
    parent: ViewGroup, inflater: LayoutInflater
) : FTMBaseViewHolder<ItemImageBinding>(
    binding = ItemImageBinding.inflate(inflater, parent, false)
) {
    fun bind(
        image: ImageUIModel,
        onImageClicked: ((image: ImageUIModel, position: Int) -> Unit)?,
        isFullScreen: Boolean
    ) {
        binding.executeAfter {
            this.item = image

            if (!isFullScreen) {
                val height =
                    posterLayout.context.resources.getDimensionPixelOffset(R.dimen.height_130)
                val width = (height * image.aspectRatio).toInt()
                posterLayout.layoutParams = LinearLayout.LayoutParams(width, height)
                posterImage.setOnClickListener {
                    onImageClicked?.invoke(image, position)
                }
            }
        }
    }
}
