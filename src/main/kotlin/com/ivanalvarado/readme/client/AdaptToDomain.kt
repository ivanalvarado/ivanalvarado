package com.ivanalvarado.readme.client

import com.ivanalvarado.readme.domain.model.ActivityItem
import com.ivanalvarado.readme.network.model.EventsJson

class AdaptToDomain {
    operator fun invoke(eventsJson: List<EventsJson>): List<ActivityItem> {
        return emptyList()
    }
}
