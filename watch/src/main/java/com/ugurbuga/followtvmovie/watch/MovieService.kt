package com.ugurbuga.followtvmovie.watch

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = "dc6ea32d8b21d70b8be0704d75bf67cf",
    ): Call<MovieGeneralResponse>
}
