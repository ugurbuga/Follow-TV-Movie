package com.ugurbuga.followtvmovie.repository.tvshow

import com.ugurbuga.followtvmovie.base.FTMBaseRepository
import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.data.api.services.TvShowService
import com.ugurbuga.followtvmovie.domain.moviedetail.model.review.ReviewGeneralResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.trailer.TrailersResponse
import com.ugurbuga.followtvmovie.domain.popular.tvshow.model.TvShowGeneralResponse
import com.ugurbuga.followtvmovie.domain.tvshowdetail.detail.TvShowDetailResponse
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class TvShowRepositoryImpl @Inject constructor(
    private val tvShowService: TvShowService
) :
    TvShowRepository, FTMBaseRepository() {

    override fun getPopularTvShows(page: Int): Flow<ApiState<TvShowGeneralResponse>> {
        return onApiCall { tvShowService.getPopularTvShows(page) }
    }

    override fun getTvShowDetail(tvShowId: String): Flow<ApiState<TvShowDetailResponse>> {
        return onApiCall { tvShowService.getTvShowDetail(tvShowId) }
    }

    override fun getTvShowReviews(movieId: String): Flow<ApiState<ReviewGeneralResponse>> {
        return onApiCall { tvShowService.getTvShowReviews(movieId) }
    }

    override fun getTvShowTrailers(movieId: String): Flow<ApiState<TrailersResponse>> {
        return onApiCall { tvShowService.getTvShowTrailers(movieId) }
    }

    override fun getRecommendations(
        movieId: String,
        page: Int
    ): Flow<ApiState<TvShowGeneralResponse>> {
        return onApiCall { tvShowService.getRecommendations(movieId, page) }
    }

    override fun getSimilarTvShows(
        movieId: String,
        page: Int
    ): Flow<ApiState<TvShowGeneralResponse>> {
        return onApiCall { tvShowService.getSimilarMovies(movieId, page) }
    }
}
