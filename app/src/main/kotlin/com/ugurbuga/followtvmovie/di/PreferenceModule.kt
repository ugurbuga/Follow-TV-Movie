package com.ugurbuga.followtvmovie.di

import com.ugurbuga.followtvmovie.data.preferences.FTMPreferenceManager
import com.ugurbuga.followtvmovie.data.preferences.Preferences
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
