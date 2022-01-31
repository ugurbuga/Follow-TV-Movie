package com.ugurbuga.followtvmovie.repository.main

import com.ugurbuga.followtvmovie.common.Resource
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun getPopularTvShows(page: Int): Flow<Resource<Any>>
}
