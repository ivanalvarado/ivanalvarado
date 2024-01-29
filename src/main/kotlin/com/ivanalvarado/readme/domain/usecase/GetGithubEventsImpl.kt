package com.ivanalvarado.readme.domain.usecase

import com.ivanalvarado.readme.client.GithubClient
import com.ivanalvarado.readme.domain.model.EventsResult
import com.ivanalvarado.readme.domain.model.GithubEvent

class GetGithubEventsImpl(
    private val client: GithubClient
) : GetGithubEvents {
    override suspend fun invoke(login: String): List<GithubEvent> {
        return when(val eventsResult = client.getUserEvents(login)) {
            is EventsResult.Success -> eventsResult.events
            is EventsResult.Error -> emptyList()
        }
    }
}
