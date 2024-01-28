package com.ivanalvarado.readme.domain.model

sealed class GithubEvent {
    abstract val repoUrl: String
    abstract val createdAt: String

    data class IssuesEvent(
        val action: String,
        val issueNumber: Int,
        val title: String,
        val issueUrl: String,
        override val repoUrl: String,
        override val createdAt: String
    ): GithubEvent()

    data class IssueCommentEvent(
        val issueNumber: Int,
        val issueCommentUrl: String,
        override val repoUrl: String,
        override val createdAt: String
    ): GithubEvent()

    data class PullRequestEvent(
        val action: String,
        val title: String,
        val merged: Boolean,
        val number: Int,
        val prUrl: String,
        override val repoUrl: String,
        override val createdAt: String
    ): GithubEvent()

    data class CreateEvent(
        val refType: String,
        val ref: String,
        override val repoUrl: String,
        override val createdAt: String
    ): GithubEvent()

    data class DeleteEvent(
        val refType: String,
        val ref: String,
        override val repoUrl: String,
        override val createdAt: String
    ): GithubEvent()

     data object UnsupportedEvent : GithubEvent() {
         override val repoUrl: String = ""
         override val createdAt: String = ""
     }
}
