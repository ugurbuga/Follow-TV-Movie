package com.ugurbuga.followtvmovie.domain.seasondetail.usecase

import com.ugurbuga.followtvmovie.core.base.UseCase
import com.ugurbuga.followtvmovie.core.common.ApiState
import com.ugurbuga.followtvmovie.core.common.map
import com.ugurbuga.followtvmovie.data.repository.tvshow.TvShowRepository
import com.ugurbuga.followtvmovie.domain.seasondetail.mapper.SeasonMapper
import com.ugurbuga.followtvmovie.domain.seasondetail.model.SeasonDetailUIModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTvShowSeasonDetailUseCase @Inject constructor(
    private val tvShowRepository: TvShowRepository,
    private val seasonMapper: SeasonMapper
) :
    UseCase<GetTvShowSeasonDetailUseCase.TvShowSeasonDetailParams, SeasonDetailUIModel>() {

    data class TvShowSeasonDetailParams(val tvShowId: String, val seasonNumber: Int)

    override fun execute(params: TvShowSeasonDetailParams): Flow<ApiState<SeasonDetailUIModel>> {
        return tvShowRepository.getTvShowSeasonDetail(params.tvShowId, params.seasonNumber).map {
            it.map { seasonDetail ->
                seasonMapper.toSeasonDetail(seasonDetail)
            }
        }
    }
}