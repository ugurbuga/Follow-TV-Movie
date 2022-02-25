package com.ugurbuga.followtvmovie.data.api.services

import com.ugurbuga.followtvmovie.domain.moviedetail.credit.CreditResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.image.PersonImageResponse
import com.ugurbuga.followtvmovie.domain.person.model.PersonDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PersonService {

    @GET("person/{personId}")
    suspend fun getPersonDetail(
        @Path("personId") personId: String,
    ): PersonDetailResponse

    @GET("person/{personId}/images")
    suspend fun getPersonImages(
        @Path("personId") personId: String,
    ): PersonImageResponse

    @GET("person/{personId}/combined_credits")
    suspend fun getPersonCredits(
        @Path("personId") personId: String,
    ): CreditResponse
}
