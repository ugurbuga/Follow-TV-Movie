package com.ugurbuga.followtvmovie.watch.domain.comingsoon

import com.ugurbuga.followtvmovie.watch.base.FTMUseCase
import com.ugurbuga.followtvmovie.watch.domain.popularlist.MovieGeneralResponse
import com.ugurbuga.followtvmovie.watch.repository.movie.MovieRepository
import com.ugurbuga.followtvmovie.watch.util.Resource
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class UpcomingMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) :
    FTMUseCase<UpcomingMovieUseCase.UpcomingMovieParams, MovieGeneralResponse>() {

    data class UpcomingMovieParams(val page: Int)

    override fun execute(params: UpcomingMovieParams): Flow<Resource<MovieGeneralResponse>> {
        return movieRepository.getUpcomingMovies(params.page)
    }
}