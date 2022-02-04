package com.ugurbuga.followtvmovie.repository.favorites

import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    suspend fun insert(note: PosterItemUIModel)

    suspend fun delete(note: PosterItemUIModel)

    fun getFavorites(listType: String): Flow<MutableList<PosterItemUIModel>>

    suspend fun update(note: PosterItemUIModel)
}
