package com.ugurbuga.followtvmovie.repository.common

import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.domain.credit.model.CreditResponse
import com.ugurbuga.followtvmovie.domain.external.model.ExternalIdsResponse
import com.ugurbuga.followtvmovie.domain.image.model.ImageResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.review.ReviewGeneralResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.video.VideosResponse
import com.ugurbuga.followtvmovie.domain.popular.movie.model.PosterGeneralResponse
import kotlinx.coroutines.flow.Flow

interface CommonRepository {

    fun getReviews(id: String, mediaType: String): Flow<ApiState<ReviewGeneralResponse>>

    fun getExternalIds(id: String, mediaType: String): Flow<ApiState<ExternalIdsResponse>>

    fun getImages(id: String, mediaType: String): Flow<ApiState<ImageResponse>>

    fun getCredits(id: String, mediaType: String): Flow<ApiState<CreditResponse>>

    fun getVideos(id: String, mediaType: String): Flow<ApiState<VideosResponse>>

    fun getSimilar(id: String, mediaType: String, page: Int): Flow<ApiState<PosterGeneralResponse>>
}
