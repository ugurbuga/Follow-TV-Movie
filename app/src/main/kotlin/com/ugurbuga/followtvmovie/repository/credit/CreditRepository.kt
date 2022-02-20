package com.ugurbuga.followtvmovie.repository.credit

import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.domain.cast.CastDetailResponse
import kotlinx.coroutines.flow.Flow

interface CreditRepository {

    fun getCastDetail(page: Int): Flow<Resource<CastDetailResponse>>
}
