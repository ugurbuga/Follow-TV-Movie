package com.ugurbuga.followtvmovie.domain.seasondetail.mapper

import com.ugurbuga.followtvmovie.data.model.response.seasondetail.CrewResponse
import com.ugurbuga.followtvmovie.data.model.response.seasondetail.EpisodeResponse
import com.ugurbuga.followtvmovie.data.model.response.seasondetail.GuestStarResponse
import com.ugurbuga.followtvmovie.data.model.response.seasondetail.SeasonDetailResponse
import com.ugurbuga.followtvmovie.domain.image.mapper.ImageMapper
import com.ugurbuga.followtvmovie.domain.seasondetail.model.CrewUIModel
import com.ugurbuga.followtvmovie.domain.seasondetail.model.EpisodeUIModel
import com.ugurbuga.followtvmovie.domain.seasondetail.model.GuestStarUIModel
import com.ugurbuga.followtvmovie.domain.seasondetail.model.SeasonDetailUIModel
import javax.inject.Inject

class SeasonMapper @Inject constructor(
    private val imageMapper: ImageMapper
) {

    fun toSeasonDetail(
        response: SeasonDetailResponse,
    ): SeasonDetailUIModel {
        return SeasonDetailUIModel(
            airDate = response.airDate,
            episodes = response.episodes.map { toEpisode(it) },
            _id = response._id,
            id = response.id,
            name = response.name,
            overview = response.overview,
            posterPath = imageMapper.getPosterUrl(response.posterPath),
            seasonNumber = response.seasonNumber,
        )
    }

    private fun toEpisode(response: EpisodeResponse): EpisodeUIModel {
        return EpisodeUIModel(
            airDate = response.airDate,
            crew = response.crew.map { toCrew(it) },
            episodeNumber = response.episodeNumber,
            guestStars = response.guestStars.map { toGuestStar(it) },
            id = response.id,
            name = response.name,
            overview = response.overview,
            productionCode = response.productionCode,
            seasonNumber = response.seasonNumber,
            stillPath = imageMapper.getPosterUrl(response.stillPath),
            voteAverage = response.voteAverage,
            voteCount = response.voteCount,
        )
    }

    private fun toGuestStar(response: GuestStarResponse): GuestStarUIModel {
        return GuestStarUIModel(
            adult = response.adult,
            character = response.character,
            creditId = response.creditId,
            gender = response.gender,
            id = response.id,
            knownForDepartment = response.knownForDepartment,
            name = response.name,
            order = response.order,
            originalName = response.originalName,
            popularity = response.popularity,
            profilePath = imageMapper.getPosterUrl(response.profilePath),
        )
    }

    private fun toCrew(response: CrewResponse): CrewUIModel {
        return CrewUIModel(
            adult = response.adult,
            creditId = response.creditId,
            department = response.department,
            gender = response.gender,
            id = response.id,
            job = response.job,
            knownForDepartment = response.knownForDepartment,
            name = response.name,
            originalName = response.originalName,
            popularity = response.popularity,
            profilePath = imageMapper.getPosterUrl(response.profilePath),
        )
    }
}
