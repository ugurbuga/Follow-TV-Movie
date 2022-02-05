package com.ugurbuga.followtvmovie.domain.moviedetail.mapper

import com.ugurbuga.followtvmovie.common.Util
import com.ugurbuga.followtvmovie.domain.moviedetail.model.GenreResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.GenreUIModel
import com.ugurbuga.followtvmovie.domain.moviedetail.model.MovieDetailResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.MovieDetailUIModel
import javax.inject.Inject

class MovieMapper @Inject constructor() {

    fun toMovieDetailUIModel(response: MovieDetailResponse): MovieDetailUIModel {

        return MovieDetailUIModel(
            adult = response.adult,
            genres = response.genres.map { toGenresUIModel(it) },
            id = response.id,
            overview = response.overview,
            posterPath = Util.getPosterPath(response.posterPath, response.backdropPath),
            releaseDate = response.releaseDate,
            status = response.status,
            title = response.title,
            video = response.video,
            voteAverage = response.voteAverage,
        )
    }

    fun toGenresUIModel(response: GenreResponse): GenreUIModel {
        return GenreUIModel(
            id = response.id,
            name = response.name
        )
    }
}
