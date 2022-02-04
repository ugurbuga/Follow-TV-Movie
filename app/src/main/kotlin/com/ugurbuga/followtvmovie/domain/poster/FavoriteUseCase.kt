package com.ugurbuga.followtvmovie.domain.poster

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import com.ugurbuga.followtvmovie.repository.favorites.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository) :
    FTMUseCase<FavoriteUseCase.FavoriteParams, MutableList<PosterItemUIModel>>() {

    data class FavoriteParams(val type: String)

    override fun execute(params: FavoriteParams): Flow<MutableList<PosterItemUIModel>> {
        return favoritesRepository.getFavorites(params.type)
    }
}