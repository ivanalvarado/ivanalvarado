package com.ivanalvarado.readme.client

import com.ivanalvarado.readme.domain.model.EventsResult

interface GithubClient {
    suspend fun getUserEvents(login: String): EventsResult
}
