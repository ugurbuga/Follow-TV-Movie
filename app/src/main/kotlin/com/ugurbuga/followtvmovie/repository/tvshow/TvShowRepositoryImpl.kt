package com.ugurbuga.followtvmovie.repository.tvshow

import com.ugurbuga.followtvmovie.base.FTMBaseRepository
import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.data.api.services.TvShowService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvShowRepositoryImpl @Inject constructor(private val tvShowService: TvShowService) :
    TvShowRepository, FTMBaseRepository() {

    override fun getPopularTvShows(page: Int): Flow<Resource<Any>> {
        return onApiCall { tvShowService.getPopularTvShows(page) }
    }
}
