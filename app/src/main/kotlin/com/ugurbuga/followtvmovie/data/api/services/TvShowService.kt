package com.ugurbuga.followtvmovie.data.api.services

import com.ugurbuga.followtvmovie.domain.popular.tvshow.model.TvShowGeneralResponse
import com.ugurbuga.followtvmovie.domain.tvshowdetail.detail.TvShowDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvShowService {

    @GET("tv/popular")
    suspend fun getPopularTvShows(
        @Query("page") page: Int,
    ): TvShowGeneralResponse

    @GET("tv/{tv_id}")
    suspend fun getTvShowDetail(
        @Path("tv_id") tvShowId: String,
    ): TvShowDetailResponse
}
