package com.ugurbuga.followtvmovie.ui.moviedetail.review

import com.ugurbuga.followtvmovie.domain.moviedetail.model.review.ReviewUIModel

data class MovieReviewViewState(val reviewList: List<ReviewUIModel>? = null) {

    fun isEmpty(): Boolean {
        return reviewList != null && reviewList.isEmpty()
    }
}