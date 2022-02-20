package com.ugurbuga.followtvmovie.domain.moviedetail.mapper

import com.ugurbuga.followtvmovie.common.Util
import com.ugurbuga.followtvmovie.data.api.ApiConstants
import com.ugurbuga.followtvmovie.domain.moviedetail.credit.CastResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.credit.CreditResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.image.ImageResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.image.ImageUIModel
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.CastUIModel
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.GenreResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.GenreUIModel
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.MovieDetailResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.MovieDetailUIModel
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.TrailerUIModel
import com.ugurbuga.followtvmovie.domain.moviedetail.model.review.MovieReviewResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.review.ReviewResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.review.ReviewUIModel
import com.ugurbuga.followtvmovie.domain.moviedetail.model.trailer.TrailersResponse
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

    private fun toGenresUIModel(response: GenreResponse): GenreUIModel {
        return GenreUIModel(
            id = response.id, name = response.name
        )
    }

    fun toReviewUIModelList(response: MovieReviewResponse): List<ReviewUIModel> {
        return response.results.map { toReviewUI(it) }
    }

    private fun toReviewUI(response: ReviewResponse): ReviewUIModel {
        return ReviewUIModel(
            author = response.author,
            content = response.content,
            createdAt = Util.getDate(response.createdAt),
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

    fun toCastList(response: CreditResponse): ArrayList<CastUIModel> {
        return ArrayList(response.cast.map {
            getCast(it)
        })
    }

    private fun getCast(response: CastResponse): CastUIModel {
        return CastUIModel(
            creditId = response.creditId,
            name = response.name,
            character = response.character,
            profilePath = getProfilePath(response),
        )
    }

    private fun getProfilePath(response: CastResponse): String {
        return if (response.profilePath != null) {
            ApiConstants.BASE_IMAGE_URL + response.profilePath
        } else {
            Util.EMPTY_STRING
        }
    }

    fun toImageList(imageResponse: ImageResponse): ArrayList<ImageUIModel> {
        var list = arrayListOf<ImageUIModel>()
        list.addAll(imageResponse.posters.map {
            ImageUIModel(
                ApiConstants.BASE_IMAGE_URL + it.filePath, it.aspectRatio
            )
        })

        list.addAll(imageResponse.backdrops.map {
            ImageUIModel(
                ApiConstants.BASE_IMAGE_URL + it.filePath, it.aspectRatio
            )
        })

        list.addAll(imageResponse.logos.map {
            ImageUIModel(
                ApiConstants.BASE_IMAGE_URL + it.filePath, it.aspectRatio
            )
        })

        list = ArrayList(list.filter { it.imageUrl.isNotBlank() })
        list.sortBy { it.imageUrl }
        return list
    }
}
