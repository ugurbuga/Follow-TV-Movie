package com.ugurbuga.followtvmovie.watch.repository.movie

import com.ugurbuga.followtvmovie.watch.base.FTMBaseRepository
import com.ugurbuga.followtvmovie.watch.data.api.services.MovieService
import com.ugurbuga.followtvmovie.watch.domain.detail.model.MovieDetailResponse
import com.ugurbuga.followtvmovie.watch.domain.popularlist.MovieGeneralResponse
import com.ugurbuga.followtvmovie.watch.util.Resource
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService
) :
    MovieRepository, FTMBaseRepository() {

    override fun getPopularMovies(page: Int): Flow<Resource<MovieGeneralResponse>> {
        return onApiCall { movieService.getPopularMovies(page) }
    }

    override fun getUpcomingMovies(page: Int): Flow<Resource<MovieGeneralResponse>> {
        return onApiCall { movieService.getUpcomingMovies(page) }
    }

    override fun getMovieDetail(movieId: String): Flow<Resource<MovieDetailResponse>> {
        return onApiCall { movieService.getMovieDetail(movieId) }
    }

}
