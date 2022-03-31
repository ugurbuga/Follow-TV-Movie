package com.ugurbuga.followtvmovie.watch.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ugurbuga.followtvmovie.watch.ui.detail.MediaType
import com.ugurbuga.followtvmovie.watch.ui.detail.model.PosterItemUIModel
import java.util.Calendar
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: PosterItemUIModel)

    @Query("Delete from favoritesTable WHERE id = :id")
    suspend fun delete(id: String)

    @Query("Select * from favoritesTable where mediaType=:mediaType AND isWatched=:isWatched order by releaseDateLong DESC")
    fun getFavorites(mediaType: String, isWatched: Boolean): Flow<MutableList<PosterItemUIModel>>

    @Query("Select * from favoritesTable where mediaType=:mediaType AND id=:id")
    fun getFavorite(mediaType: String, id: String): Flow<PosterItemUIModel?>

    @Update
    suspend fun update(note: PosterItemUIModel)

    @Query("Select * from favoritesTable where mediaType=:mediaType AND isWatched=:isWatched AND releaseDateLong > :date order by releaseDateLong DESC")
    fun getFutureMovies(
        mediaType: String = MediaType.MOVIE,
        isWatched: Boolean = false,
        date: Long = Calendar.getInstance().time.time
    ): MutableList<PosterItemUIModel>

    @Query("Select * from favoritesTable where mediaType=:mediaType AND isWatched=:isWatched AND releaseDateLong > :date order by releaseDateLong ASC")
    fun getSoonMovies(
        mediaType: String = MediaType.MOVIE,
        isWatched: Boolean = false,
        date: Long = Calendar.getInstance().time.time
    ): Flow<MutableList<PosterItemUIModel>>

}