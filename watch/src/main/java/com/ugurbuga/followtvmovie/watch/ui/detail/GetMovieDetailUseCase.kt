package com.ugurbuga.followtvmovie.watch.ui.detail

import com.ugurbuga.followtvmovie.watch.base.FTMUseCase
import com.ugurbuga.followtvmovie.watch.repository.movie.MovieRepository
import com.ugurbuga.followtvmovie.watch.util.Resource
import com.ugurbuga.followtvmovie.watch.util.map
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetMovieDetailUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieMapper: MovieMapper
) :
    FTMUseCase<GetMovieDetailUseCase.MovieDetailParams, MovieDetailUIModel>() {

    data class MovieDetailParams(val movieId: String)

    override fun execute(params: MovieDetailParams): Flow<Resource<MovieDetailUIModel>> {
        return movieRepository.getMovieDetail(params.movieId).map {
            it.map { movieDetail ->
                movieMapper.toMovieDetailUIModel(movieDetail)
            }
        }
    }
}