package com.ugurbuga.followtvmovie.repository.common

import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.domain.moviedetail.model.review.ReviewGeneralResponse
import kotlinx.coroutines.flow.Flow

interface CommonRepository {

    fun getReviews(id: String, mediaType: String): Flow<ApiState<ReviewGeneralResponse>>
}