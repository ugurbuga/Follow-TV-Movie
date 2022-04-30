package com.ugurbuga.followtvmovie.data.repository.movie

import com.ugurbuga.followtvmovie.core.base.BaseRepository
import com.ugurbuga.followtvmovie.core.common.ApiState
import com.ugurbuga.followtvmovie.data.api.services.MovieService
import com.ugurbuga.followtvmovie.data.model.response.moviedetail.MovieDetailResponse
import com.ugurbuga.followtvmovie.data.model.response.popularmovie.PosterGeneralResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService,
) :
    MovieRepository, BaseRepository() {

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
