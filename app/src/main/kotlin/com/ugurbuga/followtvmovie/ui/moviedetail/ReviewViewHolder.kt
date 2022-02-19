package com.ugurbuga.followtvmovie.ui.moviedetail

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nextlua.corelib.core.adapter.FTMBaseViewHolder
import com.ugurbuga.followtvmovie.bindings.executeAfter
import com.ugurbuga.followtvmovie.databinding.ItemReviewBinding
import com.ugurbuga.followtvmovie.domain.moviedetail.model.review.ReviewUIModel

class ReviewViewHolder(
    parent: ViewGroup,
    inflater: LayoutInflater
) : FTMBaseViewHolder<ItemReviewBinding>(
    binding = ItemReviewBinding.inflate(inflater, parent, false)
) {
    fun bind(review :ReviewUIModel) {
        binding.executeAfter {
            this.item = review
        }
    }
}