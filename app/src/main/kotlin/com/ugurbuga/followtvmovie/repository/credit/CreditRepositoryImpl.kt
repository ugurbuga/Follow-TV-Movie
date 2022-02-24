package com.ugurbuga.followtvmovie.repository.credit

import com.ugurbuga.followtvmovie.base.FTMBaseRepository
import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.data.api.services.CreditService
import com.ugurbuga.followtvmovie.domain.credit.model.CreditDetailResponse
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class CreditRepositoryImpl @Inject constructor(
    private val creditService: CreditService
) :
    CreditRepository, FTMBaseRepository() {

    override fun getCreditDetail(creditId: String): Flow<Resource<CreditDetailResponse>> {
        return onApiCall { creditService.getCreditDetail(creditId) }
    }
}
