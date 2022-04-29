package com.ugurbuga.followtvmovie.data.di.repository

import com.ugurbuga.followtvmovie.data.api.services.MovieService
import com.ugurbuga.followtvmovie.data.repository.movie.MovieRepository
import com.ugurbuga.followtvmovie.data.repository.movie.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object MovieModule {

    @Provides
     fun provideMovieService(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }

    @Provides
     fun provideMovieRepository(service: MovieService): MovieRepository {
        return MovieRepositoryImpl(service)
    }

}