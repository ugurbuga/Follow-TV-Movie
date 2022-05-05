package com.ugurbuga.followtvmovie.data.repository.movie

import com.ugurbuga.followtvmovie.core.common.ApiState
import com.ugurbuga.followtvmovie.data.model.response.moviedetail.MovieDetailResponse
import com.ugurbuga.followtvmovie.data.model.response.popularmovie.PosterGeneralResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPopularMovies(page: Int): Flow<ApiState<PosterGeneralResponse>>

    fun getUpcomingMovies(page: Int): Flow<ApiState<PosterGeneralResponse>>

    fun getMovieDetail(movieId: String): Flow<ApiState<MovieDetailResponse>>
}
