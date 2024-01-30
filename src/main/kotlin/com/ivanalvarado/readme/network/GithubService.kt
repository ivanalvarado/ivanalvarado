package com.ivanalvarado.readme.network

import com.ivanalvarado.readme.network.model.EventsJson
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {
    @GET("/users/{login}/events/public")
    suspend fun userEvents(
        @Path("login") login: String,
        @Query("per_page") perPage: Int = 100
    ): Response<List<EventsJson>>
}
