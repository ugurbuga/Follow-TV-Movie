package com.ugurbuga.followtvmovie.ui.search

import com.ugurbuga.followtvmovie.domain.poster.model.PosterUIModel

data class SearchViewState(
    val poster: PosterUIModel = PosterUIModel()
)