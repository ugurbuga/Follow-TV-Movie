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

class GetFavoritesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) :
    UseCase<GetFavoritesUseCase.GetFavoriteParams, MutableList<PosterItemUIModel>>() {

    data class GetFavoriteParams(val mediaType: String, val isWatched: Boolean)

    override fun execute(params: GetFavoriteParams): Flow<ApiState<MutableList<PosterItemUIModel>>> {
        return favoritesRepository.getFavorites(params.mediaType, params.isWatched)
            .map { it.map { data -> getMapper(data) } }
    }

    private fun getMapper(item: MutableList<PosterItemModel>): MutableList<PosterItemUIModel> {
        return item.map {
            PosterItemUIModel(
                it.id,
                it.name,
                it.posterPath,
                it.mediaType,
                it.releaseDate,
                it.releaseDateLong,
                it.isWatched
            )
        }.toMutableList()
    }
}