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
import java.util.Calendar
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


    fun upcomingToPosterUIModel(response: MovieGeneralResponse): PosterUIModel {
        return PosterUIModel(
            page = response.page,
            posterList = response.results.map { toPosterItemUIModel(it) }
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

    private fun toPosterItemUIModel(response: MovieResponse): PosterItemUIModel {
        return PosterItemUIModel(
            id = response.id,
            name = response.title,
            posterPath = imageMapper.getPosterUrl(response.posterPath, response.backdropPath),
            mediaType = MediaType.MOVIE,
            releaseDateLong = Util.getDateLong(response.releaseDate)
        )
    }

    private fun toPosterItemUIModel(response: TvShowResponse): PosterItemUIModel {
        return PosterItemUIModel(
            id = response.id,
            name = response.name,
            posterPath = imageMapper.getPosterUrl(response.posterPath, response.backdropPath),
            mediaType = MediaType.TV,
            releaseDateLong = Util.getDateLong(response.firstAirDate)
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
            releaseDateLong = Util.getDateLong(response.releaseDate ?: response.firstAirDate)
        )
    }

    fun toPosterUIModel(movieDetail: MovieDetailUIModel, isWatched: Boolean) = PosterItemUIModel(
        id = movieDetail.id,
        name = movieDetail.title,
        posterPath = movieDetail.posterPath,
        mediaType = MediaType.MOVIE,
        releaseDateLong = Util.getDateLong(movieDetail.releaseDate),
        isWatched = isWatched
    )

}
