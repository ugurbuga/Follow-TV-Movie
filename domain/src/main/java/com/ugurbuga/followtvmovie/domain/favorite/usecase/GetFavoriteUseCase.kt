package com.ugurbuga.followtvmovie.domain.favorite.usecase

import com.ugurbuga.followtvmovie.core.base.UseCase
import com.ugurbuga.followtvmovie.core.common.ApiState
import com.ugurbuga.followtvmovie.core.common.map
import com.ugurbuga.followtvmovie.data.model.PosterItemModel
import com.ugurbuga.followtvmovie.data.repository.favorites.FavoritesRepository
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavoriteUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) :
    UseCase<GetFavoriteUseCase.GetFavoriteParams, PosterItemUIModel?>() {

    data class GetFavoriteParams(val mediaType: String, val id: String)

    override fun execute(params: GetFavoriteParams): Flow<ApiState<PosterItemUIModel?>> {
        return favoritesRepository.getFavorite(params.mediaType, params.id)
            .map { it.map { data -> getMapper(data) } }
    }

    private fun getMapper(it: PosterItemModel?): PosterItemUIModel? {
        if (it == null) {
            return null
        }
        return PosterItemUIModel(
            it.id,
            it.name,
            it.posterPath,
            it.mediaType,
            it.releaseDate,
            it.releaseDateLong,
            it.isWatched
        )
    }
}