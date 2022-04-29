package com.ugurbuga.followtvmovie.data.di.repository

import com.ugurbuga.followtvmovie.data.dao.FavoritesDao
import com.ugurbuga.followtvmovie.data.repository.favorites.FavoritesRepository
import com.ugurbuga.followtvmovie.data.repository.favorites.FavoritesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object FavoritesModule {


    @Provides
    internal fun provideFavoriteRepository(favoritesDao: FavoritesDao): FavoritesRepository {
        return FavoritesRepositoryImpl(favoritesDao)
    }
}