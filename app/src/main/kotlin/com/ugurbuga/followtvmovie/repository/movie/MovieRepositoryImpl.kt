package com.ugurbuga.followtvmovie.repository.movie

import com.ugurbuga.followtvmovie.base.FTMBaseRepository
import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.data.api.services.MovieService
import com.ugurbuga.followtvmovie.domain.moviedetail.credit.CreditResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.external.ExternalIdsResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.image.ImageResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.MovieDetailResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.review.ReviewGeneralResponse
import com.ugurbuga.followtvmovie.domain.moviedetail.model.trailer.TrailersResponse
import com.ugurbuga.followtvmovie.domain.popular.movie.model.MovieGeneralResponse
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService
) :
    MovieRepository, FTMBaseRepository() {

    override fun getPopularMovies(page: Int): Flow<ApiState<MovieGeneralResponse>> {
        return onApiCall { movieService.getPopularMovies(page) }
    }

    override fun getUpcomingMovies(page: Int): Flow<ApiState<MovieGeneralResponse>> {
        return onApiCall { movieService.getUpcomingMovies(page) }
    }

    override fun getMovieDetail(movieId: String): Flow<ApiState<MovieDetailResponse>> {
        return onApiCall { movieService.getMovieDetail(movieId) }
    }

    override fun getMovieReviews(movieId: String): Flow<ApiState<ReviewGeneralResponse>> {
        return onApiCall { movieService.getMovieReviews(movieId) }
    }

    override fun getMovieTrailers(movieId: String): Flow<ApiState<TrailersResponse>> {
        return onApiCall { movieService.getMovieTrailers(movieId) }
    }

    override fun getMovieCredits(movieId: String): Flow<ApiState<CreditResponse>> {
        return onApiCall { movieService.getMovieCredits(movieId) }
    }

    override fun getMovieImages(movieId: String): Flow<ApiState<ImageResponse>> {
        return onApiCall { movieService.getMovieImages(movieId) }
    }

    override fun getMovieExternalIds(movieId: String): Flow<ApiState<ExternalIdsResponse>> {
        return onApiCall { movieService.getMovieExternalIds(movieId) }
    }

    override fun getRecommendations(
        movieId: String,
        page: Int
    ): Flow<ApiState<MovieGeneralResponse>> {
        return onApiCall { movieService.getRecommendations(movieId, page) }
    }

    override fun getSimilarMovies(
        movieId: String,
        page: Int
    ): Flow<ApiState<MovieGeneralResponse>> {
        return onApiCall { movieService.getSimilarMovies(movieId, page) }
    }
}
