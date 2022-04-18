package com.ugurbuga.followtvmovie.domain.moviedetail.usecase

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.common.map
import com.ugurbuga.followtvmovie.domain.moviedetail.external.ExternalIdsUIModel
import com.ugurbuga.followtvmovie.domain.moviedetail.mapper.MovieMapper
import com.ugurbuga.followtvmovie.repository.movie.MovieRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetMovieExternalUrlsUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieMapper: MovieMapper
) : FTMUseCase<GetMovieExternalUrlsUseCase.ExternalUrlParams, ExternalIdsUIModel>() {

    data class ExternalUrlParams(val movieId: String)

    override fun execute(params: ExternalUrlParams): Flow<ApiState<ExternalIdsUIModel>> {
        return movieRepository.getMovieExternalIds(params.movieId).map {
            it.map { imageResponse ->
                movieMapper.toExternalUrls(imageResponse)
            }
        }
    }
}