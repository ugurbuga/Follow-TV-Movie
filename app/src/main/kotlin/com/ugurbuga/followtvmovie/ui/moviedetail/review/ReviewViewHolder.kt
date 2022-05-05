package com.ugurbuga.followtvmovie.ui.moviedetail.review

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ugurbuga.followtvmovie.bindings.executeAfter
import com.ugurbuga.followtvmovie.core.adapter.BaseViewHolder
import com.ugurbuga.followtvmovie.databinding.ItemReviewBinding
import com.ugurbuga.followtvmovie.domain.moviedetail.model.review.ReviewUIModel

class ReviewViewHolder(
    parent: ViewGroup,
    inflater: LayoutInflater
) : BaseViewHolder<ItemReviewBinding>(
    binding = ItemReviewBinding.inflate(inflater, parent, false)
) {
    fun bind(review :ReviewUIModel) {
        binding.executeAfter {
            this.item = review
        }
    }
}
