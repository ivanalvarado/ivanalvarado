package com.ivanalvarado.readme.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitHelper {

    fun getInstance(authToken: String, isDebug: Boolean = false): GithubService {
        val authInterceptor = AuthInterceptor(authToken)

        val loggingInterceptor = HttpLoggingInterceptor().setLevel(
            if (isDebug) Level.BODY else Level.NONE
        )

        val client = OkHttpClient().newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()

        val moshiConverterFactory = MoshiConverterFactory.create(
            Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        )

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(client)
            .addConverterFactory(moshiConverterFactory)
            .build()

        return retrofit.create(GithubService::class.java)
    }
}
