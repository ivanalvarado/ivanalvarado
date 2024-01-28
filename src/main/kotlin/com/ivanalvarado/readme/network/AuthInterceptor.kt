package com.ivanalvarado.readme.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor constructor(private val authToken: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("Authorization", "Bearer $authToken")
        return chain.proceed(requestBuilder.build())
    }
}
