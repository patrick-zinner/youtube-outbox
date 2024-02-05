package com.youtube.patrickzinner.outbox.service

import com.youtube.patrickzinner.outbox.peristence.TaskOutboxRepo
import com.youtube.patrickzinner.outbox.peristence.TaskRepo
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.OffsetDateTime
import java.util.UUID

@Service
class TaskService(
        private val taskRepo: TaskRepo,
        private val taskOutboxRepo: TaskOutboxRepo
) {

    @Transactional
    fun create(task: Task) : Task {
        val task = taskRepo.create(task)
        val taskOutbox = TaskOutbox(
                id = UUID.randomUUID(),
                taskId = task.id,
                createdAt = OffsetDateTime.now(),
                sentToBus = false
        )
        taskOutboxRepo.save(taskOutbox)
        return task
    }

}