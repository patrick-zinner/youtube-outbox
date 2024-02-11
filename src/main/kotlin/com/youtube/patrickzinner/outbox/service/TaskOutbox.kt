package com.youtube.patrickzinner.outbox.service

import java.time.OffsetDateTime
import java.util.*

data class TaskOutbox(
        val id: UUID,
        val task: Task,
        val createdAt: OffsetDateTime,
        val sentToBus: Boolean
)
