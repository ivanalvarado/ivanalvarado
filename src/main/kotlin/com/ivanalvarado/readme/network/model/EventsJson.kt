package com.ivanalvarado.readme.network.model

data class EventsJson(
    val id: String? = null,
    val type: String? = null,
    val actor: ActorJson? = null,
    val repo: RepoJson? = null,
    val payload: PayloadJson? = null,
    val public: Boolean? = null,
    val created_at: String? = null,
    val org: OrgJson? = null
)

data class ActorJson(
    val id: Int? = null,
    val login: String? = null,
    val display_login: String? = null,
    val gravatar_id: String? = null,
    val url: String? = null,
    val avatar_url: String?
)

data class RepoJson(
    val id: Int? = null,
    val name: String? = null,
    val url: String?
)

data class PayloadJson(
    val action: String? = null,
    val push_id: Long? = null,
    val size: Int? = null,
    val distinct_size: Int? = null,
    val ref: String? = null,
    val ref_type: String? = null,
    val master_branch: String? = null,
    val description: String? = null,
    val pusher_type: String? = null,
    val head: String? = null,
    val before: String? = null,
    val commits: List<CommitJson>? = null,
    val issue: IssueJson? = null,
    val pull_request: PullRequestJson? = null,
    val number: Int? = null
)

data class CommitJson(
    val sha: String? = null,
    val author: AuthorJson? = null,
    val message: String? = null,
    val distinct: Boolean? = null,
    val url: String? = null
)

data class AuthorJson(
    val email: String? = null,
    val name: String? = null
)

data class IssueJson(
    val url: String? = null,
    val repository_url: String? = null,
    val labels_url: String? = null,
    val comments_url: String? = null,
    val events_url: String? = null,
    val html_url: String? = null,
    val id: Int? = null,
    val node_id: String? = null,
    val number: Int? = null,
    val title: String? = null,
    val user: UserJson? = null,
    val state: String? = null,
    val locked: Boolean? = null,
    val assignee: String? = null,
    val milestone: String? = null,
    val comments: Int? = null,
    val created_at: String? = null,
    val updated_at: String? = null,
    val closed_at: String? = null,
    val autho_association: String? = null,
    val active_lock_reason: String? = null,
    val body: String? = null,
    val timeline_url: String? = null

)

data class UserJson(
    val login: String? = null,
    val id: Int? = null,
    val node_id: String? = null,
    val avatar_url: String? = null,
    val gravatar_id: String? = null,
    val url: String? = null,
    val html_url: String? = null,
    val followers_url: String? = null,
    val following_url: String? = null,
    val gists_url: String? = null,
    val starred_url: String? = null,
    val subscriptions_url: String? = null,
    val organizations_url: String? = null,
    val repos_url: String? = null,
    val events_url: String? = null,
    val received_events_url: String? = null,
    val type: String? = null,
    val site_admin: Boolean? = null
)

data class OrgJson(
    val id: Int? = null,
    val login: String? = null,
    val gravatar_id: String? = null,
    val url: String? = null,
    val avatar_url: String? = null
)

data class PullRequestJson(
    val id: Long? = null,
    val html_url: String? = null,
    val title: String? = null,
    val merged: Boolean? = null
)
