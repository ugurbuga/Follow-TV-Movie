package com.ugurbuga.followtvmovie.repository.movie

import com.ugurbuga.followtvmovie.base.FTMBaseRepository
import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.data.api.services.MovieService
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.MovieDetailResponse
import com.ugurbuga.followtvmovie.domain.popular.movie.model.PosterGeneralResponse
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService
) :
    MovieRepository, FTMBaseRepository() {

    override fun getPopularMovies(page: Int): Flow<ApiState<PosterGeneralResponse>> {
        return onApiCall { movieService.getPopularMovies(page) }
    }

    override fun getUpcomingMovies(page: Int): Flow<ApiState<PosterGeneralResponse>> {
        return onApiCall { movieService.getUpcomingMovies(page) }
    }

    override fun getMovieDetail(movieId: String): Flow<ApiState<MovieDetailResponse>> {
        return onApiCall { movieService.getMovieDetail(movieId) }
    }
}
