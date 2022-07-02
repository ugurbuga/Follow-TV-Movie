package com.ugurbuga.followtvmovie.domain.moviedetail.mapper

import com.ugurbuga.followtvmovie.core.common.CommonUtil
import com.ugurbuga.followtvmovie.data.model.response.tvshowdetail.SeasonResponse
import com.ugurbuga.followtvmovie.data.model.response.tvshowdetail.TvShowDetailResponse
import com.ugurbuga.followtvmovie.domain.image.mapper.ImageMapper
import com.ugurbuga.followtvmovie.domain.tvshowdetail.model.SeasonUIModel
import com.ugurbuga.followtvmovie.domain.tvshowdetail.model.TvShowDetailUIModel
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
            releaseDate = response.firstAirDate.orEmpty(),
            releaseDateLong = CommonUtil.getDateLong(response.firstAirDate),
            status = response.status,
            title = response.name,
            voteAverage = response.voteAverage,
            seasons = response.seasons.map { toSeasonUIModel(it) },
        )
    }

    private fun toSeasonUIModel(response: SeasonResponse): SeasonUIModel {

        return SeasonUIModel(
            airDate = response.airDate,
            episodeCount = response.episodeCount,
            id = response.id,
            name = response.name,
            overview = response.overview,
            posterPath = response.posterPath,
            seasonNumber = response.seasonNumber
        )
    }
}
