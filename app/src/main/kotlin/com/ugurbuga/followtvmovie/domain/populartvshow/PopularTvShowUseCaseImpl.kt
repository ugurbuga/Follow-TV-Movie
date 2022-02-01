package com.ugurbuga.followtvmovie.domain.populartvshow

import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.common.map
import com.ugurbuga.followtvmovie.repository.tvshow.TvShowRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PopularTvShowUseCaseImpl @Inject constructor(
    private val tvShowRepository: TvShowRepository,
) : PopularTvShowUseCase {

    override fun getPopularTvShows(page: Int): Flow<Resource<Any>> {
        return tvShowRepository.getPopularTvShows(page).map {
            it.map { result ->
                result
            }
        }
    }
}
