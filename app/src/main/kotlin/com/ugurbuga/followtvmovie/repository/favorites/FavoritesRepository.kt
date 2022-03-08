package com.ugurbuga.followtvmovie.repository.favorites

import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    fun insert(note: PosterItemUIModel): Flow<Resource<Unit>>

    fun delete(id: String): Flow<Resource<Unit>>

    fun getFavorites(mediaType: String, isWatched: Boolean): Flow<Resource<MutableList<PosterItemUIModel>>>

    fun getFavorite(mediaType: String, id: String): Flow<Resource<PosterItemUIModel?>>

    suspend fun update(note: PosterItemUIModel)
}
