package com.ugurbuga.followtvmovie.watch.data.api.services

import com.ugurbuga.followtvmovie.watch.domain.detail.model.MovieDetailResponse
import com.ugurbuga.followtvmovie.watch.domain.popularlist.MovieGeneralResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
    ): MovieGeneralResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int,
    ): MovieGeneralResponse

    @GET("movie/{movieId}")
    suspend fun getMovieDetail(
        @Path("movieId") movieId: String,
    ): MovieDetailResponse

}
