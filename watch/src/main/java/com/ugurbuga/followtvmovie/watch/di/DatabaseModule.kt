package com.ugurbuga.followtvmovie.watch.di

import android.content.Context
import androidx.room.Room
import com.ugurbuga.followtvmovie.watch.dao.FTMRoomDatabase
import com.ugurbuga.followtvmovie.watch.dao.FavoritesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideFavoritesDao(appDatabase: FTMRoomDatabase): FavoritesDao {
        return appDatabase.getFavoritesDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): FTMRoomDatabase {
        return Room.databaseBuilder(
            context,
            FTMRoomDatabase::class.java,
            "ftm_database"
        ).fallbackToDestructiveMigration().build()
    }
}