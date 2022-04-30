package com.ugurbuga.followtvmovie.data.di.repository

import com.ugurbuga.followtvmovie.data.api.services.CommonService
import com.ugurbuga.followtvmovie.data.repository.common.CommonRepository
import com.ugurbuga.followtvmovie.data.repository.common.CommonRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object CommonModule {

    @Provides
    internal fun provideCommonService(retrofit: Retrofit): CommonService {
        return retrofit.create(CommonService::class.java)
    }


    @Provides
    internal fun provideCommonRepository(service: CommonService): CommonRepository {
        return CommonRepositoryImpl(service)
    }

}