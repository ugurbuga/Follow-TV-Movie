package com.ugurbuga.followtvmovie.domain.moviedetail.mapper

import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.GenreResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.GenreUIModel
import javax.inject.Inject

class GenreMapper @Inject constructor() {

    fun toGenresUIModel(response: GenreResponse): GenreUIModel {
        return GenreUIModel(
            id = response.id,
            name = response.name
        )
    }
}
