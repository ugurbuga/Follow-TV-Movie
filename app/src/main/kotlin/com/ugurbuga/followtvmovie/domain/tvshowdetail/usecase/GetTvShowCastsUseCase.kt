package com.ugurbuga.followtvmovie.domain.tvshowdetail.usecase

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.common.map
import com.ugurbuga.followtvmovie.domain.moviedetail.credit.mapper.CreditMapper
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.CastUIModel
import com.ugurbuga.followtvmovie.repository.tvshow.TvShowRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTvShowCastsUseCase @Inject constructor(
    private val tvShowRepository: TvShowRepository,
    private val creditMapper: CreditMapper
) : FTMUseCase<GetTvShowCastsUseCase.CastParams, ArrayList<CastUIModel>>() {

    data class CastParams(val tvShowId: String)

    override fun execute(params: CastParams): Flow<Resource<ArrayList<CastUIModel>>> {
        return tvShowRepository.getTvShowCredits(params.tvShowId).map {
            it.map { creditResponse ->
                creditMapper.toCastList(creditResponse)
            }
        }
    }
}