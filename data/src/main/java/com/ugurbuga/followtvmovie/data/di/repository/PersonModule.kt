package com.ugurbuga.followtvmovie.data.di.repository

import com.ugurbuga.followtvmovie.data.repository.person.PersonRepository
import com.ugurbuga.followtvmovie.data.repository.person.PersonRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class PersonModule {


    @Binds
    abstract fun providePersonRepository(apiDataRepositoryImpl: PersonRepositoryImpl): PersonRepository



}