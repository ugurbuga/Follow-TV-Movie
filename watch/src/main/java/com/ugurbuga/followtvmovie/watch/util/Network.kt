package com.ugurbuga.followtvmovie.watch.util

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Network {

    private fun provideOkHttpClient(
    ): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder().apply {
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        }
        return okHttpClient.build()
    }

    private fun provideMoshi(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().client(provideOkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create(provideMoshi())).baseUrl(BASE_URL)
            .build()
    }

    private const val CONNECT_TIMEOUT = 20L
    private const val READ_TIMEOUT = 120L
    private const val WRITE_TIMEOUT = 120L
    private const val BASE_URL = "https://api.themoviedb.org/3/"
}