package com.ivanalvarado.readme.domain.usecase

import com.ivanalvarado.readme.domain.model.ActivityItem
import com.ivanalvarado.readme.domain.model.GithubEvent
import org.junit.Test
import kotlin.test.assertEquals

class AdaptGithubEventsToActivityItemsTest {

    @Test
    fun `invoke - given a list of github events, should return a list adapted to activiy items`() {
        // Given
        val adaptGithubEventsToActivityItems = AdaptGithubEventsToActivityItemsImpl()

        // When
        val result = adaptGithubEventsToActivityItems(githubEvents)

        // Then
        assertEquals(expectedActivityItems, result)
    }

    private val githubEvents = listOf(
        GithubEvent.UnsupportedEvent,
        GithubEvent.CreateEvent(
            refType = "branch",
            ref = "main",
            repoUrl = "https://github.com/ivanalvarado/ivanalvarado",
            createdAt = "2024-01-28T20:27:53Z"
        ),
        GithubEvent.UnsupportedEvent,
        GithubEvent.DeleteEvent(
            refType = "branch",
            ref = "experimental-branch",
            repoUrl = "https://github.com/ivanalvarado/ivanalvarado",
            createdAt = "2024-01-28T20:27:53Z"
        ),
        GithubEvent.UnsupportedEvent,
        GithubEvent.IssueCommentEvent(
            issueNumber = 42,
            issueCommentUrl = "url/to/comment",
            repoUrl = "https://github.com/ivanalvarado/ivanalvarado",
            createdAt = "2024-01-28T20:27:53Z"
        ),
        GithubEvent.UnsupportedEvent,
        GithubEvent.IssuesEvent(
            action = "opened",
            issueNumber = 42,
            title = "Something is broken",
            issueUrl = "url/to/issue",
            repoUrl = "https://github.com/ivanalvarado/ivanalvarado",
            createdAt = "2024-01-28T20:27:53Z"
        ),
        GithubEvent.UnsupportedEvent,
        GithubEvent.PullRequestEvent(
            action = "",
            title = "Add support for configuration cache",
            merged = true,
            number = 8,
            prUrl = "url/to/pull/request",
            repoUrl = "https://github.com/ivanalvarado/ivanalvarado",
            createdAt = "2024-01-28T20:27:53Z"
        ),
        GithubEvent.UnsupportedEvent,
        GithubEvent.PullRequestEvent(
            action = "opened",
            title = "Add support for configuration cache",
            merged = false,
            number = 8,
            prUrl = "url/to/pull/request",
            repoUrl = "https://github.com/ivanalvarado/ivanalvarado",
            createdAt = "2024-01-28T20:27:53Z"
        ),
        GithubEvent.UnsupportedEvent,
        GithubEvent.UnsupportedEvent,
    )

    private val expectedActivityItems = listOf(
        ActivityItem(
            message = "⚡️ created branch `main` on https://github.com/ivanalvarado/ivanalvarado",
            date = "2024-01-28"
        ),
        ActivityItem(
            message = "✏️ deleted branch `experimental-branch` on https://github.com/ivanalvarado/ivanalvarado",
            date = "2024-01-28"
        ),
        ActivityItem(
            message = "💬 commented on [#42](url/to/comment) in https://github.com/ivanalvarado/ivanalvarado",
            date = "2024-01-28"
        ),
        ActivityItem(
            message = "📝 opened issue [#42](url/to/issue) on https://github.com/ivanalvarado/ivanalvarado: \"Something is broken\"",
            date = "2024-01-28"
        ),
        ActivityItem(
            message = "🧑🏻‍💻 merged PR [#8](url/to/pull/request) to https://github.com/ivanalvarado/ivanalvarado: \"Add support for configuration cache\"",
            date = "2024-01-28"
        ),
        ActivityItem(
            message = "🧑🏻‍💻 opened PR [#8](url/to/pull/request) to https://github.com/ivanalvarado/ivanalvarado: \"Add support for configuration cache\"",
            date = "2024-01-28"
        )
    )
}
