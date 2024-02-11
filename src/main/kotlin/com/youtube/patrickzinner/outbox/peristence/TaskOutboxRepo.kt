package com.youtube.patrickzinner.outbox.peristence

import com.youtube.patrickzinner.outbox.service.TaskOutbox
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

}