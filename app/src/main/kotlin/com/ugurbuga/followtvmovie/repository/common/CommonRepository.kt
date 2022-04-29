package com.ugurbuga.followtvmovie.repository.common

import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.data.model.response.credit.CreditResponse
import com.ugurbuga.followtvmovie.data.model.response.external.ExternalIdsResponse
import com.ugurbuga.followtvmovie.data.model.response.image.ImageResponse
import com.ugurbuga.followtvmovie.data.model.response.review.ReviewGeneralResponse
import com.ugurbuga.followtvmovie.data.model.response.video.VideosResponse
import com.ugurbuga.followtvmovie.data.model.response.popularmovie.PosterGeneralResponse
import kotlinx.coroutines.flow.Flow

interface CommonRepository {

    fun getReviews(id: String, mediaType: String): Flow<ApiState<ReviewGeneralResponse>>

    fun getExternalIds(id: String, mediaType: String): Flow<ApiState<ExternalIdsResponse>>

    fun getImages(id: String, mediaType: String): Flow<ApiState<ImageResponse>>

    fun getCredits(id: String, mediaType: String): Flow<ApiState<CreditResponse>>

    fun getVideos(id: String, mediaType: String): Flow<ApiState<VideosResponse>>

    fun getSimilar(id: String, mediaType: String, page: Int): Flow<ApiState<PosterGeneralResponse>>

    fun getRecommendations(
        id: String,
        mediaType: String,
        page: Int
    ): Flow<ApiState<PosterGeneralResponse>>
}
