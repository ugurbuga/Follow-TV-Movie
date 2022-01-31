package com.ugurbuga.followtvmovie.di

import com.ugurbuga.followtvmovie.repository.main.MainRepository
import com.ugurbuga.followtvmovie.repository.main.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideMainRepository(
        mainRepositoryImpl: MainRepositoryImpl
    ): MainRepository
}
