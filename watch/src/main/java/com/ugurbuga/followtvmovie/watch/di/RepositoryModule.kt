package com.ugurbuga.followtvmovie.watch.di

import com.ugurbuga.followtvmovie.watch.repository.favorites.FavoritesRepository
import com.ugurbuga.followtvmovie.watch.repository.favorites.FavoritesRepositoryImpl
import com.ugurbuga.followtvmovie.watch.repository.movie.MovieRepository
import com.ugurbuga.followtvmovie.watch.repository.movie.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository


    @Binds
    abstract fun provideFavoritesRepository(
        favoriteRepositoryImpl: FavoritesRepositoryImpl
    ): FavoritesRepository

}
