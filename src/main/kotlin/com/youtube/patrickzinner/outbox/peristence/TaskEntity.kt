package com.youtube.patrickzinner.outbox.peristence

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "task")
data class TaskEntity(
        @Id
        val id: UUID,
        val name: String
)
