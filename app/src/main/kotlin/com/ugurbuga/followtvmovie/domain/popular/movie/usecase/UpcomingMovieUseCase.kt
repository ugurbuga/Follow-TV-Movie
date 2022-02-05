package com.ugurbuga.followtvmovie.domain.popular.movie.usecase

import com.ugurbuga.followtvmovie.base.FTMResourceUseCase
import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.common.map
import com.ugurbuga.followtvmovie.domain.poster.mapper.PosterMapper
import com.ugurbuga.followtvmovie.domain.poster.model.PosterUIModel
import com.ugurbuga.followtvmovie.repository.movie.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UpcomingMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val posterMapper: PosterMapper
) :
    FTMResourceUseCase<UpcomingMovieUseCase.UpcomingMovieParams, PosterUIModel>() {

    data class UpcomingMovieParams(val page: Int)

    override fun execute(params: UpcomingMovieParams): Flow<Resource<PosterUIModel>> {
        return movieRepository.getUpcomingMovies(params.page).map {
            it.map { response -> posterMapper.toPosterUIModel(response) }
        }
    }
}