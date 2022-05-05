package com.ugurbuga.followtvmovie.data.repository.tvshow

import com.ugurbuga.followtvmovie.core.base.BaseRepository
import com.ugurbuga.followtvmovie.core.common.ApiState
import com.ugurbuga.followtvmovie.data.api.services.TvShowService
import com.ugurbuga.followtvmovie.data.model.response.popularmovie.PosterGeneralResponse
import com.ugurbuga.followtvmovie.data.model.response.tvshowdetail.TvShowDetailResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvShowRepositoryImpl @Inject constructor(
    private val tvShowService: TvShowService
) :
    TvShowRepository, BaseRepository() {

    override fun getPopularTvShows(page: Int): Flow<ApiState<PosterGeneralResponse>> {
        return onApiCall { tvShowService.getPopularTvShows(page) }
    }

    override fun getTvShowDetail(tvShowId: String): Flow<ApiState<TvShowDetailResponse>> {
        return onApiCall { tvShowService.getTvShowDetail(tvShowId) }
    }
}
