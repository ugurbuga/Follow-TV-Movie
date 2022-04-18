package com.ugurbuga.followtvmovie.domain.favorite

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.MovieDetailUIModel
import com.ugurbuga.followtvmovie.domain.poster.mapper.PosterMapper
import com.ugurbuga.followtvmovie.repository.favorites.FavoritesRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class AddFavoriteMovieUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository, private val posterMapper: PosterMapper
) : FTMUseCase<AddFavoriteMovieUseCase.AddFavoriteParams, Unit>() {

    data class AddFavoriteParams(val movieDetail: MovieDetailUIModel, val isWatched: Boolean)

    override fun execute(params: AddFavoriteParams): Flow<ApiState<Unit>> {
        return favoritesRepository.insert(
            posterMapper.toPosterUIModel(
                params.movieDetail,
                params.isWatched
            )
        )
    }
}