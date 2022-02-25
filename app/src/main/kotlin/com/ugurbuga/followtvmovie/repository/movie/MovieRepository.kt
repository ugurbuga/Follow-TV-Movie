package com.ugurbuga.followtvmovie.repository.movie

import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.domain.moviedetail.credit.CreditResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.external.ExternalIdsResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.image.MovieImageResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.MovieDetailResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.review.MovieReviewResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.trailer.TrailersResponse
import com.ugurbuga.followtvmovie.domain.popular.movie.model.MovieGeneralResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPopularMovies(page: Int): Flow<Resource<MovieGeneralResponse>>

    fun getUpcomingMovies(page: Int): Flow<Resource<MovieGeneralResponse>>

    fun getMovieDetail(movieId: String): Flow<Resource<MovieDetailResponse>>

    fun getMovieReviews(movieId: String): Flow<Resource<MovieReviewResponse>>

    fun getMovieTrailers(movieId: String): Flow<Resource<TrailersResponse>>

    fun getMovieCredits(movieId: String): Flow<Resource<CreditResponse>>

    fun getMovieImages(movieId: String): Flow<Resource<MovieImageResponse>>

    fun getMovieExternalIds(movieId: String): Flow<Resource<ExternalIdsResponse>>
}
