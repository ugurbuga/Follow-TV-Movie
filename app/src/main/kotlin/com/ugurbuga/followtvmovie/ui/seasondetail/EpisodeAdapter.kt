package com.ugurbuga.followtvmovie.ui.seasondetail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.core.adapter.BaseListAdapter
import com.ugurbuga.followtvmovie.core.common.DisplayHelper
import com.ugurbuga.followtvmovie.domain.seasondetail.model.EpisodeUIModel

class EpisodeAdapter(
    context: Context,
    private val onEpisodeClicked: ((episode: EpisodeUIModel, imageView: AppCompatImageView) -> Unit)? = null,
) : BaseListAdapter<EpisodeUIModel>(itemsSame = { old, new -> old == new },
    contentsSame = { old, new -> old == new }) {

    private var imageHeight: Double? =
        DisplayHelper.getHeightFromRatio(context, R.dimen.padding_48, 1.5, 2)

    override fun onCreateViewHolder(
        parent: ViewGroup, inflater: LayoutInflater, viewType: Int
    ): RecyclerView.ViewHolder {
        return EpisodeViewHolder(parent, inflater)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is EpisodeViewHolder) {
            val item = getItem(position)
            holder.bind(item, imageHeight,onEpisodeClicked)
        }
    }
}