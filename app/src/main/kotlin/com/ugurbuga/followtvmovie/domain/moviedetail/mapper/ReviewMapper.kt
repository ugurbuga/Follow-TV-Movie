package com.ugurbuga.followtvmovie.domain.moviedetail.mapper

import com.ugurbuga.followtvmovie.domain.moviedetail.model.review.ReviewGeneralResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.review.ReviewResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.review.ReviewUIModel
import javax.inject.Inject

class ReviewMapper @Inject constructor() {

    fun toReviewUIModelList(response: ReviewGeneralResponse): List<ReviewUIModel> {
        return response.results.map { toReviewUI(it) }
    }

    private fun toReviewUI(response: ReviewResponse): ReviewUIModel {
        return ReviewUIModel(
            author = response.author,
            content = response.content,
            createdAt = response.createdAt,
            id = response.id
        )
    }
}
