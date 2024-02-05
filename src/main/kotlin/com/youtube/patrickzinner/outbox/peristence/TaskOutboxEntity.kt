package com.youtube.patrickzinner.outbox.peristence

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.OffsetDateTime
import java.util.UUID

@Entity
@Table(name = "task_outbox")
data class TaskOutboxEntity(
        @Id
        val id: UUID,
        val taskId: UUID,
        val createdAt: OffsetDateTime,
        val sentToBus: Boolean
)
