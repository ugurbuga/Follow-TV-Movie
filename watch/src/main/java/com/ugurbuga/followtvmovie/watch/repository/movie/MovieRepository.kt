package com.ugurbuga.followtvmovie.watch.repository.movie

import com.ugurbuga.followtvmovie.watch.detail.model.MovieDetailResponse
import com.ugurbuga.followtvmovie.watch.popularlist.model.MovieGeneralResponse
import com.ugurbuga.followtvmovie.watch.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPopularMovies(page: Int): Flow<Resource<MovieGeneralResponse>>

    fun getMovieDetail(movieId: String): Flow<Resource<MovieDetailResponse>>

}
