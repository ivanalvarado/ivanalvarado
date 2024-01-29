package com.ivanalvarado.readme.domain.model

data class ActivityItem(
    val message: String,
    val date: String
) {
    override fun toString(): String {
        return "**$date** - $message"
    }
}
