package com.ugurbuga.followtvmovie.data.api.services

import com.ugurbuga.followtvmovie.data.model.response.search.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("search/multi")
    suspend fun getSearch(
        @Query("query") query: String,
        @Query("page") page: Int,
    ): SearchResponse

}
