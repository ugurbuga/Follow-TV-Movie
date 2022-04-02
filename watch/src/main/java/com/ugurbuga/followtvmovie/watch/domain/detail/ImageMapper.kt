package com.ugurbuga.followtvmovie.watch.domain.detail

import com.ugurbuga.followtvmovie.watch.data.api.ApiConstants
import com.ugurbuga.followtvmovie.watch.util.Util
import javax.inject.Inject

class ImageMapper @Inject constructor() {

    fun getPosterUrl(
        posterPath: String? = Util.EMPTY_STRING,
        backdropPath: String? = Util.EMPTY_STRING,
        profilePath: String? = Util.EMPTY_STRING
    ): String {

        return if (!posterPath.isNullOrBlank()) {
            ApiConstants.BASE_IMAGE_URL + posterPath

        } else if (!backdropPath.isNullOrBlank()) {
            ApiConstants.BASE_IMAGE_URL + backdropPath

        } else if (!profilePath.isNullOrBlank()) {
            ApiConstants.BASE_IMAGE_URL + profilePath

        } else {
            Util.EMPTY_STRING
        }
    }

}
