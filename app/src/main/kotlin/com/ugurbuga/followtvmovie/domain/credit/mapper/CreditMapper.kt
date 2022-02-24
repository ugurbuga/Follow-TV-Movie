package com.ugurbuga.followtvmovie.domain.credit.mapper

import com.ugurbuga.followtvmovie.common.Util
import com.ugurbuga.followtvmovie.data.api.ApiConstants
import com.ugurbuga.followtvmovie.domain.credit.model.CreditDetailResponse
import com.ugurbuga.followtvmovie.domain.credit.model.CreditDetailUIModel
import com.ugurbuga.followtvmovie.domain.credit.model.KnownForResponse
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import javax.inject.Inject

class CreditMapper @Inject constructor() {

    fun toCreditDetailUIModel(response: CreditDetailResponse): CreditDetailUIModel {

        return CreditDetailUIModel(
            name = response.person.name,
            profilePath = ApiConstants.BASE_IMAGE_URL + response.person.profilePath,
            knownFor = response.person.knownFor.map {
                getKnownFor(it)
            },
        )
    }

    private fun getKnownFor(response: KnownForResponse): PosterItemUIModel {
        return PosterItemUIModel(
            id = response.id,
            name = response.title ?: "",
            posterPath = Util.getPosterPath(response.posterPath, response.backdropPath),
            type = response.mediaType
        )
    }

}
