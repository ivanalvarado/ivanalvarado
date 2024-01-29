package com.ivanalvarado.readme.domain.usecase

import com.ivanalvarado.readme.domain.model.ActivityItem

interface GenerateReadMeTemplate {
    operator fun invoke(githubActivity: List<ActivityItem>): String
}
