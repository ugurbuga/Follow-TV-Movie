package com.ugurbuga.followtvmovie.domain.search

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.common.map
import com.ugurbuga.followtvmovie.domain.poster.mapper.PosterMapper
import com.ugurbuga.followtvmovie.domain.poster.model.PosterUIModel
import com.ugurbuga.followtvmovie.repository.search.SearchRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
    private val posterMapper: PosterMapper
) :
    FTMUseCase<GetSearchUseCase.SearchParams, PosterUIModel>() {

    data class SearchParams(val query: String, val page: Int)

    override fun execute(params: SearchParams): Flow<ApiState<PosterUIModel>> {
        return searchRepository.getSearch(params.query, params.page).map {
            it.map { response -> posterMapper.toPosterUIModel(response) }
        }
    }
}