package com.ugurbuga.followtvmovie.domain.populartvshow.mapper

import com.ugurbuga.followtvmovie.common.Util
import com.ugurbuga.followtvmovie.data.api.ApiConstants
import com.ugurbuga.followtvmovie.domain.populartvshow.model.PosterItemUIModel
import com.ugurbuga.followtvmovie.domain.populartvshow.model.PosterUIModel
import com.ugurbuga.followtvmovie.domain.populartvshow.model.TvShowGeneralResponse
import com.ugurbuga.followtvmovie.domain.populartvshow.model.TvShowResponse
import javax.inject.Inject

class PosterMapper @Inject constructor() {

    fun getPosterUIModel(response: TvShowGeneralResponse): PosterUIModel {

        return PosterUIModel(
            page = response.page,
            posterList = response.results.map { getPosterItemUIModel(it) }.toMutableList(),
            totalPages = response.totalPages
        )
    }

    private fun getPosterItemUIModel(response: TvShowResponse): PosterItemUIModel {
        return PosterItemUIModel(
            id = response.id,
            name = response.name,
            posterPath = getPosterPath(response),
        )
    }

    private fun getPosterPath(response: TvShowResponse): String {
        response.posterPath?.let {
            return ApiConstants.BASE_IMAGE_URL + it
        }

        response.backdropPath?.let {
            return ApiConstants.BASE_IMAGE_URL + it
        }

        return Util.EMPTY_STRING
    }

}
