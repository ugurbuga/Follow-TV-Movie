package com.ugurbuga.followtvmovie.ui.seasondetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ugurbuga.followtvmovie.core.adapter.BaseListAdapter
import com.ugurbuga.followtvmovie.core.adapter.ListAdapterItem
import com.ugurbuga.followtvmovie.domain.tvshowdetail.detail.SeasonUIModel

class EpisodeAdapter(
    private val onEpisodeClicked: ((season: SeasonUIModel) -> Unit)? = null,
) : BaseListAdapter<ListAdapterItem>(itemsSame = { old, new -> old == new },
    contentsSame = { old, new -> old == new }) {

    override fun onCreateViewHolder(
        parent: ViewGroup, inflater: LayoutInflater, viewType: Int
    ): RecyclerView.ViewHolder {
        return EpisodeViewHolder(parent, inflater)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is EpisodeViewHolder) {
            val item = getItem(position) as SeasonUIModel
            holder.bind(item, onEpisodeClicked)
        }
    }
}