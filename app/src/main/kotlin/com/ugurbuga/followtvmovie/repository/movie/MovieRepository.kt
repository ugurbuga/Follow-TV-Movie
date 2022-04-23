package com.ugurbuga.followtvmovie.repository.movie

import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.MovieDetailResponse
import com.ugurbuga.followtvmovie.domain.popular.movie.model.PosterGeneralResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPopularMovies(page: Int): Flow<ApiState<PosterGeneralResponse>>

    fun getUpcomingMovies(page: Int): Flow<ApiState<PosterGeneralResponse>>

    fun getMovieDetail(movieId: String): Flow<ApiState<MovieDetailResponse>>
}
