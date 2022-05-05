package com.ugurbuga.followtvmovie.domain.popular.movie.usecase

import com.ugurbuga.followtvmovie.core.base.UseCase
import com.ugurbuga.followtvmovie.core.common.ApiState
import com.ugurbuga.followtvmovie.core.common.map
import com.ugurbuga.followtvmovie.data.repository.movie.MovieRepository
import com.ugurbuga.followtvmovie.domain.poster.mapper.PosterMapper
import com.ugurbuga.followtvmovie.domain.poster.model.PosterUIModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PopularMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val posterMapper: PosterMapper
) :
    UseCase<PopularMovieUseCase.PopularMovieParams, PosterUIModel>() {

    data class PopularMovieParams(val mediaType: String, val page: Int)

    override fun execute(params: PopularMovieParams): Flow<ApiState<PosterUIModel>> {
        return movieRepository.getPopularMovies(params.page).map {
            it.map { response -> posterMapper.toPosterUIModel(response, params.mediaType) }
        }
    }
}