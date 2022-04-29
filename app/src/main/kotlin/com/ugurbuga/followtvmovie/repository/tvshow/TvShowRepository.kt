package com.ugurbuga.followtvmovie.repository.tvshow

import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.data.model.response.popularmovie.PosterGeneralResponse
import com.ugurbuga.followtvmovie.data.model.response.tvshowdetail.TvShowDetailResponse
import kotlinx.coroutines.flow.Flow

interface TvShowRepository {

    fun getPopularTvShows(page: Int): Flow<ApiState<PosterGeneralResponse>>

    fun getTvShowDetail(tvShowId: String): Flow<ApiState<TvShowDetailResponse>>

}
