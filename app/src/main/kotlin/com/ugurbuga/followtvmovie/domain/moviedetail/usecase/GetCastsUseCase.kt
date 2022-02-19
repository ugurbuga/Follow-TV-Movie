package com.ugurbuga.followtvmovie.domain.moviedetail.usecase

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.common.map
import com.ugurbuga.followtvmovie.domain.moviedetail.mapper.MovieMapper
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.CastUIModel
import com.ugurbuga.followtvmovie.repository.movie.MovieRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetCastsUseCase @Inject constructor(
    private val movieRepository: MovieRepository, private val movieMapper: MovieMapper
) : FTMUseCase<GetCastsUseCase.CastParams, ArrayList<CastUIModel>>() {

    data class CastParams(val movieId: Int)

    override fun execute(params: CastParams): Flow<Resource<ArrayList<CastUIModel>>> {
        return movieRepository.getCredits(params.movieId).map {
            it.map { creditResponse ->
                movieMapper.toCastList(creditResponse)
            }
        }
    }
}