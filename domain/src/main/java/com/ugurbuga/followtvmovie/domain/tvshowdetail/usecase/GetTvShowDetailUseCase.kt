package com.ugurbuga.followtvmovie.domain.tvshowdetail.usecase

import com.ugurbuga.followtvmovie.core.base.UseCase
import com.ugurbuga.followtvmovie.core.common.ApiState
import com.ugurbuga.followtvmovie.core.common.map
import com.ugurbuga.followtvmovie.data.repository.tvshow.TvShowRepository
import com.ugurbuga.followtvmovie.domain.moviedetail.mapper.TvShowMapper
import com.ugurbuga.followtvmovie.domain.tvshowdetail.model.TvShowDetailUIModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTvShowDetailUseCase @Inject constructor(
    private val tvShowRepository: TvShowRepository,
    private val tvShowMapper: TvShowMapper
) :
    UseCase<GetTvShowDetailUseCase.TvShowDetailParams, TvShowDetailUIModel>() {

    data class TvShowDetailParams(val tvShowId: String)

    override fun execute(params: TvShowDetailParams): Flow<ApiState<TvShowDetailUIModel>> {
        return tvShowRepository.getTvShowDetail(params.tvShowId).map {
            it.map { tvShowDetail ->
                tvShowMapper.toTvShowDetailUIModel(tvShowDetail)
            }
        }
    }
}