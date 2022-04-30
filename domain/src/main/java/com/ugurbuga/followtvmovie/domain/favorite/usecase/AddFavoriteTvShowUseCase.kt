package com.ugurbuga.followtvmovie.domain.favorite.usecase

import com.ugurbuga.followtvmovie.core.base.UseCase
import com.ugurbuga.followtvmovie.core.common.ApiState
import com.ugurbuga.followtvmovie.data.repository.favorites.FavoritesRepository
import com.ugurbuga.followtvmovie.domain.poster.mapper.PosterMapper
import com.ugurbuga.followtvmovie.domain.tvshowdetail.detail.TvShowDetailUIModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddFavoriteTvShowUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository, private val posterMapper: PosterMapper
) : UseCase<AddFavoriteTvShowUseCase.AddFavoriteParams, Unit>() {

    data class AddFavoriteParams(val tvShowDetail: TvShowDetailUIModel, val isWatched: Boolean)

    override fun execute(params: AddFavoriteParams): Flow<ApiState<Unit>> {
        return favoritesRepository.insert(
            posterMapper.toPosterUIModel(
                params.tvShowDetail,
                params.isWatched
            )
        )
    }
}