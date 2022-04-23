package com.ugurbuga.followtvmovie.domain.moviedetail.usecase

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.common.map
import com.ugurbuga.followtvmovie.domain.moviedetail.credit.mapper.CreditMapper
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.CastUIModel
import com.ugurbuga.followtvmovie.repository.common.CommonRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetCastsUseCase @Inject constructor(
    private val commonRepository: CommonRepository,
    private val creditMapper: CreditMapper
) : FTMUseCase<GetCastsUseCase.CastParams, ArrayList<CastUIModel>>() {

    data class CastParams(val id: String, val mediaType: String)

    override fun execute(params: CastParams): Flow<ApiState<ArrayList<CastUIModel>>> {
        return commonRepository.getCredits(params.id, params.mediaType).map {
            it.map { creditResponse ->
                creditMapper.toCastList(creditResponse)
            }
        }
    }
}