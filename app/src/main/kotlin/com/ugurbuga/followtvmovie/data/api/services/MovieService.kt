package com.ugurbuga.followtvmovie.data.api.services

import com.ugurbuga.followtvmovie.domain.moviedetail.model.MovieDetailResponse
import com.ugurbuga.followtvmovie.domain.popular.movie.model.MovieGeneralResponse
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
        @Path("movieId") page: Int,
    ): MovieDetailResponse
}
