package com.ugurbuga.followtvmovie.domain.moviedetail.usecase

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.common.map
import com.ugurbuga.followtvmovie.domain.moviedetail.mapper.MovieMapper
import com.ugurbuga.followtvmovie.domain.moviedetail.model.review.ReviewUIModel
import com.ugurbuga.followtvmovie.repository.movie.MovieRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetMovieReviewsUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieMapper: MovieMapper
) :
    FTMUseCase<GetMovieReviewsUseCase.MovieReviewsParams, List<ReviewUIModel>>() {

    data class MovieReviewsParams(val movieId: String)

    override fun execute(params: MovieReviewsParams): Flow<Resource<List<ReviewUIModel>>> {
        return movieRepository.getMovieReviews(params.movieId).map {
            it.map { movieReviewResponse ->
                movieMapper.toReviewUIModelList(movieReviewResponse)
            }
        }
    }
}