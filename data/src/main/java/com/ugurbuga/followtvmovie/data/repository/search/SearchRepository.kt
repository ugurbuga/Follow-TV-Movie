package com.ugurbuga.followtvmovie.data.repository.search

import com.ugurbuga.followtvmovie.core.common.ApiState
import com.ugurbuga.followtvmovie.data.model.response.search.SearchResponse
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun getSearch(query: String, page: Int): Flow<ApiState<SearchResponse>>

}
