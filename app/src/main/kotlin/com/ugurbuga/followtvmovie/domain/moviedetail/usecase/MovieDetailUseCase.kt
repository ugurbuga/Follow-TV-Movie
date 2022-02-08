package com.ugurbuga.followtvmovie.domain.moviedetail.usecase

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.common.map
import com.ugurbuga.followtvmovie.domain.moviedetail.mapper.MovieMapper
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.MovieDetailUIModel
import com.ugurbuga.followtvmovie.repository.movie.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieMapper: MovieMapper
) :
    FTMUseCase<MovieDetailUseCase.MovieDetailParams, MovieDetailUIModel>() {

    data class MovieDetailParams(val movieId: Int)

    override fun execute(params: MovieDetailParams): Flow<Resource<MovieDetailUIModel>> {
        return movieRepository.getMovieDetail(params.movieId).map {
            it.map { movieDetail ->
                movieMapper.toMovieDetailUIModel(movieDetail)
            }
        }
    }
}