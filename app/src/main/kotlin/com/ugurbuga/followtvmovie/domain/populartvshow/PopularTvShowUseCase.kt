package com.ugurbuga.followtvmovie.domain.populartvshow

import com.ugurbuga.followtvmovie.common.Resource
import kotlinx.coroutines.flow.Flow

interface PopularTvShowUseCase {

    fun getPopularTvShows(page: Int): Flow<Resource<Any>>

}
