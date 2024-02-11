package com.youtube.patrickzinner.outbox.peristence

import com.youtube.patrickzinner.outbox.service.Task
import org.springframework.stereotype.Component

@Component
class TaskRepo(
        private val taskJpaRepo: TaskJpaRepo
) {
    fun create(task: Task): Task {
        val entity = modelToEntity(task)
        return entityToModel(
                taskJpaRepo.save(entity)
        )
    }

}