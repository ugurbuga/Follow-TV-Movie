package com.ugurbuga.followtvmovie.domain.tvshowdetail.usecase

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.common.map
import com.ugurbuga.followtvmovie.domain.moviedetail.mapper.TvShowMapper
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.TrailerUIModel
import com.ugurbuga.followtvmovie.repository.tvshow.TvShowRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTvShowTrailersUseCase @Inject constructor(
    private val tvShowRepository: TvShowRepository,
    private val tvShowMapper: TvShowMapper
) : FTMUseCase<GetTvShowTrailersUseCase.TvShowTrailerParams, ArrayList<TrailerUIModel>>() {

    data class TvShowTrailerParams(val tvShowId: String)

    override fun execute(params: TvShowTrailerParams): Flow<ApiState<ArrayList<TrailerUIModel>>> {
        return tvShowRepository.getTvShowTrailers(params.tvShowId).map {
            it.map { trailersResponse ->
                tvShowMapper.toTrailerList(trailersResponse)
            }
        }
    }
}