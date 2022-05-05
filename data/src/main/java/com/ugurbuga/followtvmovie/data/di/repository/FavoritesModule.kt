package com.ugurbuga.followtvmovie.data.di.repository

import com.ugurbuga.followtvmovie.data.repository.favorites.FavoritesRepository
import com.ugurbuga.followtvmovie.data.repository.favorites.FavoritesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FavoritesModule {

    @Binds
    @Singleton
    abstract fun provideFavoriteRepository(apiDataRepositoryImpl: FavoritesRepositoryImpl): FavoritesRepository
}