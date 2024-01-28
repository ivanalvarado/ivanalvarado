package com.ivanalvarado.readme.client

import com.ivanalvarado.readme.domain.model.EventsResult
import com.ivanalvarado.readme.network.GithubService

class GithubClientImpl(
    private val service: GithubService,
    private val adaptToDomain: AdaptToDomain
): GithubClient {
    override suspend fun getUserEvents(login: String): EventsResult {
        val response = service.userEvents(login)
        return if (response.isSuccessful) {
            response.body()?.let {
                EventsResult.Success(
                    events = adaptToDomain(it)
                )
            } ?: EventsResult.Error(message = "Empty response.")
        } else {
            EventsResult.Error(message = response.message())
        }
    }
}
