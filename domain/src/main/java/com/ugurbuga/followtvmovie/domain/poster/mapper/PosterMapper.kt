package com.ugurbuga.followtvmovie.domain.poster.mapper

import com.ugurbuga.followtvmovie.core.common.Util
import com.ugurbuga.followtvmovie.data.model.MediaType
import com.ugurbuga.followtvmovie.data.model.PosterItemModel
import com.ugurbuga.followtvmovie.data.model.response.popularmovie.PosterGeneralResponse
import com.ugurbuga.followtvmovie.data.model.response.popularmovie.PosterResponse
import com.ugurbuga.followtvmovie.data.model.response.search.SearchItemResponse
import com.ugurbuga.followtvmovie.data.model.response.search.SearchResponse
import com.ugurbuga.followtvmovie.domain.image.mapper.ImageMapper
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.MovieDetailUIModel
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import com.ugurbuga.followtvmovie.domain.poster.model.PosterUIModel
import com.ugurbuga.followtvmovie.domain.tvshowdetail.detail.TvShowDetailUIModel
import java.util.*
import javax.inject.Inject

class PosterMapper @Inject constructor(
    private val imageMapper: ImageMapper
) {

    fun toPosterUIModel(
        response: PosterGeneralResponse,
        mediaType: String
    ): PosterUIModel {
        return PosterUIModel(
            page = response.page,
            posterList = response.results.map { toPosterItemUIModel(it, mediaType) }
                .toMutableList(),
            totalPages = response.totalPages,
        )
    }

    fun upcomingToPosterUIModel(response: PosterGeneralResponse): PosterUIModel {
        return PosterUIModel(
            page = response.page,
            posterList = response.results.map { toPosterItemUIModel(it, MediaType.MOVIE) }
                .filter { it.releaseDateLong > Calendar.getInstance().time.time }.toMutableList(),
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

    private fun toPosterItemUIModel(
        response: PosterResponse,
        mediaType: String
    ): PosterItemUIModel {
        val releaseDate = response.releaseDate ?: response.firstAirDate ?: Util.EMPTY_STRING

        return PosterItemUIModel(
            id = response.id,
            name = response.title ?: response.name ?: Util.EMPTY_STRING,
            posterPath = imageMapper.getPosterUrl(response.posterPath, response.backdropPath),
            mediaType = mediaType,
            releaseDate = releaseDate,
            releaseDateLong = Util.getDateLong(releaseDate)
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
            mediaType = response.mediaType,
            releaseDate = response.releaseDate ?: response.firstAirDate ?: Util.EMPTY_STRING,
            releaseDateLong = Util.getDateLong(response.releaseDate ?: response.firstAirDate)
        )
    }

    fun toPosterUIModel(movieDetail: MovieDetailUIModel, isWatched: Boolean) = PosterItemModel(
        id = movieDetail.id,
        name = movieDetail.title,
        posterPath = movieDetail.posterPath,
        mediaType = MediaType.MOVIE,
        releaseDate = movieDetail.releaseDate,
        releaseDateLong = Util.getDateLong(movieDetail.releaseDate),
        isWatched = isWatched
    )

    fun toPosterUIModel(movieDetail: TvShowDetailUIModel, isWatched: Boolean) = PosterItemModel(
        id = movieDetail.id,
        name = movieDetail.title,
        posterPath = movieDetail.posterPath,
        mediaType = MediaType.TV,
        releaseDate = movieDetail.releaseDate,
        releaseDateLong = Util.getDateLong(movieDetail.releaseDate),
        isWatched = isWatched
    )

}
