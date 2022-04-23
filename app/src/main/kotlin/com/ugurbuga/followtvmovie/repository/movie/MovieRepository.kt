package com.ugurbuga.followtvmovie.repository.movie

import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.MovieDetailResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.trailer.TrailersResponse
import com.ugurbuga.followtvmovie.domain.popular.movie.model.MovieGeneralResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPopularMovies(page: Int): Flow<ApiState<MovieGeneralResponse>>

    fun getUpcomingMovies(page: Int): Flow<ApiState<MovieGeneralResponse>>

    fun getMovieDetail(movieId: String): Flow<ApiState<MovieDetailResponse>>

    fun getMovieTrailers(movieId: String): Flow<ApiState<TrailersResponse>>

    fun getRecommendations(movieId: String, page: Int): Flow<ApiState<MovieGeneralResponse>>

    fun getSimilarMovies(movieId: String, page: Int): Flow<ApiState<MovieGeneralResponse>>
}
