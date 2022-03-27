package com.ugurbuga.followtvmovie.watch.detail

import com.ugurbuga.followtvmovie.watch.detail.model.PosterItemUIModel
import com.ugurbuga.followtvmovie.watch.repository.FTMUseCase
import com.ugurbuga.followtvmovie.watch.repository.favorites.FavoritesRepository
import com.ugurbuga.followtvmovie.watch.util.Resource
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetFavoriteUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) :
    FTMUseCase<GetFavoriteUseCase.GetFavoriteParams, PosterItemUIModel?>() {

    data class GetFavoriteParams(val mediaType: String, val id: String)

    override fun execute(params: GetFavoriteParams): Flow<Resource<PosterItemUIModel?>> {
        return favoritesRepository.getFavorite(params.mediaType, params.id)
    }
}