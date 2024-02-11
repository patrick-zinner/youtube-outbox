package com.youtube.patrickzinner.outbox.peristence

import com.youtube.patrickzinner.outbox.service.TaskOutbox
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component

@Component
class TaskOutboxRepo(
        private val jpaRepo: TaskOutboxJpaRepo
) {
    fun save(taskOutbox: TaskOutbox): TaskOutbox {
        val entity = modelToEntity(taskOutbox)
        return entityToModel(
                jpaRepo.save(entity)
        )
    }

    fun findAllNotSent(limit: Int): List<TaskOutbox> {
        return jpaRepo.findAllBySentToBusFalse(
                PageRequest.of(
                        0,
                        limit,
                        Sort.by("createdAt").ascending()
                )
        ).map { entityToModel(it) }
    }

}