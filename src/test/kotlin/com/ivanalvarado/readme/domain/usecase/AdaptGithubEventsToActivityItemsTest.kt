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
            repoName = "ivanalvarado/ivanalvarado",
            repoUrl = "https://github.com/ivanalvarado/ivanalvarado",
            createdAt = "2024-01-28T20:27:53Z"
        ),
        GithubEvent.UnsupportedEvent,
        GithubEvent.DeleteEvent(
            refType = "branch",
            ref = "experimental-branch",
            repoName = "ivanalvarado/ivanalvarado",
            repoUrl = "https://github.com/ivanalvarado/ivanalvarado",
            createdAt = "2024-01-28T20:27:53Z"
        ),
        GithubEvent.UnsupportedEvent,
        GithubEvent.IssueCommentEvent(
            issueNumber = 42,
            issueCommentUrl = "url/to/comment",
            repoName = "ivanalvarado/ivanalvarado",
            repoUrl = "https://github.com/ivanalvarado/ivanalvarado",
            createdAt = "2024-01-28T20:27:53Z"
        ),
        GithubEvent.UnsupportedEvent,
        GithubEvent.IssuesEvent(
            action = "opened",
            issueNumber = 42,
            title = "Something is broken",
            issueUrl = "url/to/issue",
            repoName = "ivanalvarado/ivanalvarado",
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
            repoName = "ivanalvarado/ivanalvarado",
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
            repoName = "ivanalvarado/ivanalvarado",
            repoUrl = "https://github.com/ivanalvarado/ivanalvarado",
            createdAt = "2024-01-28T20:27:53Z"
        ),
        GithubEvent.UnsupportedEvent,
        GithubEvent.WatchEvent(
            action = "started",
            repoName = "ivanalvarado/ivanalvarado",
            repoUrl = "https://github.com/ivanalvarado/ivanalvarado",
            createdAt = "2024-01-28T20:27:53Z"
        ),
        GithubEvent.UnsupportedEvent,
    )

    private val expectedActivityItems = listOf(
        ActivityItem(
            message = "⚡️ created branch `main` on [ivanalvarado/ivanalvarado](https://github.com/ivanalvarado/ivanalvarado)",
            date = "2024-01-28"
        ),
        ActivityItem(
            message = "✏️ deleted branch `experimental-branch` on [ivanalvarado/ivanalvarado](https://github.com/ivanalvarado/ivanalvarado)",
            date = "2024-01-28"
        ),
        ActivityItem(
            message = "💬 commented on [#42](url/to/comment) in [ivanalvarado/ivanalvarado](https://github.com/ivanalvarado/ivanalvarado)",
            date = "2024-01-28"
        ),
        ActivityItem(
            message = "📝 opened issue [#42](url/to/issue) on [ivanalvarado/ivanalvarado](https://github.com/ivanalvarado/ivanalvarado): \"Something is broken\"",
            date = "2024-01-28"
        ),
        ActivityItem(
            message = "🧑🏻‍💻 merged PR [#8](url/to/pull/request) to [ivanalvarado/ivanalvarado](https://github.com/ivanalvarado/ivanalvarado): \"Add support for configuration cache\"",
            date = "2024-01-28"
        ),
        ActivityItem(
            message = "🧑🏻‍💻 opened PR [#8](url/to/pull/request) to [ivanalvarado/ivanalvarado](https://github.com/ivanalvarado/ivanalvarado): \"Add support for configuration cache\"",
            date = "2024-01-28"
        ),
        ActivityItem(
            message = "👀 started watching [ivanalvarado/ivanalvarado](https://github.com/ivanalvarado/ivanalvarado)",
            date = "2024-01-28"
        )
    )
}
