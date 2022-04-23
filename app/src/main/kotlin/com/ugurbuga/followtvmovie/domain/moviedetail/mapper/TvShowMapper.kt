package com.ugurbuga.followtvmovie.domain.moviedetail.mapper

import com.ugurbuga.followtvmovie.common.Util
import com.ugurbuga.followtvmovie.domain.image.mapper.ImageMapper
import com.ugurbuga.followtvmovie.domain.tvshowdetail.detail.TvShowDetailResponse
import com.ugurbuga.followtvmovie.domain.tvshowdetail.detail.TvShowDetailUIModel
import javax.inject.Inject

class TvShowMapper @Inject constructor(
    private val imageMapper: ImageMapper,
    private val genreMapper: GenreMapper
) {

    fun toTvShowDetailUIModel(response: TvShowDetailResponse): TvShowDetailUIModel {

        return TvShowDetailUIModel(
            adult = response.adult,
            genres = response.genres.map { genreMapper.toGenresUIModel(it) },
            id = response.id,
            overview = response.overview,
            posterPath = imageMapper.getPosterUrl(response.posterPath, response.backdropPath),
            releaseDate = response.firstAirDate ?: Util.EMPTY_STRING,
            releaseDateLong = Util.getDateLong(response.firstAirDate),
            status = response.status,
            title = response.name,
            voteAverage = response.voteAverage,
        )
    }
}
