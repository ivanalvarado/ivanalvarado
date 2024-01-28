package com.ivanalvarado.readme.network.model

data class EventsJson(
    val id: String?,
    val type: String?,
    val actor: ActorJson?,
    val repo: RepoJson?,
    val payload: PayloadJson?,
    val public: Boolean?,
    val created_at: String?,
    val org: OrgJson?
)

data class ActorJson(
    val id: Int?,
    val login: String?,
    val display_login: String?,
    val gravatar_id: String?,
    val url: String?,
    val avatar_url: String?
)

data class RepoJson(
    val id: Int?,
    val name: String?,
    val url: String?
)

data class PayloadJson(
    val action: String?,
    val push_id: Long?,
    val size: Int?,
    val distinct_size: Int?,
    val ref: String?,
    val ref_type: String?,
    val master_branch: String?,
    val description: String?,
    val pusher_type: String?,
    val head: String?,
    val before: String?,
    val commits: List<CommitJson>?,
    val issue: IssueJson?,
    val pull_request: PullRequestJson?,
    val number: Int?
)

data class CommitJson(
    val sha: String?,
    val author: AuthorJson?,
    val message: String?,
    val distinct: Boolean?,
    val url: String?
)

data class AuthorJson(
    val email: String?,
    val name: String?
)

data class IssueJson(
    val url: String?,
    val repository_url: String?,
    val labels_url: String?,
    val comments_url: String?,
    val events_url: String?,
    val html_url: String?,
    val id: Int?,
    val node_id: String?,
    val number: Int?,
    val title: String?,
    val user: UserJson?,
    val state: String?,
    val locked: Boolean?,
    val assignee: String?,
    val milestone: String?,
    val comments: Int?,
    val created_at: String?,
    val updated_at: String?,
    val closed_at: String?,
    val autho_association: String?,
    val active_lock_reason: String?,
    val body: String?,
    val timeline_url: String?,

)

data class UserJson(
    val login: String?,
    val id: Int?,
    val node_id: String?,
    val avatar_url: String?,
    val gravatar_id: String?,
    val url: String?,
    val html_url: String?,
    val followers_url: String?,
    val following_url: String?,
    val gists_url: String?,
    val starred_url: String?,
    val subscriptions_url: String?,
    val organizations_url: String?,
    val repos_url: String?,
    val events_url: String?,
    val received_events_url: String?,
    val type: String?,
    val site_admin: Boolean?
)

data class OrgJson(
    val id: Int?,
    val login: String?,
    val gravatar_id: String?,
    val url: String?,
    val avatar_url: String?
)

data class PullRequestJson(
    val id: Long?,
    val html_url: String?,
    val title: String?,
    val merged: Boolean?
)
