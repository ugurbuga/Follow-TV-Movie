package com.ugurbuga.followtvmovie.watch.repository.movie

import com.ugurbuga.followtvmovie.watch.domain.detail.model.MovieDetailResponse
import com.ugurbuga.followtvmovie.watch.domain.popularlist.MovieGeneralResponse
import com.ugurbuga.followtvmovie.watch.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPopularMovies(page: Int): Flow<Resource<MovieGeneralResponse>>

    fun getUpcomingMovies(page: Int): Flow<Resource<MovieGeneralResponse>>

    fun getMovieDetail(movieId: String): Flow<Resource<MovieDetailResponse>>

}
