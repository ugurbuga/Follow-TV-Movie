package com.ugurbuga.followtvmovie.domain.moviedetail.usecase

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.common.map
import com.ugurbuga.followtvmovie.domain.poster.mapper.PosterMapper
import com.ugurbuga.followtvmovie.domain.poster.model.PosterUIModel
import com.ugurbuga.followtvmovie.repository.common.CommonRepository
import com.ugurbuga.followtvmovie.ui.discover.MediaType
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetRecommendationsUseCase @Inject constructor(
    private val commonRepository: CommonRepository,
    private val posterMapper: PosterMapper
) :
    FTMUseCase<GetRecommendationsUseCase.Recommendations, PosterUIModel>() {

    data class Recommendations(var id: String, val page: Int, val mediaType: String)

    override fun execute(params: Recommendations): Flow<ApiState<PosterUIModel>> {
        return commonRepository.getRecommendations(params.id, params.mediaType, params.page)
            .map {
                it.map { response -> posterMapper.toPosterUIModel(response, MediaType.MOVIE) }
            }
    }
}