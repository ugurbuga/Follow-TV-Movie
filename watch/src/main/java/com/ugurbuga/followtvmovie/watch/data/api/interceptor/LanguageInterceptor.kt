package com.ugurbuga.followtvmovie.watch.data.api.interceptor

import com.ugurbuga.followtvmovie.watch.data.api.ApiConstants
import javax.inject.Inject
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class LanguageInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val originalHttpUrl: HttpUrl = original.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(ApiConstants.LANGUAGE, "en-US")
            .build()

        val requestBuilder: Request.Builder = original.newBuilder().url(url)

        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }
}
