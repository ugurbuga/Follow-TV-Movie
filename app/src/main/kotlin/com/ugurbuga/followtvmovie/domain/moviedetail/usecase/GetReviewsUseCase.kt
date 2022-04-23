package com.ugurbuga.followtvmovie.domain.moviedetail.usecase

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.common.map
import com.ugurbuga.followtvmovie.domain.moviedetail.mapper.ReviewMapper
import com.ugurbuga.followtvmovie.domain.moviedetail.model.review.ReviewUIModel
import com.ugurbuga.followtvmovie.repository.common.CommonRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetReviewsUseCase @Inject constructor(
    private val commonRepository: CommonRepository,
    private val reviewMapper: ReviewMapper
) :
    FTMUseCase<GetReviewsUseCase.ReviewsParams, List<ReviewUIModel>>() {

    data class ReviewsParams(val id: String, val mediaType: String)

    override fun execute(params: ReviewsParams): Flow<ApiState<List<ReviewUIModel>>> {
        return commonRepository.getReviews(params.id, params.mediaType).map {
            it.map { movieReviewResponse ->
                reviewMapper.toReviewUIModelList(movieReviewResponse)
            }
        }
    }
}