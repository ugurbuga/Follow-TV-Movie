package com.ugurbuga.followtvmovie.ui.favorite

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.core.adapter.BaseListAdapter
import com.ugurbuga.followtvmovie.core.adapter.ListAdapterItem
import com.ugurbuga.followtvmovie.domain.poster.model.EmptyUIModel
import com.ugurbuga.followtvmovie.domain.poster.model.LoadingUIModel
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import com.ugurbuga.followtvmovie.ui.discover.adapter.EmptyViewHolder
import com.ugurbuga.followtvmovie.ui.discover.adapter.LoadingViewHolder
import com.ugurbuga.followtvmovie.ui.discover.adapter.PosterHolderType

class FavoriteAdapter(
    context: Context,
    private val onPosterClick: ((poster: PosterItemUIModel, imageView: AppCompatImageView) -> Unit)? = null,
) : BaseListAdapter<ListAdapterItem>(itemsSame = { old, new -> old == new },
    contentsSame = { old, new -> old == new }) {

    private var imageHeight: Double? =
        com.ugurbuga.followtvmovie.core.common.DisplayHelper.getHeightFromRatio(context, R.dimen.padding_48, 1.5, 2)

    override fun onCreateViewHolder(
        parent: ViewGroup, inflater: LayoutInflater, viewType: Int
    ): RecyclerView.ViewHolder {


        when (viewType) {
            PosterHolderType.POSTER -> return FavoriteViewHolder(parent, inflater)

            PosterHolderType.EMPTY -> return EmptyViewHolder(parent, inflater)

            PosterHolderType.LOADING -> return LoadingViewHolder(parent, inflater)
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

            is LoadingViewHolder -> {

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
                com.ugurbuga.followtvmovie.core.common.Util.INVALID_INDEX
            }
        }
    }
}
