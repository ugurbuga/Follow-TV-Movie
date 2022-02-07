package com.ugurbuga.followtvmovie.domain.popular.tvshow.usecase

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.common.map
import com.ugurbuga.followtvmovie.domain.poster.mapper.PosterMapper
import com.ugurbuga.followtvmovie.domain.poster.model.PosterUIModel
import com.ugurbuga.followtvmovie.repository.tvshow.TvShowRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PopularTvShowUseCase @Inject constructor(
    private val tvShowRepository: TvShowRepository,
    private val posterMapper: PosterMapper
) :
    FTMUseCase<PopularTvShowUseCase.PopularTvShowParams, PosterUIModel>() {

    data class PopularTvShowParams(val page: Int)

    override fun execute(params: PopularTvShowParams): Flow<Resource<PosterUIModel>> {
        return tvShowRepository.getPopularTvShows(params.page).map {
            it.map { response -> posterMapper.toPosterUIModel(response) }
        }
    }
}