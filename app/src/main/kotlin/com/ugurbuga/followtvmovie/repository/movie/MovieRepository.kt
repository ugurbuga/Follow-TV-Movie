package com.ugurbuga.followtvmovie.repository.movie

import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.domain.moviedetail.model.MovieDetailResponse
import com.ugurbuga.followtvmovie.domain.popular.movie.model.MovieGeneralResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPopularMovies(page: Int): Flow<Resource<MovieGeneralResponse>>

    fun getUpcomingMovies(page: Int): Flow<Resource<MovieGeneralResponse>>

    fun getMovieDetail(movieId: Int): Flow<Resource<MovieDetailResponse>>
}
