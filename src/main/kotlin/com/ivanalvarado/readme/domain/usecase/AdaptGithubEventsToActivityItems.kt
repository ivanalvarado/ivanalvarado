package com.ivanalvarado.readme.domain.usecase

import com.ivanalvarado.readme.domain.model.ActivityItem
import com.ivanalvarado.readme.domain.model.GithubEvent

interface AdaptGithubEventsToActivityItems {
    operator fun invoke(githubEvents: List<GithubEvent>): List<ActivityItem>
}
