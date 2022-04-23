package com.ugurbuga.followtvmovie.domain.external.usecase

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.common.map
import com.ugurbuga.followtvmovie.domain.external.mapper.ExternalUrlsMapper
import com.ugurbuga.followtvmovie.domain.external.model.ExternalIdsUIModel
import com.ugurbuga.followtvmovie.repository.common.CommonRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetExternalUrlsUseCase @Inject constructor(
    private val commonRepository: CommonRepository,
    private val externalUrlsMapper: ExternalUrlsMapper
) : FTMUseCase<GetExternalUrlsUseCase.ExternalUrlParams, ExternalIdsUIModel>() {

    data class ExternalUrlParams(val id: String, val mediaType: String)

    override fun execute(params: ExternalUrlParams): Flow<ApiState<ExternalIdsUIModel>> {
        return commonRepository.getExternalIds(params.id, params.mediaType).map {
            it.map { imageResponse ->
                externalUrlsMapper.toExternalUrls(imageResponse)
            }
        }
    }
}