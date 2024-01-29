package com.ivanalvarado.readme.domain.usecase

import com.ivanalvarado.readme.domain.model.ActivityItem
import com.ivanalvarado.readme.domain.model.GithubEvent

class AdaptGithubEventsToActivityItemsImpl : AdaptGithubEventsToActivityItems {
    override fun invoke(githubEvents: List<GithubEvent>): List<ActivityItem> {
        return githubEvents.filter { githubEvent ->
            !githubEvent.javaClass.isInstance(GithubEvent.UnsupportedEvent)
        }.map { githubEvent ->
            when(githubEvent) {
                is GithubEvent.CreateEvent -> {
                    ActivityItem(
                        message = "⚡️created ${githubEvent.refType} ${githubEvent.ref} on ${githubEvent.repoUrl}",
                        date = githubEvent.createdAt.parseDate()
                    )
                }
                is GithubEvent.DeleteEvent -> {
                    ActivityItem(
                        message = "✏️ deleted ${githubEvent.refType} ${githubEvent.ref} on ${githubEvent.repoUrl}",
                        date = githubEvent.createdAt.parseDate()
                    )
                }
                is GithubEvent.IssueCommentEvent -> {
                    ActivityItem(
                        message = "💬 commented on [#${githubEvent.issueNumber}](${githubEvent.issueCommentUrl}) in ${githubEvent.repoUrl}",
                        date = githubEvent.createdAt.parseDate()
                    )
                }
                is GithubEvent.IssuesEvent -> {
                    ActivityItem(
                        message = "📝 ${githubEvent.action} issue [#${githubEvent.issueNumber}](${githubEvent.issueUrl}) on ${githubEvent.repoUrl}: \"${githubEvent.title}\"",
                        date = githubEvent.createdAt.parseDate()
                    )
                }
                is GithubEvent.PullRequestEvent -> {
                    val action = if(githubEvent.merged) "merged" else githubEvent.action
                    ActivityItem(
                        message = "🧑🏻‍💻$action PR [${githubEvent.number}](${githubEvent.prUrl}) to ${githubEvent.repoUrl}: \"${githubEvent.title}\"",
                        date = githubEvent.createdAt.parseDate()
                    )
                }
                else -> throw IllegalStateException("Unsupported Event: $githubEvent")
            }
        }.take(FIRST_TEN)
    }

    private fun String.parseDate(): String {
        return this.substring(0, 10)
    }

    companion object {
        private const val FIRST_TEN = 10
    }
}
