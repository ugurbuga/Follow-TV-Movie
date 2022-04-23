package com.ugurbuga.followtvmovie.repository.common

import com.ugurbuga.followtvmovie.base.FTMBaseRepository
import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.data.api.services.CommonService
import com.ugurbuga.followtvmovie.domain.moviedetail.model.review.ReviewGeneralResponse
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

}
