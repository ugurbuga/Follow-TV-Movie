package com.ugurbuga.followtvmovie.domain.moviedetail.usecase

import com.ugurbuga.followtvmovie.core.base.UseCase
import com.ugurbuga.followtvmovie.core.common.ApiState
import com.ugurbuga.followtvmovie.core.common.map
import com.ugurbuga.followtvmovie.data.repository.common.CommonRepository
import com.ugurbuga.followtvmovie.domain.moviedetail.mapper.ReviewMapper
import com.ugurbuga.followtvmovie.domain.moviedetail.model.review.ReviewUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetReviewsUseCase @Inject constructor(
    private val commonRepository: CommonRepository,
    private val reviewMapper: ReviewMapper
) :
    UseCase<GetReviewsUseCase.ReviewsParams, List<ReviewUIModel>>() {

    data class ReviewsParams(val id: String, val mediaType: String)

    override fun execute(params: ReviewsParams): Flow<ApiState<List<ReviewUIModel>>> {
        return commonRepository.getReviews(params.id, params.mediaType).map {
            it.map { movieReviewResponse ->
                reviewMapper.toReviewUIModelList(movieReviewResponse)
            }
        }
    }
}