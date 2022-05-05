package com.ugurbuga.followtvmovie.data.api.interceptor

import com.ugurbuga.followtvmovie.data.BuildConfig
import com.ugurbuga.followtvmovie.data.api.ApiConstants
import javax.inject.Inject
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class ApplicationInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val originalHttpUrl: HttpUrl = original.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(ApiConstants.API_KEY, BuildConfig.API_KEY)
            .build()

        val requestBuilder: Request.Builder = original.newBuilder().url(url)

        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }
}
