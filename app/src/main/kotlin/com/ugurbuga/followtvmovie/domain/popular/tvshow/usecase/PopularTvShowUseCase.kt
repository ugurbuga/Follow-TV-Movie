package com.ugurbuga.followtvmovie.domain.popular.tvshow.usecase

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.common.map
import com.ugurbuga.followtvmovie.domain.poster.mapper.PosterMapper
import com.ugurbuga.followtvmovie.domain.poster.model.PosterUIModel
import com.ugurbuga.followtvmovie.repository.tvshow.TvShowRepository
import com.ugurbuga.followtvmovie.ui.discover.MediaType
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PopularTvShowUseCase @Inject constructor(
    private val tvShowRepository: TvShowRepository,
    private val posterMapper: PosterMapper
) :
    FTMUseCase<PopularTvShowUseCase.PopularTvShowParams, PosterUIModel>() {

    data class PopularTvShowParams(val page: Int)

    override fun execute(params: PopularTvShowParams): Flow<ApiState<PosterUIModel>> {
        return tvShowRepository.getPopularTvShows(params.page).map {
            it.map { response -> posterMapper.toPosterUIModel(response, MediaType.TV) }
        }
    }
}