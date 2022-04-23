package com.ugurbuga.followtvmovie.data.api.services

import com.ugurbuga.followtvmovie.domain.moviedetail.credit.CreditResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.review.ReviewGeneralResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.trailer.TrailersResponse
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

    @GET("tv/{tvShowId}")
    suspend fun getTvShowDetail(
        @Path("tvShowId") tvShowId: String,
    ): TvShowDetailResponse

    @GET("tv/{tvShowId}/reviews")
    suspend fun getTvShowReviews(
        @Path("tvShowId") tvShowId: String,
    ): ReviewGeneralResponse

    @GET("tv/{tvShowId}/videos")
    suspend fun getTvShowTrailers(
        @Path("tvShowId") tvShowId: String,
    ): TrailersResponse

    @GET("tv/{tvShowId}/credits")
    suspend fun getTvShowCredits(
        @Path("tvShowId") tvShowId: String,
    ): CreditResponse

    @GET("tv/{tvShowId}/recommendations")
    suspend fun getRecommendations(
        @Path("tvShowId") tvShowId: String,
        @Query("page") page: Int,
    ): TvShowGeneralResponse

    @GET("tv/{tvShowId}/similar")
    suspend fun getSimilarMovies(
        @Path("tvShowId") tvShowId: String,
        @Query("page") page: Int,
    ): TvShowGeneralResponse
}
