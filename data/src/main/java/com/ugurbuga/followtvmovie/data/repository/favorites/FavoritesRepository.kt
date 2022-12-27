package com.ugurbuga.followtvmovie.data.repository.favorites

import com.ugurbuga.followtvmovie.core.common.ApiState
import com.ugurbuga.followtvmovie.data.model.PosterItemModel
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    fun insert(note: PosterItemModel): Flow<ApiState<Unit>>

    fun delete(id: String): Flow<ApiState<Unit>>

    fun getFavorites(
        mediaType: String,
        isWatched: Boolean
    ): Flow<ApiState<MutableList<PosterItemModel>>>

    suspend fun getTileFavorites(
        mediaType: String,
        isWatched: Boolean
    ): MutableList<PosterItemModel>

    fun getFavorite(mediaType: String, id: String): Flow<ApiState<PosterItemModel?>>

    fun getSoonMovies(mediaType: String): Flow<ApiState<MutableList<PosterItemModel>>>

    suspend fun update(note: PosterItemModel)
}
