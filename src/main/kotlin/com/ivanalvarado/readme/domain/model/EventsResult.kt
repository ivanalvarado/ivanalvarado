package com.ivanalvarado.readme.domain.model

sealed class EventsResult {
    data class Success(val events: List<GithubEvent>) : EventsResult()
    data class Error(val message: String): EventsResult()
}
