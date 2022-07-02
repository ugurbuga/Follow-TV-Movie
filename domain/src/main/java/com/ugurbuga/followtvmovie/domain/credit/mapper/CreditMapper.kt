package com.ugurbuga.followtvmovie.domain.credit.mapper

import com.ugurbuga.followtvmovie.core.common.CommonUtil
import com.ugurbuga.followtvmovie.data.model.response.credit.CastResponse
import com.ugurbuga.followtvmovie.data.model.response.credit.CreditResponse
import com.ugurbuga.followtvmovie.domain.image.mapper.ImageMapper
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.CastUIModel
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import javax.inject.Inject

class CreditMapper @Inject constructor(
    private val imageMapper: ImageMapper
) {
    fun toCastList(response: CreditResponse): ArrayList<CastUIModel> {
        return ArrayList(response.cast.map {
            getCast(it)
        })
    }

    private fun getCast(response: CastResponse): CastUIModel {
        return CastUIModel(
            id = response.id,
            name = response.name ?: response.title.orEmpty(),
            character = response.character.orEmpty(),
            profilePath = imageMapper.getPosterUrl(
                response.posterPath,
                response.backdropPath,
                response.profilePath
            ),
            mediaType = response.mediaType.orEmpty()
        )
    }

    fun toPosterList(response: CreditResponse): ArrayList<PosterItemUIModel> {
        return ArrayList(response.cast
            .sortedByDescending { getReleaseDate(it) }
            .map { getPoster(it) }
            .distinctBy { it.name })
    }

    private fun getReleaseDate(cast: CastResponse): Long {
        val releaseDate = cast.releaseDate ?: cast.firstAirDate.orEmpty()
        return CommonUtil.getDateLong(releaseDate)
    }

    private fun getPoster(response: CastResponse): PosterItemUIModel {
        val releaseDate = response.releaseDate ?: response.firstAirDate.orEmpty()
        return PosterItemUIModel(
            id = response.id,
            name = response.title ?: response.name.orEmpty(),
            posterPath = imageMapper.getPosterUrl(
                response.posterPath,
                response.backdropPath,
                response.profilePath
            ),
            mediaType = response.mediaType.orEmpty(),
            releaseDate = releaseDate,
            releaseDateLong = CommonUtil.getDateLong(releaseDate)
        )
    }

}