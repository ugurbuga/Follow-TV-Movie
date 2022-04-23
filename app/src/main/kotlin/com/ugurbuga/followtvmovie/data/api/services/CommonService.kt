package com.ugurbuga.followtvmovie.data.api.services

import com.ugurbuga.followtvmovie.domain.moviedetail.model.review.ReviewGeneralResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CommonService {

    @GET("{mediaType}/{id}/reviews")
    suspend fun getReviews(
        @Path("id") id: String,
        @Path("mediaType") mediaType: String,
    ): ReviewGeneralResponse

}