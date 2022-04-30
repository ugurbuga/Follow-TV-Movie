package com.ugurbuga.followtvmovie.domain.person.mapper

import com.ugurbuga.followtvmovie.core.common.Util
import com.ugurbuga.followtvmovie.data.model.response.person.PersonDetailResponse
import com.ugurbuga.followtvmovie.domain.image.mapper.ImageMapper
import com.ugurbuga.followtvmovie.domain.person.model.PersonDetailUIModel
import javax.inject.Inject

class PersonMapper @Inject constructor(
    private val imageMapper: ImageMapper
) {

    fun toPersonDetailUIModel(response: PersonDetailResponse): PersonDetailUIModel {

        return PersonDetailUIModel(
            name = response.name,
            biography = response.biography,
            profilePath = imageMapper.getPosterUrl(response.profilePath),
            birthDay = response.birthDay ?: Util.EMPTY_STRING,
            deathDay = response.deathDay ?: Util.EMPTY_STRING,
        )
    }

}
