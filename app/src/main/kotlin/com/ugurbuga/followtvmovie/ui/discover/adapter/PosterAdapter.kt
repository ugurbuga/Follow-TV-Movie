package com.ugurbuga.followtvmovie.ui.discover.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.ugurbuga.followtvmovie.base.adapter.FTMBaseListAdapter
import com.ugurbuga.followtvmovie.base.adapter.ListAdapterItem
import com.ugurbuga.followtvmovie.common.Util
import com.ugurbuga.followtvmovie.domain.poster.model.LoadingUIModel
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel

class PosterAdapter(
    private val onPosterClick: ((poster: PosterItemUIModel, imageView: AppCompatImageView) -> Unit)? = null,
) : FTMBaseListAdapter<ListAdapterItem>(
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

            PosterHolderType.LOADING -> return ProgressViewHolder(parent, inflater)
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

            is ProgressViewHolder -> {
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
