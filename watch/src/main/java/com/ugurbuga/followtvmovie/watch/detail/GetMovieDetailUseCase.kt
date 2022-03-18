package com.ugurbuga.followtvmovie.watch.detail

import com.ugurbuga.followtvmovie.watch.detail.model.MovieDetailResponse
import com.ugurbuga.followtvmovie.watch.repository.FTMUseCase
import com.ugurbuga.followtvmovie.watch.repository.movie.MovieRepository
import com.ugurbuga.followtvmovie.watch.util.Resource
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetMovieDetailUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) : FTMUseCase<GetMovieDetailUseCase.MovieDetailParams, MovieDetailResponse>() {

    data class MovieDetailParams(val movieId: String)

    override fun execute(params: MovieDetailParams): Flow<Resource<MovieDetailResponse>> {
        return movieRepository.getMovieDetail(params.movieId)
    }
}