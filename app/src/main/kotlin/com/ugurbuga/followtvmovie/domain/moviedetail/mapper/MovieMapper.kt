package com.ugurbuga.followtvmovie.domain.moviedetail.mapper

import com.ugurbuga.followtvmovie.common.Util
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

    fun toGenresUIModel(response: GenreResponse): GenreUIModel {
        return GenreUIModel(
            id = response.id, name = response.name
        )
    }

    fun toReviewUIModelList(movieReviewResponse: MovieReviewResponse): List<ReviewUIModel> {
        return movieReviewResponse.results.map { toReviewUI(it) }
    }

    private fun toReviewUI(response: ReviewResponse): ReviewUIModel {
        return ReviewUIModel(
            author = response.author,
            content = response.content,
            createdAt = Util.getDate(response.createdAt),
            id = response.id
        )
    }

    fun toTrailerList(trailersResponse: TrailersResponse): ArrayList<TrailerUIModel> {
        val list = arrayListOf<TrailerUIModel>()
        trailersResponse.results.forEach {
            if (it.site == "YouTube") {
                list.add(TrailerUIModel(key = it.key, name = it.name))
            }
        }
        return list
    }
}
