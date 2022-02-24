package com.ugurbuga.followtvmovie.repository.credit

import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.domain.credit.model.CreditDetailResponse
import kotlinx.coroutines.flow.Flow

interface CreditRepository {

    fun getCreditDetail(page: String): Flow<Resource<CreditDetailResponse>>
}
