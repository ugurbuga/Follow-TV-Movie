package com.ugurbuga.followtvmovie.ui.seasons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ugurbuga.followtvmovie.core.adapter.BaseListAdapter
import com.ugurbuga.followtvmovie.core.adapter.ListAdapterItem
import com.ugurbuga.followtvmovie.domain.tvshowdetail.detail.SeasonUIModel

class SeasonAdapter(
    private val onSeasonClicked: ((season: SeasonUIModel) -> Unit)? = null,
) : BaseListAdapter<ListAdapterItem>(itemsSame = { old, new -> old == new },
    contentsSame = { old, new -> old == new }) {

    override fun onCreateViewHolder(
        parent: ViewGroup, inflater: LayoutInflater, viewType: Int
    ): RecyclerView.ViewHolder {
        return SeasonViewHolder(parent, inflater)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SeasonViewHolder) {
            val item = getItem(position) as SeasonUIModel
            holder.bind(item, onSeasonClicked)
        }
    }
}