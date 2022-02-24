package com.ugurbuga.followtvmovie.di

import com.ugurbuga.followtvmovie.repository.credit.CreditRepository
import com.ugurbuga.followtvmovie.repository.credit.CreditRepositoryImpl
import com.ugurbuga.followtvmovie.repository.favorites.FavoritesRepository
import com.ugurbuga.followtvmovie.repository.favorites.FavoritesRepositoryImpl
import com.ugurbuga.followtvmovie.repository.movie.MovieRepository
import com.ugurbuga.followtvmovie.repository.movie.MovieRepositoryImpl
import com.ugurbuga.followtvmovie.repository.tvshow.TvShowRepository
import com.ugurbuga.followtvmovie.repository.tvshow.TvShowRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideTvShowRepository(
        tvShowRepositoryImpl: TvShowRepositoryImpl
    ): TvShowRepository

    @Binds
    abstract fun provideMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository

    @Binds
    abstract fun provideCreditRepository(
        creditRepositoryImpl: CreditRepositoryImpl
    ): CreditRepository

    @Binds
    abstract fun provideFavoritesRepository(
        favoriteRepositoryImpl: FavoritesRepositoryImpl
    ): FavoritesRepository
}
