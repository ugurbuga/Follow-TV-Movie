package com.ugurbuga.followtvmovie.domain.popular.tvshow.usecase

import com.ugurbuga.followtvmovie.core.base.UseCase
import com.ugurbuga.followtvmovie.core.common.ApiState
import com.ugurbuga.followtvmovie.core.common.map
import com.ugurbuga.followtvmovie.data.repository.tvshow.TvShowRepository
import com.ugurbuga.followtvmovie.domain.poster.mapper.PosterMapper
import com.ugurbuga.followtvmovie.domain.poster.model.PosterUIModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PopularTvShowUseCase @Inject constructor(
    private val tvShowRepository: TvShowRepository,
    private val posterMapper: PosterMapper
) :
    UseCase<PopularTvShowUseCase.PopularTvShowParams, PosterUIModel>() {

    data class PopularTvShowParams(val mediaType: String, val page: Int)

    override fun execute(params: PopularTvShowParams): Flow<ApiState<PosterUIModel>> {
        return tvShowRepository.getPopularTvShows(params.page).map {
            it.map { response -> posterMapper.toPosterUIModel(response, params.mediaType) }
        }
    }
}