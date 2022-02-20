package com.ugurbuga.followtvmovie.domain.cast

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.domain.moviedetail.mapper.MovieMapper
import com.ugurbuga.followtvmovie.repository.credit.CreditRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCastDetailUseCase @Inject constructor(
    private val creditRepository: CreditRepository,
    private val movieMapper: MovieMapper
) : FTMUseCase<GetCastDetailUseCase.CastDetailParam, CastDetailResponse>() {

    data class CastDetailParam(val creditId: Int)

    //TODO: Uğur -> Convert to UIModel
    override fun execute(params: CastDetailParam): Flow<Resource<CastDetailResponse>> {
        return creditRepository.getCastDetail(params.creditId)
    }
}