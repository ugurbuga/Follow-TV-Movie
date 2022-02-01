package com.ugurbuga.followtvmovie.di

import com.ugurbuga.followtvmovie.domain.populartvshow.PopularTvShowUseCase
import com.ugurbuga.followtvmovie.domain.populartvshow.PopularTvShowUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class TvShowUseCaseModule {

    @Binds
    abstract fun provideTvShowUseCase(
        orderUseCaseImpl: PopularTvShowUseCaseImpl
    ): PopularTvShowUseCase
}
