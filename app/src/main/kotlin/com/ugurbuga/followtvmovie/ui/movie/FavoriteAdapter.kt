package com.ugurbuga.followtvmovie.ui.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.nextlua.corelib.core.adapter.FTMBaseListAdapter
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.base.adapter.ListAdapterItem
import com.ugurbuga.followtvmovie.common.DisplayHelper
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel

class FavoriteAdapter(
    context: Context,
    private val onPosterClick: ((poster: PosterItemUIModel, imageView: AppCompatImageView) -> Unit)? = null,
) : FTMBaseListAdapter<ListAdapterItem>(
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
        return FavoriteViewHolder(parent, inflater)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FavoriteViewHolder -> {
                val product = getItem(position) as PosterItemUIModel
                holder.bind(
                    product,
                    imageHeight,
                    onPosterClick
                )
            }
        }
    }
}
