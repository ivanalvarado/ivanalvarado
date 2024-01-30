package com.ivanalvarado.readme.client

import com.ivanalvarado.readme.domain.model.GithubEvent
import com.ivanalvarado.readme.network.model.EventsJson

class AdaptToDomain {
    operator fun invoke(eventsJson: List<EventsJson>): List<GithubEvent> {
        return eventsJson.map { event ->
            when(event.type) {
                "CreateEvent" -> {
                    GithubEvent.CreateEvent(
                        refType = event.payload?.ref_type.orEmpty(),
                        ref = event.payload?.ref.orEmpty(),
                        repoName = event.repo?.name.orEmpty(),
                        repoUrl = event.repo?.getHtmlUrl().orEmpty(),
                        createdAt = event.created_at.orEmpty()
                    )
                }
                "DeleteEvent" -> {
                    GithubEvent.DeleteEvent(
                        refType = event.payload?.ref_type.orEmpty(),
                        ref = event.payload?.ref.orEmpty(),
                        repoName = event.repo?.name.orEmpty(),
                        repoUrl = event.repo?.getHtmlUrl().orEmpty(),
                        createdAt = event.created_at.orEmpty()
                    )
                }
                "IssuesEvent" -> {
                    GithubEvent.IssuesEvent(
                        action = event.payload?.action.orEmpty(),
                        issueNumber = event.payload?.issue?.number ?: 0,
                        title = event.payload?.issue?.title.orEmpty(),
                        issueUrl = event.payload?.issue?.html_url.orEmpty(),
                        repoName = event.repo?.name.orEmpty(),
                        repoUrl = event.repo?.getHtmlUrl().orEmpty(),
                        createdAt = event.created_at.orEmpty()
                    )
                }
                "IssueCommentEvent" -> {
                    GithubEvent.IssueCommentEvent(
                        issueNumber = event.payload?.issue?.number ?: 0,
                        issueCommentUrl = event.payload?.issue?.comments_url.orEmpty(),
                        repoName = event.repo?.name.orEmpty(),
                        repoUrl = event.repo?.getHtmlUrl().orEmpty(),
                        createdAt = event.created_at.orEmpty()
                    )
                }
                "PullRequestEvent" -> {
                    GithubEvent.PullRequestEvent(
                        action = event.payload?.action.orEmpty(),
                        title = event.payload?.pull_request?.title.orEmpty(),
                        merged = event.payload?.pull_request?.merged ?: false,
                        number = event.payload?.number ?: 0,
                        prUrl = event.payload?.pull_request?.html_url.orEmpty(),
                        repoName = event.repo?.name.orEmpty(),
                        repoUrl = event.repo?.getHtmlUrl().orEmpty(),
                        createdAt = event.created_at.orEmpty()
                    )
                }
                else -> {
                    GithubEvent.UnsupportedEvent
                }
            }
        }
    }
}
