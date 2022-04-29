package com.ugurbuga.followtvmovie.data.api.services

import com.ugurbuga.followtvmovie.data.model.response.moviedetail.MovieDetailResponse
import com.ugurbuga.followtvmovie.data.model.response.popularmovie.PosterGeneralResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
    ): PosterGeneralResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int,
    ): PosterGeneralResponse

    @GET("movie/{movieId}")
    suspend fun getMovieDetail(
        @Path("movieId") movieId: String,
    ): MovieDetailResponse
}
