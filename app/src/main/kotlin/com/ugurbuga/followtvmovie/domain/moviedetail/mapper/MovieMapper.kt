package com.ugurbuga.followtvmovie.domain.moviedetail.mapper

import com.ugurbuga.followtvmovie.common.Util
import com.ugurbuga.followtvmovie.domain.image.ImageMapper
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.MovieDetailResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.MovieDetailUIModel
import javax.inject.Inject

class MovieMapper @Inject constructor(
    private val imageMapper: ImageMapper,
    private val genreMapper: GenreMapper,
) {

    fun toMovieDetailUIModel(response: MovieDetailResponse): MovieDetailUIModel {

        return MovieDetailUIModel(
            adult = response.adult,
            genres = response.genres.map { genreMapper.toGenresUIModel(it) },
            id = response.id,
            overview = response.overview,
            posterPath = imageMapper.getPosterUrl(response.posterPath, response.backdropPath),
            releaseDate = response.releaseDate,
            releaseDateLong = Util.getDateLong(response.releaseDate),
            status = response.status,
            title = response.title,
            voteAverage = response.voteAverage,
        )
    }
}
