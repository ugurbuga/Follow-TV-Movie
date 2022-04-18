package com.ugurbuga.followtvmovie.repository.favorites

import com.ugurbuga.followtvmovie.common.ApiState
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    fun insert(note: PosterItemUIModel): Flow<ApiState<Unit>>

    fun delete(id: String): Flow<ApiState<Unit>>

    fun getFavorites(
        mediaType: String,
        isWatched: Boolean
    ): Flow<ApiState<MutableList<PosterItemUIModel>>>

    fun getFavorite(mediaType: String, id: String): Flow<ApiState<PosterItemUIModel?>>

    fun getSoonMovies(): Flow<ApiState<MutableList<PosterItemUIModel>>>

    suspend fun update(note: PosterItemUIModel)
}
