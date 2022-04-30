package com.ugurbuga.followtvmovie.domain.search

import com.ugurbuga.followtvmovie.core.base.UseCase
import com.ugurbuga.followtvmovie.core.common.ApiState
import com.ugurbuga.followtvmovie.core.common.map
import com.ugurbuga.followtvmovie.data.repository.search.SearchRepository
import com.ugurbuga.followtvmovie.domain.poster.mapper.PosterMapper
import com.ugurbuga.followtvmovie.domain.poster.model.PosterUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
    private val posterMapper: PosterMapper
) :
    UseCase<GetSearchUseCase.SearchParams, PosterUIModel>() {

    data class SearchParams(val query: String, val page: Int)

    override fun execute(params: SearchParams): Flow<ApiState<PosterUIModel>> {
        return searchRepository.getSearch(params.query, params.page).map {
            it.map { response -> posterMapper.toPosterUIModel(response) }
        }
    }
}