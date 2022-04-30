package com.ugurbuga.followtvmovie.domain.person.mapper

import com.ugurbuga.followtvmovie.core.common.CommonUtil
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
            birthDay = response.birthDay ?: CommonUtil.EMPTY_STRING,
            deathDay = response.deathDay ?: CommonUtil.EMPTY_STRING,
        )
    }

}
