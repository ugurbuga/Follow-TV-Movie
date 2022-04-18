package com.ugurbuga.followtvmovie.repository.search

import com.ugurbuga.followtvmovie.base.FTMBaseRepository
import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.data.api.services.SearchService
import com.ugurbuga.followtvmovie.domain.search.SearchResponse
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class SearchRepositoryImpl @Inject constructor(
    private val searchService: SearchService
) :
    SearchRepository, FTMBaseRepository() {

    override fun getSearch(query: String, page: Int): Flow<ApiState<SearchResponse>> {
        return onApiCall { searchService.getSearch(query, page) }
    }

}
