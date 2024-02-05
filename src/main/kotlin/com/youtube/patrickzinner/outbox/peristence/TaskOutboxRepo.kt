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

    fun modelToEntity(taskOutbox: TaskOutbox) =
            TaskOutboxEntity(
                    id = taskOutbox.id,
                    taskId = taskOutbox.taskId,
                    createdAt = taskOutbox.createdAt,
                    sentToBus = taskOutbox.sentToBus
            )

    fun entityToModel(entity: TaskOutboxEntity) =
            TaskOutbox(
                    id = entity.id,
                    taskId = entity.taskId,
                    createdAt = entity.createdAt,
                    sentToBus = entity.sentToBus
            )

}