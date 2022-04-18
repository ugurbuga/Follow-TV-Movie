package com.ugurbuga.followtvmovie.domain.tvshowdetail.usecase

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.common.map
import com.ugurbuga.followtvmovie.domain.moviedetail.external.ExternalIdsUIModel
import com.ugurbuga.followtvmovie.domain.moviedetail.mapper.MovieMapper
import com.ugurbuga.followtvmovie.repository.tvshow.TvShowRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTvShowExternalUrlsUseCase @Inject constructor(
    private val tvShowRepository: TvShowRepository,
    private val movieMapper: MovieMapper
) : FTMUseCase<GetTvShowExternalUrlsUseCase.ExternalUrlParams, ExternalIdsUIModel>() {

    data class ExternalUrlParams(val tvShowId: String)

    override fun execute(params: ExternalUrlParams): Flow<ApiState<ExternalIdsUIModel>> {
        return tvShowRepository.getTvShowExternalIds(params.tvShowId).map {
            it.map { imageResponse ->
                movieMapper.toExternalUrls(imageResponse)
            }
        }
    }
}