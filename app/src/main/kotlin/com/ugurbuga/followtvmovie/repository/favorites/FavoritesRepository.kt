package com.ugurbuga.followtvmovie.repository.favorites

import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    fun insert(note: PosterItemUIModel): Flow<Resource<Unit>>

    suspend fun delete(note: PosterItemUIModel)

    fun getFavorites(listType: String): Flow<Resource<MutableList<PosterItemUIModel>>>

    suspend fun update(note: PosterItemUIModel)
}
