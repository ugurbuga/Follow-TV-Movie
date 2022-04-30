package com.ugurbuga.followtvmovie.di

import com.ugurbuga.followtvmovie.di.preferences.FTMPreferenceManager
import com.ugurbuga.followtvmovie.di.preferences.Preferences
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferenceModule {

    @Binds
    abstract fun providePreferences(preferences: FTMPreferenceManager): Preferences
}
