package com.ugurbuga.followtvmovie.watch.domain.detail

import com.ugurbuga.followtvmovie.watch.ui.detail.MediaType
import com.ugurbuga.followtvmovie.watch.ui.detail.MovieDetailUIModel
import com.ugurbuga.followtvmovie.watch.ui.detail.PosterItemUIModel
import com.ugurbuga.followtvmovie.watch.util.Util
import javax.inject.Inject

class PosterMapper @Inject constructor() {

    fun toPosterUIModel(movieDetail: MovieDetailUIModel, isWatched: Boolean) = PosterItemUIModel(
        id = movieDetail.id,
        name = movieDetail.title,
        posterPath = movieDetail.posterPath,
        mediaType = MediaType.MOVIE,
        releaseDate = movieDetail.releaseDate,
        releaseDateLong = Util.getDateLong(movieDetail.releaseDate),
        isWatched = isWatched
    )

}
