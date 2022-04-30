package com.ugurbuga.followtvmovie.domain.favorite.usecase

import com.ugurbuga.followtvmovie.core.base.UseCase
import com.ugurbuga.followtvmovie.core.common.ApiState
import com.ugurbuga.followtvmovie.data.repository.favorites.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) :
    UseCase<DeleteFavoriteUseCase.DeleteFavoriteParams, Unit>() {

    data class DeleteFavoriteParams(val id: String)

    override fun execute(params: DeleteFavoriteParams): Flow<ApiState<Unit>> {
        return favoritesRepository.delete(params.id)
    }
}