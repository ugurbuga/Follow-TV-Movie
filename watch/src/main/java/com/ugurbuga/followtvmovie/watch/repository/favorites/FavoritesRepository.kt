package com.ugurbuga.followtvmovie.watch.repository.favorites

import com.ugurbuga.followtvmovie.watch.detail.model.PosterItemUIModel
import com.ugurbuga.followtvmovie.watch.util.Resource
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    fun insert(note: PosterItemUIModel): Flow<Resource<Unit>>

    fun delete(id: String): Flow<Resource<Unit>>

    fun getFavorites(
        mediaType: String,
        isWatched: Boolean
    ): Flow<Resource<MutableList<PosterItemUIModel>>>

    fun getFavorite(mediaType: String, id: String): Flow<Resource<PosterItemUIModel?>>

    fun getSoonMovies(): Flow<Resource<MutableList<PosterItemUIModel>>>

    suspend fun update(note: PosterItemUIModel)
}
