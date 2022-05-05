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

class GetFavoriteUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    private val posterMapper: PosterMapper
) :
    UseCase<GetFavoriteUseCase.GetFavoriteParams, PosterItemUIModel?>() {

    data class GetFavoriteParams(val mediaType: String, val id: String)

    override fun execute(params: GetFavoriteParams): Flow<ApiState<PosterItemUIModel?>> {
        return favoritesRepository.getFavorite(params.mediaType, params.id)
            .map { it.map { data -> posterMapper.toNullablePosterItemUIModel(data) } }
    }
}