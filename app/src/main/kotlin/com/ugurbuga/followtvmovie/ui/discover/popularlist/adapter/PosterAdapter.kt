package com.ugurbuga.followtvmovie.ui.discover.popularlist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nextlua.corelib.core.adapter.FTMBaseListAdapter
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.common.DisplayHelper
import com.ugurbuga.followtvmovie.common.Util
import com.ugurbuga.followtvmovie.domain.poster.model.LoadingUIModel
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel

class PosterAdapter(
    context: Context,
    private val onPosterClick: ((id: Int) -> Unit)? = null,
) : FTMBaseListAdapter<Any>(
    itemsSame = { old, new -> old == new },
    contentsSame = { old, new -> old == new }
) {

    private var imageHeight: Double? =
        DisplayHelper.getHeightFromRatio(context, R.dimen.padding_48, 1.5, 2)

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
                val product = getItem(position) as PosterItemUIModel
                holder.bind(
                    product,
                    imageHeight,
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
