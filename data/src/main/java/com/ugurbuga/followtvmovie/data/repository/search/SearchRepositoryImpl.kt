package com.ugurbuga.followtvmovie.data.repository.search

import com.ugurbuga.followtvmovie.data.BaseRepository
import com.ugurbuga.followtvmovie.core.common.ApiState
import com.ugurbuga.followtvmovie.data.api.services.SearchService
import com.ugurbuga.followtvmovie.data.model.response.search.SearchResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchService: SearchService
) :
    SearchRepository, BaseRepository() {

    override fun getSearch(query: String, page: Int): Flow<ApiState<SearchResponse>> {
        return onApiCall { searchService.getSearch(query, page) }
    }

}
