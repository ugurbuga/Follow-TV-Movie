package com.ugurbuga.followtvmovie.domain.poster.mapper

import com.ugurbuga.followtvmovie.common.Util
import com.ugurbuga.followtvmovie.data.api.ApiConstants
import com.ugurbuga.followtvmovie.domain.popular.movie.model.MovieGeneralResponse
import com.ugurbuga.followtvmovie.domain.popular.movie.model.MovieResponse
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import com.ugurbuga.followtvmovie.domain.poster.model.PosterUIModel
import com.ugurbuga.followtvmovie.domain.popular.tvshow.model.TvShowGeneralResponse
import com.ugurbuga.followtvmovie.domain.popular.tvshow.model.TvShowResponse
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
            posterPath = getPosterPath(response.posterPath, response.backdropPath)
        )
    }

    private fun getPosterPath(posterPath: String?, backdropPath: String?): String {
        posterPath?.let {
            return ApiConstants.BASE_IMAGE_URL + it
        }

        backdropPath?.let {
            return ApiConstants.BASE_IMAGE_URL + it
        }

        return Util.EMPTY_STRING
    }

    fun getPosterUIModel(response: MovieGeneralResponse): PosterUIModel {
        return PosterUIModel(
            page = response.page,
            posterList = response.results.map { getPosterItemUIModel(it) }.toMutableList(),
            totalPages = response.totalPages
        )
    }

    private fun getPosterItemUIModel(response: MovieResponse): PosterItemUIModel {
        return PosterItemUIModel(
            id = response.id,
            name = response.title,
            posterPath = getPosterPath(response.posterPath, response.backdropPath)
        )
    }

}
