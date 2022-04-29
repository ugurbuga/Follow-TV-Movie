package com.ugurbuga.followtvmovie.domain.moviedetail.usecase

import com.ugurbuga.followtvmovie.core.base.UseCase
import com.ugurbuga.followtvmovie.core.common.ApiState
import com.ugurbuga.followtvmovie.core.common.map
import com.ugurbuga.followtvmovie.data.repository.movie.MovieRepository
import com.ugurbuga.followtvmovie.domain.moviedetail.mapper.MovieMapper
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.MovieDetailUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieMapper: MovieMapper
) :
    UseCase<GetMovieDetailUseCase.MovieDetailParams, MovieDetailUIModel>() {

    data class MovieDetailParams(val movieId: String)

    override fun execute(params: MovieDetailParams): Flow<ApiState<MovieDetailUIModel>> {
        return movieRepository.getMovieDetail(params.movieId).map {
            it.map { movieDetail ->
                movieMapper.toMovieDetailUIModel(movieDetail)
            }
        }
    }
}