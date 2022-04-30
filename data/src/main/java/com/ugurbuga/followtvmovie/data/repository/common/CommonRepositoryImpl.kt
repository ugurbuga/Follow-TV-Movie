package com.ugurbuga.followtvmovie.data.repository.common

import com.ugurbuga.followtvmovie.core.base.BaseRepository
import com.ugurbuga.followtvmovie.core.common.ApiState
import com.ugurbuga.followtvmovie.data.api.services.CommonService
import com.ugurbuga.followtvmovie.data.model.response.credit.CreditResponse
import com.ugurbuga.followtvmovie.data.model.response.external.ExternalIdsResponse
import com.ugurbuga.followtvmovie.data.model.response.image.ImageResponse
import com.ugurbuga.followtvmovie.data.model.response.popularmovie.PosterGeneralResponse
import com.ugurbuga.followtvmovie.data.model.response.review.ReviewGeneralResponse
import com.ugurbuga.followtvmovie.data.model.response.video.VideosResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CommonRepositoryImpl @Inject constructor(
    private val commonService: CommonService
) :
    CommonRepository, BaseRepository() {

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
