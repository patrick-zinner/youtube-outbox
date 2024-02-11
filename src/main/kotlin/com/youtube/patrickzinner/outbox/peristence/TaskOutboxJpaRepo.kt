package com.youtube.patrickzinner.outbox.peristence

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface TaskOutboxJpaRepo : JpaRepository<TaskOutboxEntity, UUID> {
    fun findByTaskId(taskId: UUID): TaskOutboxEntity
    fun findAllBySentToBusFalse(pageable: Pageable): List<TaskOutboxEntity>
}