package com.ugurbuga.followtvmovie.ui.moviedetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ugurbuga.followtvmovie.base.adapter.FTMBaseListAdapter
import com.ugurbuga.followtvmovie.base.adapter.ListAdapterItem
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.TrailerUIModel

class TrailerAdapter(
    private val onTrailerClicked: ((trailer: TrailerUIModel) -> Unit)? = null,
) : FTMBaseListAdapter<ListAdapterItem>(itemsSame = { old, new -> old == new },
    contentsSame = { old, new -> old == new }) {

    override fun onCreateViewHolder(
        parent: ViewGroup, inflater: LayoutInflater, viewType: Int
    ): RecyclerView.ViewHolder {
        return TrailerViewHolder(parent, inflater)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TrailerViewHolder) {
            val item = getItem(position) as TrailerUIModel
            holder.bind(item, onTrailerClicked)
        }
    }
}