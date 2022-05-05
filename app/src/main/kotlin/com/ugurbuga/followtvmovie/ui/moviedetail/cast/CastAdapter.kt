package com.ugurbuga.followtvmovie.ui.moviedetail.cast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.ugurbuga.followtvmovie.core.adapter.BaseListAdapter
import com.ugurbuga.followtvmovie.core.adapter.ListAdapterItem
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.CastUIModel

class CastAdapter(
    private val onCastClicked: ((cast: CastUIModel, imageView: AppCompatImageView) -> Unit)? = null,
) : BaseListAdapter<ListAdapterItem>(itemsSame = { old, new -> old == new },
    contentsSame = { old, new -> old == new }) {

    override fun onCreateViewHolder(
        parent: ViewGroup, inflater: LayoutInflater, viewType: Int
    ): RecyclerView.ViewHolder {
        return CastViewHolder(parent, inflater)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CastViewHolder) {
            val item = getItem(position) as CastUIModel
            holder.bind(item, onCastClicked)
        }
    }
}