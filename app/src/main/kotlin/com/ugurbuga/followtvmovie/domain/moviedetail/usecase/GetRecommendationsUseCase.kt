package com.ugurbuga.followtvmovie.domain.moviedetail.usecase

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.common.map
import com.ugurbuga.followtvmovie.domain.poster.mapper.PosterMapper
import com.ugurbuga.followtvmovie.domain.poster.model.PosterUIModel
import com.ugurbuga.followtvmovie.repository.movie.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetRecommendationsUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val posterMapper: PosterMapper
) :
    FTMUseCase<GetRecommendationsUseCase.Recommendations, PosterUIModel>() {

    data class Recommendations(var movieId: String, val page: Int)

    override fun execute(params: Recommendations): Flow<Resource<PosterUIModel>> {
        return movieRepository.getRecommendations(params.movieId, params.page).map {
            it.map { response -> posterMapper.toPosterUIModel(response) }
        }
    }
}