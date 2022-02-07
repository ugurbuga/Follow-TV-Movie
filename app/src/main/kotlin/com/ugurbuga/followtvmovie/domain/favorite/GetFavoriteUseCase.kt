package com.ugurbuga.followtvmovie.domain.favorite

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import com.ugurbuga.followtvmovie.repository.favorites.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) :
    FTMUseCase<GetFavoriteUseCase.GetFavoriteParams, PosterItemUIModel?>() {

    data class GetFavoriteParams(val type: String, val id: Int)

    override fun execute(params: GetFavoriteParams): Flow<Resource<PosterItemUIModel?>> {
        return favoritesRepository.getFavorite(params.type, params.id)
    }
}