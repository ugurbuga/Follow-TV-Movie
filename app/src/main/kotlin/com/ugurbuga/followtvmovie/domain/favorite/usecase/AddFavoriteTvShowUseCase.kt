package com.ugurbuga.followtvmovie.domain.favorite.usecase

import com.ugurbuga.followtvmovie.base.FTMUseCase
import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.domain.poster.mapper.PosterMapper
import com.ugurbuga.followtvmovie.domain.tvshowdetail.detail.TvShowDetailUIModel
import com.ugurbuga.followtvmovie.repository.favorites.FavoritesRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class AddFavoriteTvShowUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository, private val posterMapper: PosterMapper
) : FTMUseCase<AddFavoriteTvShowUseCase.AddFavoriteParams, Unit>() {

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