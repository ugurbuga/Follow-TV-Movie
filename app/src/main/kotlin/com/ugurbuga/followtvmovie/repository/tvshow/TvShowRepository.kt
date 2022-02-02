package com.ugurbuga.followtvmovie.repository.tvshow

import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.domain.populartvshow.model.TvShowGeneralResponse
import kotlinx.coroutines.flow.Flow

interface TvShowRepository {

    fun getPopularTvShows(page: Int): Flow<Resource<TvShowGeneralResponse>>
}
