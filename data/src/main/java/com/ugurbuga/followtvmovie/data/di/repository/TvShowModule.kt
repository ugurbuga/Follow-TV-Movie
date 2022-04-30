package com.ugurbuga.followtvmovie.data.di.repository

import com.ugurbuga.followtvmovie.data.repository.tvshow.TvShowRepository
import com.ugurbuga.followtvmovie.data.repository.tvshow.TvShowRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class TvShowModule {


    @Binds
    abstract fun provideTvShowRepository(apiDataRepositoryImpl: TvShowRepositoryImpl): TvShowRepository




}