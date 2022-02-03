package com.ugurbuga.followtvmovie.data.api.services

import com.ugurbuga.followtvmovie.domain.popular.tvshow.model.TvShowGeneralResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TvShowService {

    @GET("tv/popular")
    suspend fun getPopularTvShows(
        @Query("page") page: Int,
    ): TvShowGeneralResponse
}
