package com.ugurbuga.followtvmovie.watch.domain.detail

import com.ugurbuga.followtvmovie.watch.base.FTMUseCase
import com.ugurbuga.followtvmovie.watch.repository.favorites.FavoritesRepository
import com.ugurbuga.followtvmovie.watch.ui.detail.MovieDetailUIModel
import com.ugurbuga.followtvmovie.watch.util.Resource
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class AddFavoriteUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository, private val posterMapper: PosterMapper
) : FTMUseCase<AddFavoriteUseCase.AddFavoriteParams, Unit>() {

    data class AddFavoriteParams(val movieDetail: MovieDetailUIModel, val isWatched: Boolean)

    override fun execute(params: AddFavoriteParams): Flow<Resource<Unit>> {
        return favoritesRepository.insert(
            posterMapper.toPosterUIModel(
                params.movieDetail,
                params.isWatched
            )
        )
    }
}