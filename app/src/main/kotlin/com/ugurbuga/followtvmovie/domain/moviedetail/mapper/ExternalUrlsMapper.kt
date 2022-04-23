package com.ugurbuga.followtvmovie.domain.moviedetail.mapper

import com.ugurbuga.followtvmovie.domain.moviedetail.external.ExternalIdsResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.external.ExternalIdsUIModel
import javax.inject.Inject

class ExternalUrlsMapper @Inject constructor() {

    fun toExternalUrls(response: ExternalIdsResponse): ExternalIdsUIModel {
        return ExternalIdsUIModel(
            facebookId = response.facebookId,
            imdbId = response.imdbId,
            instagramId = response.instagramId,
            twitterId = response.twitterId
        )
    }
}
