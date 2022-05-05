package com.ugurbuga.followtvmovie.domain.credit.usecase

import com.ugurbuga.followtvmovie.core.base.UseCase
import com.ugurbuga.followtvmovie.core.common.ApiState
import com.ugurbuga.followtvmovie.core.common.map
import com.ugurbuga.followtvmovie.data.repository.common.CommonRepository
import com.ugurbuga.followtvmovie.domain.credit.mapper.CreditMapper
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.CastUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCastsUseCase @Inject constructor(
    private val commonRepository: CommonRepository,
    private val creditMapper: CreditMapper
) : UseCase<GetCastsUseCase.CastParams, ArrayList<CastUIModel>>() {

    data class CastParams(val id: String, val mediaType: String)

    override fun execute(params: CastParams): Flow<ApiState<ArrayList<CastUIModel>>> {
        return commonRepository.getCredits(params.id, params.mediaType).map {
            it.map { creditResponse ->
                creditMapper.toCastList(creditResponse)
            }
        }
    }
}