package com.ugurbuga.followtvmovie.data.api.services

import com.ugurbuga.followtvmovie.domain.cast.CastDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CreditService {

    @GET("movie/{movieId}")
    suspend fun getCastDetail(
        @Path("creditId") creditId: Int,
    ): CastDetailResponse
}
