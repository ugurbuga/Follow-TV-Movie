package com.ugurbuga.followtvmovie.di

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
    abstract fun provideMainRepository(
        mainRepositoryImpl: TvShowRepositoryImpl
    ): TvShowRepository
}
