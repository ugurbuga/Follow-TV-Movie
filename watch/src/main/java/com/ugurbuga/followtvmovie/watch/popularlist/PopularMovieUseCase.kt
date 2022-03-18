package com.ugurbuga.followtvmovie.watch.popularlist

import com.ugurbuga.followtvmovie.watch.popularlist.model.MovieGeneralResponse
import com.ugurbuga.followtvmovie.watch.repository.FTMUseCase
import com.ugurbuga.followtvmovie.watch.repository.movie.MovieRepository
import com.ugurbuga.followtvmovie.watch.util.Resource
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class PopularMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) :
    FTMUseCase<PopularMovieUseCase.PopularMovieParams, MovieGeneralResponse>() {

    data class PopularMovieParams(val page: Int)

    override fun execute(params: PopularMovieParams): Flow<Resource<MovieGeneralResponse>> {
        return movieRepository.getPopularMovies(params.page)
    }
}