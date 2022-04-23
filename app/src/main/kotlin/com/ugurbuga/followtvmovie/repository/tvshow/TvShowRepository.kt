package com.ugurbuga.followtvmovie.repository.tvshow

import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.domain.moviedetail.credit.CreditResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.image.ImageResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.review.ReviewGeneralResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.trailer.TrailersResponse
import com.ugurbuga.followtvmovie.domain.popular.tvshow.model.TvShowGeneralResponse
import com.ugurbuga.followtvmovie.domain.tvshowdetail.detail.TvShowDetailResponse
import kotlinx.coroutines.flow.Flow

interface TvShowRepository {

    fun getPopularTvShows(page: Int): Flow<ApiState<TvShowGeneralResponse>>

    fun getTvShowDetail(tvShowId: String): Flow<ApiState<TvShowDetailResponse>>

    fun getTvShowReviews(movieId: String): Flow<ApiState<ReviewGeneralResponse>>

    fun getTvShowTrailers(movieId: String): Flow<ApiState<TrailersResponse>>

    fun getTvShowCredits(movieId: String): Flow<ApiState<CreditResponse>>

    fun getTvShowImages(movieId: String): Flow<ApiState<ImageResponse>>

    fun getRecommendations(
        movieId: String,
        page: Int
    ): Flow<ApiState<TvShowGeneralResponse>>

    fun getSimilarTvShows(
        movieId: String,
        page: Int
    ): Flow<ApiState<TvShowGeneralResponse>>
}
