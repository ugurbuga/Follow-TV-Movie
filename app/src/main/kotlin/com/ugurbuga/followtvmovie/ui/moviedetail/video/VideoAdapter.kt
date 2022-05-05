package com.ugurbuga.followtvmovie.ui.moviedetail.video

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ugurbuga.followtvmovie.core.adapter.BaseListAdapter
import com.ugurbuga.followtvmovie.core.adapter.ListAdapterItem
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.VideoUIModel

class VideoAdapter(
    private val onVideoClicked: ((video: VideoUIModel) -> Unit)? = null,
) : BaseListAdapter<ListAdapterItem>(itemsSame = { old, new -> old == new },
    contentsSame = { old, new -> old == new }) {

    override fun onCreateViewHolder(
        parent: ViewGroup, inflater: LayoutInflater, viewType: Int
    ): RecyclerView.ViewHolder {
        return VideoViewHolder(parent, inflater)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is VideoViewHolder) {
            val item = getItem(position) as VideoUIModel
            holder.bind(item, onVideoClicked)
        }
    }
}