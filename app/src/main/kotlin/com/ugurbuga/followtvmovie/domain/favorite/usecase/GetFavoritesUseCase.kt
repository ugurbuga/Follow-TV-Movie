package com.ugurbuga.followtvmovie.domain.favorite.usecase

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import com.ugurbuga.followtvmovie.repository.favorites.FavoritesRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetFavoritesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) :
    FTMUseCase<GetFavoritesUseCase.GetFavoriteParams, MutableList<PosterItemUIModel>>() {

    data class GetFavoriteParams(val mediaType: String, val isWatched: Boolean)

    override fun execute(params: GetFavoriteParams): Flow<ApiState<MutableList<PosterItemUIModel>>> {
        return favoritesRepository.getFavorites(params.mediaType, params.isWatched)
    }
}