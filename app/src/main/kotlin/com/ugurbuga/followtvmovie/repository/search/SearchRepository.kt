package com.ugurbuga.followtvmovie.repository.search

import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.domain.search.SearchResponse
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun getSearch(query: String, page: Int): Flow<ApiState<SearchResponse>>

}
