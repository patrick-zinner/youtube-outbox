package com.youtube.patrickzinner.outbox.peristence

import jakarta.persistence.*
import java.time.OffsetDateTime
import java.util.UUID

@Entity
@Table(name = "task_outbox")
data class TaskOutboxEntity(
        @Id
        val id: UUID,
        @OneToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "task_id")
        val task: TaskEntity,
        val createdAt: OffsetDateTime,
        val sentToBus: Boolean
)
