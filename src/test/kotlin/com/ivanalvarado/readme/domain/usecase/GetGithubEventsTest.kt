package com.ivanalvarado.readme.domain.usecase

import com.ivanalvarado.readme.client.GithubClient
import com.ivanalvarado.readme.domain.model.EventsResult
import com.ivanalvarado.readme.domain.model.GithubEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class GetGithubEventsTest {

    @Test
    fun `invoke - given successful fetch of events, should return list of events`() = runBlockingTest {
        // Given
        val config = FakeGithubClient.Config.Success(githubEvents)
        val client = FakeGithubClient(config)
        val getGithubEvents = GetGithubEventsImpl(client)

        // When
        val result = getGithubEvents("ivanalvarado")

        // Then
        assertEquals(githubEvents, result)
    }

    @Test
    fun `invoke - given error fetching github events, should return empty list`() = runBlockingTest {
        // Given
        val config = FakeGithubClient.Config.Error
        val client = FakeGithubClient(config)
        val getGithubEvents = GetGithubEventsImpl(client)

        // When
        val result = getGithubEvents("ivanalvarado")

        // Then
        assertEquals(emptyList(), result)
    }

    private val githubEvents = listOf(
        GithubEvent.CreateEvent(
            refType = "branch",
            ref = "main",
            repoName = "ivanalvarado/ivanalvarado",
            repoUrl = "https://github.com/ivanalvarado/ivanalvarado",
            createdAt = "2024-01-28T20:27:53Z"
        ),
        GithubEvent.DeleteEvent(
            refType = "branch",
            ref = "experimental-branch",
            repoName = "ivanalvarado/ivanalvarado",
            repoUrl = "https://github.com/ivanalvarado/ivanalvarado",
            createdAt = "2024-01-28T20:27:53Z"
        ),
        GithubEvent.IssueCommentEvent(
            issueNumber = 42,
            issueCommentUrl = "url/to/comment",
            repoName = "ivanalvarado/ivanalvarado",
            repoUrl = "https://github.com/ivanalvarado/ivanalvarado",
            createdAt = "2024-01-28T20:27:53Z"
        ),
        GithubEvent.IssuesEvent(
            action = "opened",
            issueNumber = 42,
            title = "Something is broken",
            issueUrl = "url/to/issue",
            repoName = "ivanalvarado/ivanalvarado",
            repoUrl = "https://github.com/ivanalvarado/ivanalvarado",
            createdAt = "2024-01-28T20:27:53Z"
        ),
        GithubEvent.PullRequestEvent(
            action = "",
            title = "Add support for configuration cache",
            merged = true,
            number = 8,
            prUrl = "url/to/pull/request",
            repoName = "ivanalvarado/ivanalvarado",
            repoUrl = "https://github.com/ivanalvarado/ivanalvarado",
            createdAt = "2024-01-28T20:27:53Z"
        ),
        GithubEvent.PullRequestEvent(
            action = "opened",
            title = "Add support for configuration cache",
            merged = false,
            number = 8,
            prUrl = "url/to/pull/request",
            repoName = "ivanalvarado/ivanalvarado",
            repoUrl = "https://github.com/ivanalvarado/ivanalvarado",
            createdAt = "2024-01-28T20:27:53Z"
        ),
        GithubEvent.UnsupportedEvent
    )

    private class FakeGithubClient(private val config: Config): GithubClient {

        sealed class Config {
            data class Success(val githubEvents: List<GithubEvent>): Config()
            data object Error : Config()
        }

        override suspend fun getUserEvents(login: String): EventsResult {
            return when(config) {
                is Config.Success -> EventsResult.Success(config.githubEvents)
                Config.Error -> EventsResult.Error(message = "Failed to fetch events.")
            }
        }
    }
}
