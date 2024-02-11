package com.youtube.patrickzinner.outbox.peristence

import com.youtube.patrickzinner.outbox.service.Task
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class TaskRepo(
        private val taskJpaRepo: TaskJpaRepo
) {
    fun create(task: Task): Task {
        log.debug { "Saving task ${task.name} to DB" }
        val entity = modelToEntity(task)
        return entityToModel(
                taskJpaRepo.save(entity)
        )
    }

    companion object {

        private val log = KotlinLogging.logger {}
    }

}