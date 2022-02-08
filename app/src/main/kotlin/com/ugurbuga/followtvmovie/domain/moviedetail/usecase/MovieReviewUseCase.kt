package com.ugurbuga.followtvmovie.domain.moviedetail.usecase

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.common.map
import com.ugurbuga.followtvmovie.domain.moviedetail.mapper.MovieMapper
import com.ugurbuga.followtvmovie.domain.moviedetail.model.review.ReviewUIModel
import com.ugurbuga.followtvmovie.repository.movie.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieReviewUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieMapper: MovieMapper
) :
    FTMUseCase<MovieReviewUseCase.MovieReviewParams, List<ReviewUIModel>>() {

    data class MovieReviewParams(val movieId: Int)

    override fun execute(params: MovieReviewParams): Flow<Resource<List<ReviewUIModel>>> {
        return movieRepository.getMovieReviews(params.movieId).map {
            it.map { movieReviewResponse ->
                movieMapper.toReviewUIModelList(movieReviewResponse)
            }
        }
    }
}