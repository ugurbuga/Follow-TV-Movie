package com.ugurbuga.followtvmovie.domain.moviedetail.usecase

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.common.map
import com.ugurbuga.followtvmovie.domain.moviedetail.mapper.MovieMapper
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.TrailerUIModel
import com.ugurbuga.followtvmovie.repository.movie.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTrailersUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieMapper: MovieMapper
) : FTMUseCase<GetTrailersUseCase.TrailerParams, ArrayList<TrailerUIModel>>() {

    data class TrailerParams(val movieId: Int)

    override fun execute(params: TrailerParams): Flow<Resource<ArrayList<TrailerUIModel>>> {
        return movieRepository.getTrailers(params.movieId).map {
            it.map { trailersResponse ->
                movieMapper.toTrailerList(trailersResponse)
            }
        }
    }
}