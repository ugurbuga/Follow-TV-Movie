package com.ugurbuga.followtvmovie.domain.moviedetail.usecase

import com.ugurbuga.followtvmovie.core.base.UseCase
import com.ugurbuga.followtvmovie.core.common.ApiState
import com.ugurbuga.followtvmovie.core.common.map
import com.ugurbuga.followtvmovie.data.model.MediaType
import com.ugurbuga.followtvmovie.data.repository.common.CommonRepository
import com.ugurbuga.followtvmovie.domain.poster.mapper.PosterMapper
import com.ugurbuga.followtvmovie.domain.poster.model.PosterUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetRecommendationsUseCase @Inject constructor(
    private val commonRepository: CommonRepository,
    private val posterMapper: PosterMapper
) :
    UseCase<GetRecommendationsUseCase.Recommendations, PosterUIModel>() {

    data class Recommendations(var id: String, val page: Int, val mediaType: String)

    override fun execute(params: Recommendations): Flow<ApiState<PosterUIModel>> {
        return commonRepository.getRecommendations(params.id, params.mediaType, params.page)
            .map {
                it.map { response -> posterMapper.toPosterUIModel(response, MediaType.MOVIE) }
            }
    }
}