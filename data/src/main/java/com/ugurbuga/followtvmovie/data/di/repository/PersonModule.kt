package com.ugurbuga.followtvmovie.data.di.repository

import com.ugurbuga.followtvmovie.data.api.services.PersonService
import com.ugurbuga.followtvmovie.data.repository.person.PersonRepository
import com.ugurbuga.followtvmovie.data.repository.person.PersonRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object PersonModule {

    @Provides
    internal fun providePersonService(retrofit: Retrofit): PersonService {
        return retrofit.create(PersonService::class.java)
    }

    @Provides
    internal fun providePersonRepository(service: PersonService): PersonRepository {
        return PersonRepositoryImpl(service)
    }

}