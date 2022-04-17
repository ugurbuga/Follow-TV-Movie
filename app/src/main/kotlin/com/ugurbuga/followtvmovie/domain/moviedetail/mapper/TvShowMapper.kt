package com.ugurbuga.followtvmovie.domain.moviedetail.mapper

import com.ugurbuga.followtvmovie.common.Util
import com.ugurbuga.followtvmovie.domain.image.ImageMapper
import com.ugurbuga.followtvmovie.domain.moviedetail.external.ExternalIdsResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.external.ExternalIdsUIModel
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.GenreResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.GenreUIModel
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.TrailerUIModel
import com.ugurbuga.followtvmovie.domain.moviedetail.model.review.ReviewGeneralResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.review.ReviewResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.review.ReviewUIModel
import com.ugurbuga.followtvmovie.domain.moviedetail.model.trailer.TrailersResponse
import com.ugurbuga.followtvmovie.domain.tvshowdetail.detail.TvShowDetailResponse
import com.ugurbuga.followtvmovie.domain.tvshowdetail.detail.TvShowDetailUIModel
import javax.inject.Inject

class TvShowMapper @Inject constructor(
    private val imageMapper: ImageMapper
) {

    fun toTvShowDetailUIModel(response: TvShowDetailResponse): TvShowDetailUIModel {

        return TvShowDetailUIModel(
            adult = response.adult,
            genres = response.genres.map { toGenresUIModel(it) },
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

    private fun toGenresUIModel(response: GenreResponse): GenreUIModel {
        return GenreUIModel(
            id = response.id, name = response.name
        )
    }

    fun toReviewUIModelList(response: ReviewGeneralResponse): List<ReviewUIModel> {
        return response.results.map { toReviewUI(it) }
    }

    private fun toReviewUI(response: ReviewResponse): ReviewUIModel {
        return ReviewUIModel(
            author = response.author,
            content = response.content,
            createdAt = response.createdAt,
            id = response.id
        )
    }

    fun toTrailerList(response: TrailersResponse): ArrayList<TrailerUIModel> {
        val list = arrayListOf<TrailerUIModel>()
        response.results.forEach {
            if (it.site == "YouTube") {
                list.add(TrailerUIModel(key = it.key, name = it.name))
            }
        }
        return list
    }

    fun toExternalUrls(response: ExternalIdsResponse): ExternalIdsUIModel {
        return ExternalIdsUIModel(
            facebookId = response.facebookId,
            imdbId = response.imdbId,
            instagramId = response.instagramId,
            twitterId = response.twitterId
        )
    }
}
