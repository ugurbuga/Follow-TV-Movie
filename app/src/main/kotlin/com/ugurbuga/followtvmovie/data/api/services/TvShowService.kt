package com.ugurbuga.followtvmovie.data.api.services

import com.ugurbuga.followtvmovie.domain.popular.movie.model.PosterGeneralResponse
import com.ugurbuga.followtvmovie.domain.tvshowdetail.detail.TvShowDetailResponse
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

    @GET("tv/{tvShowId}/recommendations")
    suspend fun getRecommendations(
        @Path("tvShowId") tvShowId: String,
        @Query("page") page: Int,
    ): PosterGeneralResponse
}
