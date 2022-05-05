package com.ugurbuga.followtvmovie.domain.favorite.usecase

import com.ugurbuga.followtvmovie.core.base.UseCase
import com.ugurbuga.followtvmovie.core.common.ApiState
import com.ugurbuga.followtvmovie.core.common.map
import com.ugurbuga.followtvmovie.data.repository.favorites.FavoritesRepository
import com.ugurbuga.followtvmovie.domain.poster.mapper.PosterMapper
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetFavoritesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    private val posterMapper: PosterMapper
) :
    UseCase<GetFavoritesUseCase.GetFavoriteParams, MutableList<PosterItemUIModel>>() {

    data class GetFavoriteParams(val mediaType: String, val isWatched: Boolean)

    override fun execute(params: GetFavoriteParams): Flow<ApiState<MutableList<PosterItemUIModel>>> {
        return favoritesRepository.getFavorites(params.mediaType, params.isWatched)
            .map { it.map { data -> posterMapper.toPosterItemUIModelList(data) } }
    }
}