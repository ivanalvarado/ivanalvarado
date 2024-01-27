package com.ivanalvarado.readme.network

import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("/users/{login}/events/public")
    suspend fun userEvents(@Path("login") login: String)
}
