package com.ugurbuga.followtvmovie.data.api.services

import com.ugurbuga.followtvmovie.domain.credit.model.CreditResponse
import com.ugurbuga.followtvmovie.domain.external.model.ExternalIdsResponse
import com.ugurbuga.followtvmovie.domain.image.model.ImageResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.review.ReviewGeneralResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.video.VideosResponse
import com.ugurbuga.followtvmovie.domain.popular.movie.model.PosterGeneralResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CommonService {

    @GET("{mediaType}/{id}/reviews")
    suspend fun getReviews(
        @Path("id") id: String,
        @Path("mediaType") mediaType: String,
    ): ReviewGeneralResponse

    @GET("{mediaType}/{id}/external_ids")
    suspend fun getExternalIds(
        @Path("id") id: String,
        @Path("mediaType") mediaType: String,
    ): ExternalIdsResponse

    @GET("{mediaType}/{id}/images")
    suspend fun getImages(
        @Path("id") id: String,
        @Path("mediaType") mediaType: String,
        @Query("include_image_language") includeImageLanguage: String = "null"
    ): ImageResponse

    @GET("{mediaType}/{id}/credits")
    suspend fun getCredits(
        @Path("id") id: String,
        @Path("mediaType") mediaType: String,
    ): CreditResponse

    @GET("{mediaType}/{id}/videos")
    suspend fun getVideos(
        @Path("id") id: String,
        @Path("mediaType") mediaType: String,
    ): VideosResponse

    @GET("{mediaType}/{id}/similar")
    suspend fun getSimilar(
        @Path("id") id: String,
        @Path("mediaType") mediaType: String,
        @Query("page") page: Int,
    ): PosterGeneralResponse

    @GET("{mediaType}/{id}/recommendations")
    suspend fun getRecommendations(
        @Path("id") id: String,
        @Path("mediaType") mediaType: String,
        @Query("page") page: Int,
    ): PosterGeneralResponse
}
