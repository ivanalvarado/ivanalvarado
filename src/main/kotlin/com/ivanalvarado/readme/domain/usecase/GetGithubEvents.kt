package com.ivanalvarado.readme.domain.usecase

import com.ivanalvarado.readme.domain.model.GithubEvent

interface GetGithubEvents {
    suspend operator fun invoke(login: String): List<GithubEvent>
}
