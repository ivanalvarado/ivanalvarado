package com.ivanalvarado.readme.domain.usecase

import com.ivanalvarado.readme.domain.model.ActivityItem
import com.ivanalvarado.readme.domain.model.GithubEvent
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class AdaptGithubEventsToActivityItemsImpl : AdaptGithubEventsToActivityItems {
    override fun invoke(githubEvents: List<GithubEvent>): List<ActivityItem> {
        return githubEvents.filter { githubEvent ->
            !githubEvent.javaClass.isInstance(GithubEvent.UnsupportedEvent)
        }.map { githubEvent ->
            when(githubEvent) {
                is GithubEvent.CreateEvent -> {
                    ActivityItem(
                        message = "⚡️ created ${githubEvent.refType} `${githubEvent.ref}` on [${githubEvent.repoName}](${githubEvent.repoUrl})",
                        date = "${githubEvent.createdAt.parseDate()}"
                    )
                }
                is GithubEvent.DeleteEvent -> {
                    ActivityItem(
                        message = "✏️ deleted ${githubEvent.refType} `${githubEvent.ref}` on [${githubEvent.repoName}](${githubEvent.repoUrl})",
                        date = "${githubEvent.createdAt.parseDate()}"
                    )
                }
                is GithubEvent.IssueCommentEvent -> {
                    ActivityItem(
                        message = "💬 commented on [#${githubEvent.issueNumber}](${githubEvent.issueCommentUrl}) in [${githubEvent.repoName}](${githubEvent.repoUrl})",
                        date = "${githubEvent.createdAt.parseDate()}"
                    )
                }
                is GithubEvent.IssuesEvent -> {
                    ActivityItem(
                        message = "📝 ${githubEvent.action} issue [#${githubEvent.issueNumber}](${githubEvent.issueUrl}) on [${githubEvent.repoName}](${githubEvent.repoUrl}): \"${githubEvent.title}\"",
                        date = "${githubEvent.createdAt.parseDate()}"
                    )
                }
                is GithubEvent.PullRequestEvent -> {
                    val action = if(githubEvent.merged) "merged" else githubEvent.action
                    ActivityItem(
                        message = "🧑🏻‍💻 $action PR [#${githubEvent.number}](${githubEvent.prUrl}) to [${githubEvent.repoName}](${githubEvent.repoUrl}): \"${githubEvent.title}\"",
                        date = "${githubEvent.createdAt.parseDate()}"
                    )
                }
                is GithubEvent.WatchEvent -> {
                    ActivityItem(
                        message = "👀 ${githubEvent.action} watching [${githubEvent.repoName}](${githubEvent.repoUrl})",
                        date = "${githubEvent.createdAt.parseDate()}"
                    )
                }
                is GithubEvent.ForkEvent -> {
                    ActivityItem(
                        message = "🔱 forked [${githubEvent.fullName}](${githubEvent.htmlUrl}) from [${githubEvent.repoName}](${githubEvent.repoUrl})",
                        date = "${githubEvent.createdAt.parseDate()}"
                    )
                }
                else -> throw IllegalStateException("Unsupported Event: $githubEvent")
            }
        }.take(FIRST_TEN)
    }

    /**
     * Parses just the date from the datetime format.
     * Example: 2024-01-28T20:27:53Z --> 2024-01-28
     */
    private fun String.parseDate(): LocalDate {
        return Instant.parse(this).toLocalDateTime(TimeZone.of("America/Los_Angeles")).date
    }

    companion object {
        private const val FIRST_TEN = 10
    }
}
