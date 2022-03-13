package com.ugurbuga.followtvmovie.domain.popular.movie.usecase

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.common.map
import com.ugurbuga.followtvmovie.domain.poster.mapper.PosterMapper
import com.ugurbuga.followtvmovie.domain.poster.model.PosterUIModel
import com.ugurbuga.followtvmovie.repository.movie.MovieRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UpcomingMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val posterMapper: PosterMapper
) :
    FTMUseCase<UpcomingMovieUseCase.UpcomingMovieParams, PosterUIModel>() {

    data class UpcomingMovieParams(val page: Int)

    override fun execute(params: UpcomingMovieParams): Flow<Resource<PosterUIModel>> {
        return movieRepository.getUpcomingMovies(params.page).map {
            it.map { response -> posterMapper.upcomingToPosterUIModel(response) }
        }
    }
}