package com.ugurbuga.followtvmovie.domain.poster.mapper

import com.ugurbuga.followtvmovie.common.Util
import com.ugurbuga.followtvmovie.domain.moviedetail.model.MovieDetailUIModel
import com.ugurbuga.followtvmovie.domain.popular.movie.model.MovieGeneralResponse
import com.ugurbuga.followtvmovie.domain.popular.movie.model.MovieResponse
import com.ugurbuga.followtvmovie.domain.popular.tvshow.model.TvShowGeneralResponse
import com.ugurbuga.followtvmovie.domain.popular.tvshow.model.TvShowResponse
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import com.ugurbuga.followtvmovie.domain.poster.model.PosterUIModel
import com.ugurbuga.followtvmovie.ui.discover.DiscoverType
import javax.inject.Inject

class PosterMapper @Inject constructor() {

    fun toPosterUIModel(response: TvShowGeneralResponse): PosterUIModel {

        return PosterUIModel(
            page = response.page,
            posterList = response.results.map { toPosterItemUIModel(it) }.toMutableList(),
            totalPages = response.totalPages
        )
    }

    private fun toPosterItemUIModel(response: TvShowResponse): PosterItemUIModel {
        return PosterItemUIModel(
            id = response.id,
            name = response.name,
            posterPath = Util.getPosterPath(response.posterPath, response.backdropPath),
            type = DiscoverType.TV_SHOW
        )
    }

    fun toPosterUIModel(response: MovieGeneralResponse): PosterUIModel {
        return PosterUIModel(
            page = response.page,
            posterList = response.results.map { toPosterItemUIModel(it) }.toMutableList(),
            totalPages = response.totalPages,
        )
    }

    private fun toPosterItemUIModel(response: MovieResponse): PosterItemUIModel {
        return PosterItemUIModel(
            id = response.id,
            name = response.title,
            posterPath = Util.getPosterPath(response.posterPath, response.backdropPath),
            type = DiscoverType.MOVIE
        )
    }

    fun toPosterUIModel(movieDetail: MovieDetailUIModel) = PosterItemUIModel(
        id = movieDetail.id,
        name = movieDetail.title,
        posterPath = movieDetail.posterPath,
        type = DiscoverType.MOVIE,
    )

}
