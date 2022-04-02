package com.ugurbuga.followtvmovie.watch.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ugurbuga.followtvmovie.watch.ui.detail.PosterItemUIModel

@Database(entities = [PosterItemUIModel::class], version = 2, exportSchema = false)
abstract class FTMRoomDatabase : RoomDatabase() {

    abstract fun getFavoritesDao(): FavoritesDao
}