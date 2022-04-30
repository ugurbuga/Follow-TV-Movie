package com.ugurbuga.followtvmovie.domain.favorite.usecase

import com.ugurbuga.followtvmovie.core.base.UseCase
import com.ugurbuga.followtvmovie.core.common.ApiState
import com.ugurbuga.followtvmovie.data.repository.favorites.FavoritesRepository
import com.ugurbuga.followtvmovie.domain.moviedetail.model.detail.MovieDetailUIModel
import com.ugurbuga.followtvmovie.domain.poster.mapper.PosterMapper
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class AddFavoriteMovieUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository, private val posterMapper: PosterMapper
) : UseCase<AddFavoriteMovieUseCase.AddFavoriteParams, Unit>() {

    data class AddFavoriteParams(
        val mediaType: String,
        val movieDetail: MovieDetailUIModel,
        val isWatched: Boolean
    )

    override fun execute(params: AddFavoriteParams): Flow<ApiState<Unit>> {
        return favoritesRepository.insert(
            posterMapper.toPosterUIModel(
                params.movieDetail,
                params.isWatched,
                params.mediaType
            )
        )
    }
}