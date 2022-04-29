package com.ugurbuga.followtvmovie.domain.external.usecase

import com.ugurbuga.followtvmovie.core.base.UseCase
import com.ugurbuga.followtvmovie.core.common.ApiState
import com.ugurbuga.followtvmovie.core.common.map
import com.ugurbuga.followtvmovie.data.repository.common.CommonRepository
import com.ugurbuga.followtvmovie.domain.external.mapper.ExternalUrlsMapper
import com.ugurbuga.followtvmovie.domain.external.model.ExternalIdsUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetExternalUrlsUseCase @Inject constructor(
    private val commonRepository: CommonRepository,
    private val externalUrlsMapper: ExternalUrlsMapper
) : UseCase<GetExternalUrlsUseCase.ExternalUrlParams, ExternalIdsUIModel>() {

    data class ExternalUrlParams(val id: String, val mediaType: String)

    override fun execute(params: ExternalUrlParams): Flow<ApiState<ExternalIdsUIModel>> {
        return commonRepository.getExternalIds(params.id, params.mediaType).map {
            it.map { imageResponse ->
                externalUrlsMapper.toExternalUrls(imageResponse)
            }
        }
    }
}