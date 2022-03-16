package com.ugurbuga.followtvmovie.data.api.services

import com.ugurbuga.followtvmovie.domain.moviedetail.credit.CreditResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.external.ExternalIdsResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.image.MovieImageResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.MovieDetailResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.review.MovieReviewResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.trailer.TrailersResponse
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
        @Path("movieId") movieId: String,
    ): MovieDetailResponse

    @GET("movie/{movieId}/reviews")
    suspend fun getMovieReviews(
        @Path("movieId") movieId: String,
    ): MovieReviewResponse

    @GET("movie/{movieId}/videos")
    suspend fun getMovieTrailers(
        @Path("movieId") movieId: String,
    ): TrailersResponse

    @GET("movie/{movieId}/credits")
    suspend fun getMovieCredits(
        @Path("movieId") movieId: String,
    ): CreditResponse

    @GET("movie/{movieId}/images")
    suspend fun getMovieImages(
        @Path("movieId") movieId: String,
        @Query("include_image_language") includeImageLanguage: String = "null"
    ): MovieImageResponse

    @GET("movie/{movieId}/external_ids")
    suspend fun getMovieExternalIds(
        @Path("movieId") movieId: String,
    ): ExternalIdsResponse

    @GET("movie/{movieId}/recommendations")
    suspend fun getRecommendations(
        @Path("movieId") movieId: String,
        @Query("page") page: Int,
    ): MovieGeneralResponse

    @GET("movie/{movieId}/similar")
    suspend fun getSimilarMovies(
        @Path("movieId") movieId: String,
        @Query("page") page: Int,
    ): MovieGeneralResponse
}
