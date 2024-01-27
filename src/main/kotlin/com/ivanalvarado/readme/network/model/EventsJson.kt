package com.ivanalvarado.readme.network.model

data class EventsJson(
    val id: String?,
    val type: String?,
    val actor: ActorJson?,
    val repo: RepoJson?,
    val payload: PayloadJson?,
    val public: Boolean?,
    val created_at: String?
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
    val push_id: Int?,
    val size: Int?,
    val distinct_size: Int?,
    val ref: String?,
    val ref_type: String?,
    val master_branch: String?,
    val description: String?,
    val pusher_type: String,
    val head: String?,
    val before: String?,
    val commits: List<CommitJson>
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
