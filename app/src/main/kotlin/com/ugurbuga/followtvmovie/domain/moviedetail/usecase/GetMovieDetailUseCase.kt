package com.ugurbuga.followtvmovie.domain.moviedetail.usecase

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.common.map
import com.ugurbuga.followtvmovie.domain.moviedetail.mapper.MovieMapper
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.MovieDetailUIModel
import com.ugurbuga.followtvmovie.repository.movie.MovieRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetMovieDetailUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieMapper: MovieMapper
) :
    FTMUseCase<GetMovieDetailUseCase.MovieDetailParams, MovieDetailUIModel>() {

    data class MovieDetailParams(val movieId: String)

    override fun execute(params: MovieDetailParams): Flow<ApiState<MovieDetailUIModel>> {
        return movieRepository.getMovieDetail(params.movieId).map {
            it.map { movieDetail ->
                movieMapper.toMovieDetailUIModel(movieDetail)
            }
        }
    }
}