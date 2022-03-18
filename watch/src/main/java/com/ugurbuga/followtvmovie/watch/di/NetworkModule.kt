package com.ugurbuga.followtvmovie.watch.di

import androidx.viewbinding.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.ugurbuga.followtvmovie.watch.data.api.ApiConstants
import com.ugurbuga.followtvmovie.watch.data.api.interceptor.ApplicationInterceptor
import com.ugurbuga.followtvmovie.watch.data.api.interceptor.LanguageInterceptor
import com.ugurbuga.followtvmovie.watch.data.api.services.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        applicationInterceptor: ApplicationInterceptor,
        languageInterceptor: LanguageInterceptor,
    ): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder().apply {
            connectTimeout(ApiConstants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(ApiConstants.READ_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(ApiConstants.WRITE_TIMEOUT, TimeUnit.SECONDS)
            if (BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                addNetworkInterceptor(interceptor)
            }
            addInterceptor(languageInterceptor)
            addInterceptor(applicationInterceptor)
        }
        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun providerRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieService(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }

}
