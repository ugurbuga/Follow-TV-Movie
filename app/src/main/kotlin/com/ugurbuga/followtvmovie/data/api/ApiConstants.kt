package com.ugurbuga.followtvmovie.data.api

import com.ugurbuga.followtvmovie.BuildConfig


object ApiConstants {
    const val CONNECT_TIMEOUT = 20L
    const val READ_TIMEOUT = 120L
    const val WRITE_TIMEOUT = 120L
    const val API_KEY = "api_key"
    const val LANGUAGE = "language"

    const val BASE_URL = BuildConfig.API_URL
}
