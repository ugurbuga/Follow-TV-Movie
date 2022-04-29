package com.ugurbuga.followtvmovie.domain.credit.mapper

import com.ugurbuga.followtvmovie.core.common.Util
import com.ugurbuga.followtvmovie.data.model.response.credit.CastResponse
import com.ugurbuga.followtvmovie.data.model.response.credit.CreditResponse
import com.ugurbuga.followtvmovie.domain.image.mapper.ImageMapper
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.CastUIModel
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
            name = response.name ?: response.title ?: Util.EMPTY_STRING,
            character = response.character ?: Util.EMPTY_STRING,
            profilePath = imageMapper.getPosterUrl(
                response.posterPath,
                response.backdropPath,
                response.profilePath
            ),
            mediaType = response.mediaType ?: Util.EMPTY_STRING
        )
    }

}