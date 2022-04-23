package com.ugurbuga.followtvmovie.repository.tvshow

import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.domain.popular.movie.model.PosterGeneralResponse
import com.ugurbuga.followtvmovie.domain.tvshowdetail.detail.TvShowDetailResponse
import kotlinx.coroutines.flow.Flow

interface TvShowRepository {

    fun getPopularTvShows(page: Int): Flow<ApiState<PosterGeneralResponse>>

    fun getTvShowDetail(tvShowId: String): Flow<ApiState<TvShowDetailResponse>>

}
