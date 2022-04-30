package com.ugurbuga.followtvmovie.data.di.repository

import com.ugurbuga.followtvmovie.data.repository.search.SearchRepository
import com.ugurbuga.followtvmovie.data.repository.search.SearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SearchModule {

    @Binds
    abstract fun provideSearchRepository(apiDataRepositoryImpl: SearchRepositoryImpl): SearchRepository


}