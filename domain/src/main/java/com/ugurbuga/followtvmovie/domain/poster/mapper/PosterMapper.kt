package com.ugurbuga.followtvmovie.domain.poster.mapper

import com.ugurbuga.followtvmovie.core.common.CommonUtil
import com.ugurbuga.followtvmovie.data.model.PosterItemModel
import com.ugurbuga.followtvmovie.data.model.response.popularmovie.PosterGeneralResponse
import com.ugurbuga.followtvmovie.data.model.response.popularmovie.PosterResponse
import com.ugurbuga.followtvmovie.data.model.response.search.SearchItemResponse
import com.ugurbuga.followtvmovie.data.model.response.search.SearchResponse
import com.ugurbuga.followtvmovie.domain.image.mapper.ImageMapper
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.MovieDetailUIModel
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import com.ugurbuga.followtvmovie.domain.poster.model.PosterUIModel
import com.ugurbuga.followtvmovie.domain.tvshowdetail.model.TvShowDetailUIModel
import java.util.Calendar
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

    fun upcomingToPosterUIModel(response: PosterGeneralResponse, mediaType: String): PosterUIModel {
        return PosterUIModel(
            page = response.page,
            posterList = response.results.map { toPosterItemUIModel(it, mediaType) }
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
        val releaseDate = response.releaseDate ?: response.firstAirDate.orEmpty()

        return PosterItemUIModel(
            id = response.id,
            name = response.title ?: response.name.orEmpty(),
            posterPath = imageMapper.getPosterUrl(response.posterPath, response.backdropPath),
            mediaType = mediaType,
            releaseDate = releaseDate,
            releaseDateLong = CommonUtil.getDateLong(releaseDate)
        )
    }

    private fun toPosterItemUIModel(response: SearchItemResponse): PosterItemUIModel {
        return PosterItemUIModel(
            id = response.id,
            name = response.name ?: response.title.orEmpty(),
            posterPath = imageMapper.getPosterUrl(
                response.posterPath,
                response.backdropPath,
                response.profilePath
            ),
            mediaType = response.mediaType,
            releaseDate = response.releaseDate ?: response.firstAirDate.orEmpty(),
            releaseDateLong = CommonUtil.getDateLong(response.releaseDate ?: response.firstAirDate)
        )
    }

    fun toPosterUIModel(movieDetail: MovieDetailUIModel, isWatched: Boolean, mediaType: String) =
        PosterItemModel(
            id = movieDetail.id,
            name = movieDetail.title,
            posterPath = movieDetail.posterPath,
            mediaType = mediaType,
            releaseDate = movieDetail.releaseDate,
            releaseDateLong = CommonUtil.getDateLong(movieDetail.releaseDate),
            isWatched = isWatched
        )

    fun toPosterUIModel(movieDetail: TvShowDetailUIModel, isWatched: Boolean, mediaType: String) =
        PosterItemModel(
            id = movieDetail.id,
            name = movieDetail.title,
            posterPath = movieDetail.posterPath,
            mediaType = mediaType,
            releaseDate = movieDetail.releaseDate,
            releaseDateLong = CommonUtil.getDateLong(movieDetail.releaseDate),
            isWatched = isWatched
        )

    fun toPosterItemUIModelList(item: MutableList<PosterItemModel>): MutableList<PosterItemUIModel> {
        return item.map {
            toPosterItemUIModel(it)
        }.toMutableList()
    }

    fun toPosterItemUIModel(item: PosterItemModel): PosterItemUIModel {
        return PosterItemUIModel(
            item.id,
            item.name,
            item.posterPath,
            item.mediaType,
            item.releaseDate,
            item.releaseDateLong,
            item.isWatched
        )
    }

    fun toNullablePosterItemUIModel(item: PosterItemModel?): PosterItemUIModel? {
        return item?.let {
            PosterItemUIModel(
                item.id,
                item.name,
                item.posterPath,
                item.mediaType,
                item.releaseDate,
                item.releaseDateLong,
                item.isWatched
            )
        } ?: run {
            null
        }
    }
}
