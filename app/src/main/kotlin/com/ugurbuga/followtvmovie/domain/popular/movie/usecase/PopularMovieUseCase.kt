package com.ugurbuga.followtvmovie.domain.popular.movie.usecase

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.common.map
import com.ugurbuga.followtvmovie.domain.poster.mapper.PosterMapper
import com.ugurbuga.followtvmovie.domain.poster.model.PosterUIModel
import com.ugurbuga.followtvmovie.repository.movie.MovieRepository
import com.ugurbuga.followtvmovie.ui.discover.MediaType
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PopularMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val posterMapper: PosterMapper
) :
    FTMUseCase<PopularMovieUseCase.PopularMovieParams, PosterUIModel>() {

    data class PopularMovieParams(val page: Int)

    override fun execute(params: PopularMovieParams): Flow<ApiState<PosterUIModel>> {
        return movieRepository.getPopularMovies(params.page).map {
            it.map { response -> posterMapper.toPosterUIModel(response, MediaType.MOVIE) }
        }
    }
}