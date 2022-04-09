package com.ugurbuga.followtvmovie.watch.domain.detail

import com.ugurbuga.followtvmovie.watch.base.FTMUseCase
import com.ugurbuga.followtvmovie.watch.repository.favorites.FavoritesRepository
import com.ugurbuga.followtvmovie.watch.util.Resource
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class DeleteFavoriteUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) :
    FTMUseCase<DeleteFavoriteUseCase.DeleteFavoriteParams, Unit>() {

    data class DeleteFavoriteParams(val id: String)

    override fun execute(params: DeleteFavoriteParams): Flow<Resource<Unit>> {
        return favoritesRepository.delete(params.id)
    }
}