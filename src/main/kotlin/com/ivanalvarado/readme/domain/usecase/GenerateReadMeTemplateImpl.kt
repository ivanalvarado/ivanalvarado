package com.ivanalvarado.readme.domain.usecase

import com.ivanalvarado.readme.domain.model.ActivityItem

class GenerateReadMeTemplateImpl : GenerateReadMeTemplate {
    override fun invoke(githubActivity: List<ActivityItem>): String {
        return """
### Android DevXp @ [Tinder](https://medium.com/tinder)

## Github Activity
${githubActivity.joinToString(separator = "\n") { "- $it"}}
        """.trimIndent()
    }
}
