package com.ugurbuga.followtvmovie.data.di.repository

import com.ugurbuga.followtvmovie.data.repository.common.CommonRepository
import com.ugurbuga.followtvmovie.data.repository.common.CommonRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class CommonModule {


    @Binds
    abstract fun bindApiRepository(apiDataRepositoryImpl: CommonRepositoryImpl): CommonRepository

}