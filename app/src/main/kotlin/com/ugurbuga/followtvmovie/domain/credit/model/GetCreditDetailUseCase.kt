package com.ugurbuga.followtvmovie.domain.credit.model

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.common.map
import com.ugurbuga.followtvmovie.domain.credit.mapper.CreditMapper
import com.ugurbuga.followtvmovie.repository.credit.CreditRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetCreditDetailUseCase @Inject constructor(
    private val creditRepository: CreditRepository, private val creditMapper: CreditMapper
) : FTMUseCase<GetCreditDetailUseCase.CreditDetailParam, CreditDetailUIModel>() {

    data class CreditDetailParam(val creditId: String)

    override fun execute(params: CreditDetailParam): Flow<Resource<CreditDetailUIModel>> {
        return creditRepository.getCreditDetail(params.creditId).map {
            it.map { creditDetailResponse ->
                creditMapper.toCreditDetailUIModel(creditDetailResponse)
            }
        }
    }
}