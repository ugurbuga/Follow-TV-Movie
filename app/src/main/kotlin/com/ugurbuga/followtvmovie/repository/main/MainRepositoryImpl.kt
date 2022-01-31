package com.ugurbuga.followtvmovie.repository.main

import com.ugurbuga.followtvmovie.base.BaseRepository
import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.data.api.services.TvShowService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val tvShowService: TvShowService) :
    MainRepository, BaseRepository() {

    override fun getPopularTvShows(page: Int): Flow<Resource<Any>> {
        return onApiCall { tvShowService.getPopularTvShows(page) }
    }
}
