package com.ugurbuga.followtvmovie.repository.credit

import com.ugurbuga.followtvmovie.base.FTMBaseRepository
import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.data.api.services.CreditService
import com.ugurbuga.followtvmovie.domain.cast.CastDetailResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreditRepositoryImpl @Inject constructor(
    private val creditService: CreditService
) :
    CreditRepository, FTMBaseRepository() {

    override fun getCastDetail(page: Int): Flow<Resource<CastDetailResponse>> {
        return onApiCall { creditService.getCastDetail(page) }
    }
}
