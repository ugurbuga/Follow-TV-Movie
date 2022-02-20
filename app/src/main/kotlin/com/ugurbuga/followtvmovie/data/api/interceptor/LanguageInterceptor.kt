package com.ugurbuga.followtvmovie.data.api.interceptor

import com.ugurbuga.followtvmovie.data.api.ApiConstants
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject


class LanguageInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val originalHttpUrl: HttpUrl = original.url

        val originalUrlString = originalHttpUrl.toUrl().toString()
        if (originalUrlString.startsWith(ApiConstants.BASE_URL + "movie/") &&
            originalUrlString.endsWith("images")
        ) {
            return chain.proceed(original)
        }

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(ApiConstants.LANGUAGE, "en-US")
            .build()

        val requestBuilder: Request.Builder = original.newBuilder().url(url)

        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }
}
