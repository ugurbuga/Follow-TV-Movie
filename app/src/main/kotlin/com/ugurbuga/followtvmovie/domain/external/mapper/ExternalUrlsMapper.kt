package com.ugurbuga.followtvmovie.domain.external.mapper

import com.ugurbuga.followtvmovie.domain.external.model.ExternalIdsResponse
import com.ugurbuga.followtvmovie.domain.external.model.ExternalIdsUIModel
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
