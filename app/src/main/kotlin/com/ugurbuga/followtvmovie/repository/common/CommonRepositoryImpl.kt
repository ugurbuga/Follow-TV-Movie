package com.ugurbuga.followtvmovie.repository.common

import com.ugurbuga.followtvmovie.base.FTMBaseRepository
import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.data.api.services.CommonService
import com.ugurbuga.followtvmovie.domain.credit.model.CreditResponse
import com.ugurbuga.followtvmovie.domain.external.model.ExternalIdsResponse
import com.ugurbuga.followtvmovie.domain.image.model.ImageResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.review.ReviewGeneralResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.video.VideosResponse
import com.ugurbuga.followtvmovie.domain.popular.movie.model.PosterGeneralResponse
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class CommonRepositoryImpl @Inject constructor(
    private val commonService: CommonService
) :
    CommonRepository, FTMBaseRepository() {

    override fun getReviews(
        id: String,
        mediaType: String
    ): Flow<ApiState<ReviewGeneralResponse>> {
        return onApiCall { commonService.getReviews(id, mediaType) }
    }

    override fun getExternalIds(
        id: String,
        mediaType: String
    ): Flow<ApiState<ExternalIdsResponse>> {
        return onApiCall { commonService.getExternalIds(id, mediaType) }
    }

    override fun getImages(
        id: String,
        mediaType: String
    ): Flow<ApiState<ImageResponse>> {
        return onApiCall { commonService.getImages(id, mediaType) }
    }

    override fun getCredits(
        id: String,
        mediaType: String
    ): Flow<ApiState<CreditResponse>> {
        return onApiCall { commonService.getCredits(id, mediaType) }
    }

    override fun getVideos(
        id: String,
        mediaType: String
    ): Flow<ApiState<VideosResponse>> {
        return onApiCall { commonService.getVideos(id, mediaType) }
    }

    override fun getSimilar(
        id: String,
        mediaType: String,
        page: Int,
    ): Flow<ApiState<PosterGeneralResponse>> {
        return onApiCall { commonService.getSimilar(id, mediaType, page) }
    }

    override fun getRecommendations(
        id: String,
        mediaType: String,
        page: Int
    ): Flow<ApiState<PosterGeneralResponse>> {
        return onApiCall { commonService.getRecommendations(id, mediaType, page) }
    }

}
