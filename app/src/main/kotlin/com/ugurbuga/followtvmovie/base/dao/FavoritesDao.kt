package com.ugurbuga.followtvmovie.base.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: PosterItemUIModel)

    @Query("Delete from favoritesTable WHERE id = :id")
    suspend fun delete(id: String)

    @Query("Select * from favoritesTable where mediaType=:mediaType AND isWatched=:isWatched order by id")
    fun getFavorites(mediaType: String, isWatched: Boolean): Flow<MutableList<PosterItemUIModel>>

    @Query("Select * from favoritesTable where mediaType=:mediaType AND id=:id")
    fun getFavorite(mediaType: String, id: String): Flow<PosterItemUIModel?>

    @Update
    suspend fun update(note: PosterItemUIModel)

}