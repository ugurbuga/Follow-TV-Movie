package com.ugurbuga.followtvmovie.repository.tvshow

import com.ugurbuga.followtvmovie.base.FTMBaseRepository
import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.data.api.services.TvShowService
import com.ugurbuga.followtvmovie.domain.moviedetail.credit.CreditResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.external.ExternalIdsResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.image.ImageResponse
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

    override fun getPopularTvShows(page: Int): Flow<Resource<TvShowGeneralResponse>> {
        return onApiCall { tvShowService.getPopularTvShows(page) }
    }

    override fun getTvShowDetail(tvShowId: String): Flow<Resource<TvShowDetailResponse>> {
        return onApiCall { tvShowService.getTvShowDetail(tvShowId) }
    }

    override fun getTvShowReviews(movieId: String): Flow<Resource<ReviewGeneralResponse>> {
        return onApiCall { tvShowService.getTvShowReviews(movieId) }
    }

    override fun getTvShowTrailers(movieId: String): Flow<Resource<TrailersResponse>> {
        return onApiCall { tvShowService.getTvShowTrailers(movieId) }
    }

    override fun getTvShowCredits(movieId: String): Flow<Resource<CreditResponse>> {
        return onApiCall { tvShowService.getTvShowCredits(movieId) }
    }

    override fun getTvShowImages(movieId: String): Flow<Resource<ImageResponse>> {
        return onApiCall { tvShowService.getTvShowImages(movieId) }
    }

    override fun getTvShowExternalIds(movieId: String): Flow<Resource<ExternalIdsResponse>> {
        return onApiCall { tvShowService.getTvShowExternalIds(movieId) }
    }

    override fun getRecommendations(
        movieId: String,
        page: Int
    ): Flow<Resource<TvShowGeneralResponse>> {
        return onApiCall { tvShowService.getRecommendations(movieId, page) }
    }

    override fun getSimilarTvShows(
        movieId: String,
        page: Int
    ): Flow<Resource<TvShowGeneralResponse>> {
        return onApiCall { tvShowService.getSimilarMovies(movieId, page) }
    }
}
