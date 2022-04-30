package com.ugurbuga.followtvmovie.data.di.repository

import com.ugurbuga.followtvmovie.data.repository.movie.MovieRepository
import com.ugurbuga.followtvmovie.data.repository.movie.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class MovieModule {


    @Binds
    abstract fun provideMovieRepository(apiDataRepositoryImpl: MovieRepositoryImpl): MovieRepository


}