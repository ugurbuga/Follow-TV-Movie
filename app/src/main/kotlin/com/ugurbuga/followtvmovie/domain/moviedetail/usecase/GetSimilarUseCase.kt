package com.ugurbuga.followtvmovie.domain.moviedetail.usecase

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.common.map
import com.ugurbuga.followtvmovie.domain.poster.mapper.PosterMapper
import com.ugurbuga.followtvmovie.domain.poster.model.PosterUIModel
import com.ugurbuga.followtvmovie.repository.common.CommonRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSimilarUseCase @Inject constructor(
    private val commonRepository: CommonRepository,
    private val posterMapper: PosterMapper
) :
    FTMUseCase<GetSimilarUseCase.Similar, PosterUIModel>() {

    data class Similar(var movieId: String, val page: Int, val mediaType: String)

    override fun execute(params: Similar): Flow<ApiState<PosterUIModel>> {
        return commonRepository.getSimilar(params.movieId, params.mediaType, params.page).map {
            it.map { response -> posterMapper.toPosterUIModel(response, params.mediaType) }
        }
    }
}