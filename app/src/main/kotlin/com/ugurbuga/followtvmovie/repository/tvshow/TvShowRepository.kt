package com.ugurbuga.followtvmovie.repository.tvshow

import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.domain.moviedetail.credit.CreditResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.external.ExternalIdsResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.image.ImageResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.review.ReviewGeneralResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.trailer.TrailersResponse
import com.ugurbuga.followtvmovie.domain.popular.tvshow.model.TvShowGeneralResponse
import com.ugurbuga.followtvmovie.domain.tvshowdetail.detail.TvShowDetailResponse
import kotlinx.coroutines.flow.Flow

interface TvShowRepository {

    fun getPopularTvShows(page: Int): Flow<Resource<TvShowGeneralResponse>>

    fun getTvShowDetail(tvShowId: String): Flow<Resource<TvShowDetailResponse>>

    fun getTvShowReviews(movieId: String): Flow<Resource<ReviewGeneralResponse>>

    fun getTvShowTrailers(movieId: String): Flow<Resource<TrailersResponse>>

    fun getTvShowCredits(movieId: String): Flow<Resource<CreditResponse>>

    fun getTvShowImages(movieId: String): Flow<Resource<ImageResponse>>

    fun getTvShowExternalIds(movieId: String): Flow<Resource<ExternalIdsResponse>>

    fun getRecommendations(
        movieId: String,
        page: Int
    ): Flow<Resource<TvShowGeneralResponse>>

    fun getSimilarTvShows(
        movieId: String,
        page: Int
    ): Flow<Resource<TvShowGeneralResponse>>
}
