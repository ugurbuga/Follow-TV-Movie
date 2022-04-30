package com.ugurbuga.followtvmovie.domain.moviedetail.mapper

import com.ugurbuga.followtvmovie.core.common.CommonUtil
import com.ugurbuga.followtvmovie.data.model.response.moviedetail.MovieDetailResponse
import com.ugurbuga.followtvmovie.domain.image.mapper.ImageMapper
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
            releaseDateLong = CommonUtil.getDateLong(response.releaseDate),
            status = response.status,
            title = response.title,
            voteAverage = response.voteAverage,
        )
    }
}
