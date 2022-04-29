package com.ugurbuga.followtvmovie.data.di.repository

import com.ugurbuga.followtvmovie.data.api.services.SearchService
import com.ugurbuga.followtvmovie.data.repository.search.SearchRepository
import com.ugurbuga.followtvmovie.data.repository.search.SearchRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object SearchModule {

    @Provides
    internal fun provideSearchService(retrofit: Retrofit): SearchService {
        return retrofit.create(SearchService::class.java)
    }

    @Provides
    internal fun provideSearchRepository(service: SearchService): SearchRepository {
        return SearchRepositoryImpl(service)
    }

}