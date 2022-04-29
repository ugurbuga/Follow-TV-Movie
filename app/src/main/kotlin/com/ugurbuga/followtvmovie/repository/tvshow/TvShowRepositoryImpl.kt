package com.ugurbuga.followtvmovie.repository.tvshow

import com.ugurbuga.followtvmovie.base.FTMBaseRepository
import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.data.api.services.TvShowService
import com.ugurbuga.followtvmovie.data.model.response.popularmovie.PosterGeneralResponse
import com.ugurbuga.followtvmovie.data.model.response.tvshowdetail.TvShowDetailResponse
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class TvShowRepositoryImpl @Inject constructor(
    private val tvShowService: TvShowService
) :
    TvShowRepository, FTMBaseRepository() {

    override fun getPopularTvShows(page: Int): Flow<ApiState<PosterGeneralResponse>> {
        return onApiCall { tvShowService.getPopularTvShows(page) }
    }

    override fun getTvShowDetail(tvShowId: String): Flow<ApiState<TvShowDetailResponse>> {
        return onApiCall { tvShowService.getTvShowDetail(tvShowId) }
    }
}
