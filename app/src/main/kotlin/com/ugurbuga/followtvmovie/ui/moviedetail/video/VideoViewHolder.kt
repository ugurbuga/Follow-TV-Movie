package com.ugurbuga.followtvmovie.ui.moviedetail.video

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ugurbuga.followtvmovie.bindings.executeAfter
import com.ugurbuga.followtvmovie.core.adapter.BaseViewHolder
import com.ugurbuga.followtvmovie.databinding.ItemVideoBinding
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.VideoUIModel

class VideoViewHolder(
    parent: ViewGroup, inflater: LayoutInflater
) : BaseViewHolder<ItemVideoBinding>(
    binding = ItemVideoBinding.inflate(inflater, parent, false)
) {
    fun bind(video: VideoUIModel, onVideoClicked: ((video: VideoUIModel) -> Unit)?) {
        binding.executeAfter {
            this.item = video
            root.setOnClickListener {
                onVideoClicked?.invoke(video)
            }
        }
    }
}
