package com.ugurbuga.followtvmovie.domain.poster

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.base.adapter.ListAdapterItem
import com.ugurbuga.followtvmovie.repository.favorites.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) :
    FTMUseCase<FavoriteUseCase.FavoriteParams, MutableList<ListAdapterItem>>() {

    data class FavoriteParams(val type: String)

    override fun execute(params: FavoriteParams): Flow<MutableList<ListAdapterItem>> {
        return favoritesRepository.getFavorites(params.type).map {
            (it as MutableList<ListAdapterItem>)
        }
    }
}