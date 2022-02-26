package com.ugurbuga.followtvmovie.domain.poster.mapper

import com.ugurbuga.followtvmovie.common.Util
import com.ugurbuga.followtvmovie.domain.image.ImageMapper
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.MovieDetailUIModel
import com.ugurbuga.followtvmovie.domain.popular.movie.model.MovieGeneralResponse
import com.ugurbuga.followtvmovie.domain.popular.movie.model.MovieResponse
import com.ugurbuga.followtvmovie.domain.popular.tvshow.model.TvShowGeneralResponse
import com.ugurbuga.followtvmovie.domain.popular.tvshow.model.TvShowResponse
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import com.ugurbuga.followtvmovie.domain.poster.model.PosterUIModel
import com.ugurbuga.followtvmovie.domain.search.SearchItemResponse
import com.ugurbuga.followtvmovie.domain.search.SearchResponse
import com.ugurbuga.followtvmovie.ui.discover.MediaType
import javax.inject.Inject

class PosterMapper @Inject constructor(
    private val imageMapper: ImageMapper
) {

    fun toPosterUIModel(response: TvShowGeneralResponse): PosterUIModel {

        return PosterUIModel(
            page = response.page,
            posterList = response.results.map { toPosterItemUIModel(it) }.toMutableList(),
            totalPages = response.totalPages
        )
    }

    fun toPosterUIModel(response: MovieGeneralResponse): PosterUIModel {
        return PosterUIModel(
            page = response.page,
            posterList = response.results.map { toPosterItemUIModel(it) }.toMutableList(),
            totalPages = response.totalPages,
        )
    }

    fun toPosterUIModel(response: SearchResponse): PosterUIModel {
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
            posterPath = imageMapper.getPosterUrl(response.posterPath, response.backdropPath),
            mediaType = MediaType.MOVIE
        )
    }

    private fun toPosterItemUIModel(response: TvShowResponse): PosterItemUIModel {
        return PosterItemUIModel(
            id = response.id,
            name = response.name,
            posterPath = imageMapper.getPosterUrl(response.posterPath, response.backdropPath),
            mediaType = MediaType.TV
        )
    }


    private fun toPosterItemUIModel(response: SearchItemResponse): PosterItemUIModel {
        return PosterItemUIModel(
            id = response.id,
            name = response.name ?: response.title ?: Util.EMPTY_STRING,
            posterPath = imageMapper.getPosterUrl(
                response.posterPath,
                response.backdropPath,
                response.profilePath
            ),
            mediaType = response.mediaType
        )
    }

    fun toPosterUIModel(movieDetail: MovieDetailUIModel) = PosterItemUIModel(
        id = movieDetail.id,
        name = movieDetail.title,
        posterPath = movieDetail.posterPath,
        mediaType = MediaType.MOVIE,
    )

}
