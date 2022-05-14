package com.ugurbuga.followtvmovie.ui.seasondetail

import com.ugurbuga.followtvmovie.core.common.CommonUtil
import com.ugurbuga.followtvmovie.domain.seasondetail.model.SeasonDetailUIModel

data class SeasonDetailViewState(
    val seasonDetail: SeasonDetailUIModel? = null,
    val imageUrl: String? = null,
) {
    fun getPosterUrl(): String {
        return if (seasonDetail != null && seasonDetail.posterPath.isNotEmpty()) {
            seasonDetail.posterPath
        } else if (!imageUrl.isNullOrEmpty()) {
            imageUrl
        } else {
            CommonUtil.EMPTY_STRING
        }
    }
}