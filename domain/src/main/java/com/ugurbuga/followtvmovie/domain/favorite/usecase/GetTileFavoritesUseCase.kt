package com.ugurbuga.followtvmovie.domain.favorite.usecase

import com.ugurbuga.followtvmovie.data.repository.favorites.FavoritesRepository
import com.ugurbuga.followtvmovie.domain.poster.mapper.PosterMapper
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import javax.inject.Inject

class GetTileFavoritesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    private val posterMapper: PosterMapper
) {
    data class GetFavoriteParams(val mediaType: String, val isWatched: Boolean)

    suspend fun execute(params: GetFavoriteParams): MutableList<PosterItemUIModel> {
        val mainList = favoritesRepository.getTileFavorites(params.mediaType, params.isWatched)
        return posterMapper.toPosterItemUIModelList(mainList)
    }
}