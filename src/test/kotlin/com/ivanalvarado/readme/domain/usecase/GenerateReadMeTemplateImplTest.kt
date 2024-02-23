package com.ivanalvarado.readme.domain.usecase

import com.ivanalvarado.readme.domain.model.ActivityItem
import org.junit.Test
import kotlin.test.assertEquals

class GenerateReadMeTemplateImplTest {

    @Test
    fun `invoke - given a list of activity items, should return markdown for readme`() {
        // Given
        val generateReadMeTemplate = GenerateReadMeTemplateImpl()

        // When
        val result = generateReadMeTemplate(githubActivity)

        // Then
        assertEquals(expectedReadMeTemplate, result)
    }

    private val githubActivity = listOf(
        ActivityItem(
            message = "⚡️ created branch `main` on https://github.com/ivanalvarado/ivanalvarado",
            date = "2024-01-28"
        ),
        ActivityItem(
            message = "✏️ deleted branch `experimental-branch` on https://github.com/ivanalvarado/ivanalvarado",
            date = "2024-01-28"
        ),
        ActivityItem(
            message = "💬 commented on [#42](url/to/comment) in https://github.com/ivanalvarado/ivanalvarado",
            date = "2024-01-28"
        ),
        ActivityItem(
            message = "📝 opened issue [#42](url/to/issue) on https://github.com/ivanalvarado/ivanalvarado: \"Something is broken\"",
            date = "2024-01-28"
        ),
        ActivityItem(
            message = "🧑🏻‍💻 merged PR [#8](url/to/pull/request) to https://github.com/ivanalvarado/ivanalvarado: \"Add support for configuration cache\"",
            date = "2024-01-28"
        ),
        ActivityItem(
            message = "🧑🏻‍💻 opened PR [#8](url/to/pull/request) to https://github.com/ivanalvarado/ivanalvarado: \"Add support for configuration cache\"",
            date = "2024-01-28"
        )
    )

    private val expectedReadMeTemplate = """
        ### Mobile DevXp @ [Tinder](https://medium.com/tinder)
        
        ## Github Activity
        - **2024-01-28** - ⚡️ created branch `main` on https://github.com/ivanalvarado/ivanalvarado
        - **2024-01-28** - ✏️ deleted branch `experimental-branch` on https://github.com/ivanalvarado/ivanalvarado
        - **2024-01-28** - 💬 commented on [#42](url/to/comment) in https://github.com/ivanalvarado/ivanalvarado
        - **2024-01-28** - 📝 opened issue [#42](url/to/issue) on https://github.com/ivanalvarado/ivanalvarado: "Something is broken"
        - **2024-01-28** - 🧑🏻‍💻 merged PR [#8](url/to/pull/request) to https://github.com/ivanalvarado/ivanalvarado: "Add support for configuration cache"
        - **2024-01-28** - 🧑🏻‍💻 opened PR [#8](url/to/pull/request) to https://github.com/ivanalvarado/ivanalvarado: "Add support for configuration cache"
    """.trimIndent()
}
