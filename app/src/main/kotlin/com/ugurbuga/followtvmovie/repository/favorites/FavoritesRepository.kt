package com.ugurbuga.followtvmovie.repository.favorites

import com.ugurbuga.followtvmovie.common.Resource
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    fun insert(note: PosterItemUIModel): Flow<Resource<Unit>>

    fun delete(id: Int): Flow<Resource<Unit>>

    fun getFavorites(listType: String): Flow<Resource<MutableList<PosterItemUIModel>>>

    fun getFavorite(listType: String, id: Int): Flow<Resource<PosterItemUIModel?>>

    suspend fun update(note: PosterItemUIModel)
}
