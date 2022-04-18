package com.ugurbuga.followtvmovie.domain.moviedetail.usecase

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.common.map
import com.ugurbuga.followtvmovie.domain.moviedetail.credit.mapper.CreditMapper
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.CastUIModel
import com.ugurbuga.followtvmovie.repository.movie.MovieRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetMovieCastsUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val creditMapper: CreditMapper
) : FTMUseCase<GetMovieCastsUseCase.CastParams, ArrayList<CastUIModel>>() {

    data class CastParams(val movieId: String)

    override fun execute(params: CastParams): Flow<ApiState<ArrayList<CastUIModel>>> {
        return movieRepository.getMovieCredits(params.movieId).map {
            it.map { creditResponse ->
                creditMapper.toCastList(creditResponse)
            }
        }
    }
}