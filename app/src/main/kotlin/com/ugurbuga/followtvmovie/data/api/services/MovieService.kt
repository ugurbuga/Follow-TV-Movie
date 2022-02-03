package com.ugurbuga.followtvmovie.data.api.services

import com.ugurbuga.followtvmovie.domain.popular.movie.model.MovieGeneralResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
    ): MovieGeneralResponse
}
