package com.ugurbuga.followtvmovie.domain.popular.movie.usecase

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.common.map
import com.ugurbuga.followtvmovie.domain.poster.mapper.PosterMapper
import com.ugurbuga.followtvmovie.domain.poster.model.PosterUIModel
import com.ugurbuga.followtvmovie.repository.movie.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PopularMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val posterMapper: PosterMapper
) :
    FTMUseCase<PopularMovieUseCase.PopularMovieParams, PosterUIModel>() {

    data class PopularMovieParams(val page: Int)

    override fun execute(params: PopularMovieParams): Flow<Resource<PosterUIModel>> {
        return movieRepository.getPopularMovies(params.page).map {
            it.map { response -> posterMapper.getPosterUIModel(response) }
        }
    }
}