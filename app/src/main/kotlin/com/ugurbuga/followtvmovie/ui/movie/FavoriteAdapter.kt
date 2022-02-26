package com.ugurbuga.followtvmovie.ui.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.adapter.FTMBaseListAdapter
import com.ugurbuga.followtvmovie.base.adapter.ListAdapterItem
import com.ugurbuga.followtvmovie.common.DisplayHelper
import com.ugurbuga.followtvmovie.common.Util
import com.ugurbuga.followtvmovie.domain.poster.model.EmptyUIModel
import com.ugurbuga.followtvmovie.domain.poster.model.LoadingUIModel
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import com.ugurbuga.followtvmovie.ui.discover.adapter.EmptyViewHolder
import com.ugurbuga.followtvmovie.ui.discover.adapter.PosterHolderType
import com.ugurbuga.followtvmovie.ui.discover.adapter.ProgressViewHolder

class FavoriteAdapter(
    context: Context,
    private val onPosterClick: ((poster: PosterItemUIModel, imageView: AppCompatImageView) -> Unit)? = null,
) : FTMBaseListAdapter<ListAdapterItem>(itemsSame = { old, new -> old == new },
    contentsSame = { old, new -> old == new }) {

    private var imageHeight: Double? =
        DisplayHelper.getHeightFromRatio(context, R.dimen.padding_48, 1.5, 2)

    override fun onCreateViewHolder(
        parent: ViewGroup, inflater: LayoutInflater, viewType: Int
    ): RecyclerView.ViewHolder {


        when (viewType) {
            PosterHolderType.POSTER -> return FavoriteViewHolder(parent, inflater)

            PosterHolderType.EMPTY -> return EmptyViewHolder(parent, inflater)

            PosterHolderType.LOADING -> return ProgressViewHolder(parent, inflater)
        }

        throw IllegalStateException("View Error")

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FavoriteViewHolder -> {
                val item = getItem(position) as PosterItemUIModel
                holder.bind(item, imageHeight, onPosterClick)
            }

            is EmptyViewHolder -> {
                val item = getItem(position) as EmptyUIModel
                holder.bind(item)
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
            is EmptyUIModel -> {
                PosterHolderType.EMPTY
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
