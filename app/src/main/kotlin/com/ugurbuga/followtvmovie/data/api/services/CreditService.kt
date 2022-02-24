package com.ugurbuga.followtvmovie.data.api.services

import com.ugurbuga.followtvmovie.domain.credit.model.CreditDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CreditService {

    @GET("credit/{creditId}")
    suspend fun getCreditDetail(
        @Path("creditId") creditId: String,
    ): CreditDetailResponse
}
