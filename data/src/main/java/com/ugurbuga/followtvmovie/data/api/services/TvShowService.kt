package com.ugurbuga.followtvmovie.data.api.services

import com.ugurbuga.followtvmovie.data.model.response.popularmovie.PosterGeneralResponse
import com.ugurbuga.followtvmovie.data.model.response.tvshowdetail.TvShowDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvShowService {

    @GET("tv/popular")
    suspend fun getPopularTvShows(
        @Query("page") page: Int,
    ): PosterGeneralResponse

    @GET("tv/{tvShowId}")
    suspend fun getTvShowDetail(
        @Path("tvShowId") tvShowId: String,
    ): TvShowDetailResponse
}
