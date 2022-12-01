package com.ugurbuga.followtvmovie.watch.ui.popularlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ugurbuga.followtvmovie.core.adapter.BaseListAdapter
import com.ugurbuga.followtvmovie.core.adapter.ListAdapterItem
import com.ugurbuga.followtvmovie.domain.poster.model.LoadingUIModel
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import com.ugurbuga.followtvmovie.watch.ui.popularlist.model.PosterHolderType
import com.ugurbuga.followtvmovie.watch.util.Util

class PosterAdapter(
    private val onPosterClick: ((poster: PosterItemUIModel) -> Unit)? = null,
) : BaseListAdapter<ListAdapterItem>(
    itemsSame = { old, new -> old == new },
    contentsSame = { old, new -> old == new }
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {

        when (viewType) {
            PosterHolderType.POSTER -> return PosterViewHolder(parent, inflater)

            PosterHolderType.LOADING -> return LoadingViewHolder(parent, inflater)
        }
        throw IllegalStateException("View Error")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PosterViewHolder -> {
                val item = getItem(position) as PosterItemUIModel
                holder.bind(
                    item,
                    onPosterClick
                )
            }

            is LoadingViewHolder -> {
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is PosterItemUIModel -> {
                PosterHolderType.POSTER
            }
            is LoadingUIModel -> {
                PosterHolderType.LOADING
            }
            else -> {
                Util.INVALID_INDEX
            }
        }
    }
}
