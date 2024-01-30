package com.ivanalvarado.readme.client

import com.ivanalvarado.readme.domain.model.GithubEvent
import com.ivanalvarado.readme.network.model.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import kotlin.test.assertEquals

@RunWith(Parameterized::class)
class AdaptToDomainTest(
    private val eventsJson: List<EventsJson>,
    private val githubEvents: List<GithubEvent>
) {

    private val adaptToDomain = AdaptToDomain()

    @Test
    fun `invoke - given events json, should adapt to domain model`() {
        // When
        val result = adaptToDomain(eventsJson)

        // Then
        assertEquals(githubEvents, result)
    }

    companion object {
        @Parameterized.Parameters(
            name = "{index}: " +
                    "eventsJson = {0}, " +
                    "eventsDomain = {1}, "
        )
        @JvmStatic
        fun data(): Iterable<Array<Any?>> {
            return listOf(
                params(
                    eventsJson = listOf(
                        EventsJson()
                    ),
                    githubEvents = listOf(
                        GithubEvent.UnsupportedEvent
                    )
                ),
                params(
                    eventsJson = listOf(
                        EventsJson(type = "CreateEvent"),
                        EventsJson(type = "DeleteEvent"),
                        EventsJson(type = "IssuesEvent"),
                        EventsJson(type = "IssueCommentEvent"),
                        EventsJson(type = "PullRequestEvent"),
                        EventsJson(type = "WatchEvent"),
                        EventsJson(type = "PushEvent")
                    ),
                    githubEvents = listOf(
                        GithubEvent.CreateEvent(
                            refType = "",
                            ref = "",
                            repoName = "",
                            repoUrl = "",
                            createdAt = ""
                        ),
                        GithubEvent.DeleteEvent(
                            refType = "",
                            ref = "",
                            repoName = "",
                            repoUrl = "",
                            createdAt = ""
                        ),
                        GithubEvent.IssuesEvent(
                            action = "",
                            issueNumber = 0,
                            title = "",
                            issueUrl = "",
                            repoName = "",
                            repoUrl = "",
                            createdAt = ""
                        ),
                        GithubEvent.IssueCommentEvent(
                            issueNumber = 0,
                            issueCommentUrl = "",
                            repoName = "",
                            repoUrl = "",
                            createdAt = ""
                        ),
                        GithubEvent.PullRequestEvent(
                            action = "",
                            title = "",
                            merged = false,
                            number = 0,
                            prUrl = "",
                            repoName = "",
                            repoUrl = "",
                            createdAt = ""
                        ),
                        GithubEvent.WatchEvent(
                            action = "",
                            repoName = "",
                            repoUrl = "",
                            createdAt = ""
                        ),
                        GithubEvent.UnsupportedEvent
                    )
                ),
                params(
                    eventsJson = listOf(
                        EventsJson(
                            type = "CreateEvent",
                            repo = RepoJson(
                                name = "ivanalvarado/ivanalvarado",
                                url = "https://api.github.com/repos/ivanalvarado/ivanalvarado"
                            ),
                            payload = PayloadJson(
                                ref_type = "branch",
                                ref = "main"
                            ),
                            created_at = "2024-01-28T20:27:53Z"
                        ),
                        EventsJson(
                            type = "DeleteEvent",
                            payload = PayloadJson(
                                ref_type = "branch",
                                ref = "experimental-branch"
                            ),
                            repo = RepoJson(
                                name = "ivanalvarado/ivanalvarado",
                                url = "https://api.github.com/repos/ivanalvarado/ivanalvarado"
                            ),
                            created_at = "2024-01-28T20:27:53Z"
                        ),
                        EventsJson(
                            type = "IssuesEvent",
                            payload = PayloadJson(
                                action = "opened",
                                issue = IssueJson(
                                    number = 42,
                                    title = "Something is broken",
                                    html_url = "url/to/issue"
                                )
                            ),
                            repo = RepoJson(
                                name = "ivanalvarado/ivanalvarado",
                                url = "https://api.github.com/repos/ivanalvarado/ivanalvarado"
                            ),
                            created_at = "2024-01-28T20:27:53Z"
                        ),
                        EventsJson(
                            type = "IssueCommentEvent",
                            payload = PayloadJson(
                                issue = IssueJson(
                                    number = 42,
                                    comments_url = "url/to/comment"
                                )
                            ),
                            repo = RepoJson(
                                name = "ivanalvarado/ivanalvarado",
                                url = "https://api.github.com/repos/ivanalvarado/ivanalvarado"
                            ),
                            created_at = "2024-01-28T20:27:53Z"
                        ),
                        EventsJson(
                            type = "PullRequestEvent",
                            payload = PayloadJson(
                                action = "opened",
                                pull_request = PullRequestJson(
                                    title = "Add support for configuration cache",
                                    merged = false,
                                    html_url = "url/to/pull/request"
                                ),
                                number = 8
                            ),
                            repo = RepoJson(
                                name = "ivanalvarado/ivanalvarado",
                                url = "https://api.github.com/repos/ivanalvarado/ivanalvarado"
                            ),
                            created_at = "2024-01-28T20:27:53Z"
                        ),
                        EventsJson(
                            type = "WatchEvent",
                            payload = PayloadJson(
                                action = "started"
                            ),
                            repo = RepoJson(
                                name = "ivanalvarado/ivanalvarado",
                                url = "https://api.github.com/repos/ivanalvarado/ivanalvarado"
                            ),
                            created_at = "2024-01-28T20:27:53Z"
                        ),
                        EventsJson(type = "PushEvent")
                    ),
                    githubEvents = listOf(
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
                        GithubEvent.IssuesEvent(
                            action = "opened",
                            issueNumber = 42,
                            title = "Something is broken",
                            issueUrl = "url/to/issue",
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
                        GithubEvent.WatchEvent(
                            action = "started",
                            repoName = "ivanalvarado/ivanalvarado",
                            repoUrl = "https://github.com/ivanalvarado/ivanalvarado",
                            createdAt = "2024-01-28T20:27:53Z"
                        ),
                        GithubEvent.UnsupportedEvent
                    )
                )
            )
        }

        private fun params(
            eventsJson: List<EventsJson>,
            githubEvents: List<GithubEvent>
        ): Array<Any?> {
            return arrayOf(eventsJson, githubEvents)
        }
    }
}
