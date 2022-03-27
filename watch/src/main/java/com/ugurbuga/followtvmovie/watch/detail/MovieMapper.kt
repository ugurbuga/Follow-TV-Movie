package com.ugurbuga.followtvmovie.watch.detail

import com.ugurbuga.followtvmovie.watch.detail.model.GenreResponse
import com.ugurbuga.followtvmovie.watch.detail.model.MovieDetailResponse
import com.ugurbuga.followtvmovie.watch.util.Util
import javax.inject.Inject

class MovieMapper @Inject constructor(
    private val imageMapper: ImageMapper
) {

    fun toMovieDetailUIModel(response: MovieDetailResponse): MovieDetailUIModel {
        return MovieDetailUIModel(
            adult = response.adult,
            genres = response.genres.map { toGenresUIModel(it) },
            id = response.id,
            overview = response.overview,
            posterPath = imageMapper.getPosterUrl(response.posterPath, response.backdropPath),
            releaseDate = response.releaseDate,
            releaseDateLong = Util.getDateLong(response.releaseDate),
            status = response.status,
            title = response.title,
            video = response.video,
            voteAverage = response.voteAverage,
        )
    }

    private fun toGenresUIModel(response: GenreResponse): GenreUIModel {
        return GenreUIModel(
            id = response.id, name = response.name
        )
    }
}
