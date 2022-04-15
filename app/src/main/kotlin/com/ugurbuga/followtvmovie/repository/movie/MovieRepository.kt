package com.ugurbuga.followtvmovie.repository.movie

import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.domain.moviedetail.credit.CreditResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.external.ExternalIdsResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.image.ImageResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.MovieDetailResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.review.ReviewGeneralResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.trailer.TrailersResponse
import com.ugurbuga.followtvmovie.domain.popular.movie.model.MovieGeneralResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPopularMovies(page: Int): Flow<Resource<MovieGeneralResponse>>

    fun getUpcomingMovies(page: Int): Flow<Resource<MovieGeneralResponse>>

    fun getMovieDetail(movieId: String): Flow<Resource<MovieDetailResponse>>

    fun getMovieReviews(movieId: String): Flow<Resource<ReviewGeneralResponse>>

    fun getMovieTrailers(movieId: String): Flow<Resource<TrailersResponse>>

    fun getMovieCredits(movieId: String): Flow<Resource<CreditResponse>>

    fun getMovieImages(movieId: String): Flow<Resource<ImageResponse>>

    fun getMovieExternalIds(movieId: String): Flow<Resource<ExternalIdsResponse>>

    fun getRecommendations(movieId: String, page: Int): Flow<Resource<MovieGeneralResponse>>

    fun getSimilarMovies(movieId: String, page: Int): Flow<Resource<MovieGeneralResponse>>
}
