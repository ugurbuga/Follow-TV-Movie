package com.ugurbuga.followtvmovie.ui.tvshowdetail

import com.ugurbuga.followtvmovie.domain.tvshowdetail.model.TvShowDetailUIModel


data class TvShowDetailViewState(
    val tvShowDetail: TvShowDetailUIModel? = null,
    val isSeasonExpanded: Boolean = false
) {
    fun hasSeason(): Boolean {
        return tvShowDetail != null && tvShowDetail.seasons.isNotEmpty()
    }
}