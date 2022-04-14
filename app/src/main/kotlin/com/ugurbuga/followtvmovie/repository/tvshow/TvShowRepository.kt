package com.ugurbuga.followtvmovie.repository.tvshow

import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.domain.popular.tvshow.model.TvShowGeneralResponse
import com.ugurbuga.followtvmovie.domain.tvshowdetail.detail.TvShowDetailResponse
import kotlinx.coroutines.flow.Flow

interface TvShowRepository {

    fun getPopularTvShows(page: Int): Flow<Resource<TvShowGeneralResponse>>

    fun getTvShowDetail(tvShowId: String): Flow<Resource<TvShowDetailResponse>>

}
