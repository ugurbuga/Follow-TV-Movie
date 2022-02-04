package com.ugurbuga.followtvmovie.base.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ugurbuga.followtvmovie.domain.poster.model.PosterItemUIModel

@Database(entities = arrayOf(PosterItemUIModel::class), version = 1, exportSchema = false)
abstract class FTMRoomDatabase : RoomDatabase() {

    abstract fun getFavoritesDao(): FavoritesDao
}